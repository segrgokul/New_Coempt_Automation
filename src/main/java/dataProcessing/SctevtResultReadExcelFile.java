package dataProcessing;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.util.IOUtils;

public class SctevtResultReadExcelFile {

    public static Map<String, StudentInfo> readExcel(String fileKey, String sheetKey) throws IOException {
        IOUtils.setByteArrayMaxOverride(200 * 1024 * 1024);
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(input);
        }

        String filePath = prop.getProperty(fileKey + ".path");
        String sheetName = prop.getProperty(sheetKey + ".sheet");

        if (filePath == null || sheetName == null) {
            throw new IllegalArgumentException("Invalid file or sheet key.");
        }

        FileInputStream excelFile = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][columnCount];
        String[] headers = new String[columnCount];
        Row headerRow = sheet.getRow(0);

        int rowIndex = 0;
        for (Row row : sheet) {
            if (rowIndex == 0) {
                for (int i = 0; i < columnCount; i++) {
                    Cell cell = headerRow.getCell(i);
                    headers[i] = (cell != null) ? cell.getStringCellValue().trim() : "";
                }
                rowIndex++;
                continue;
            }

            if (isRowEmpty(row)) break;

            for (int colIndex = 0; colIndex < columnCount; colIndex++) {
                Cell cell = row.getCell(colIndex);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    if (headers[colIndex].toLowerCase().contains("registration")) {
                        if (cell.getCellType() == CellType.STRING) {
                            data[rowIndex - 1][colIndex] = cell.getStringCellValue().trim();
                        } else {
                            data[rowIndex - 1][colIndex] = String.format("%.0f", cell.getNumericCellValue());
                        }
                    } else {
                        switch (cell.getCellType()) {
                            case STRING:
                                data[rowIndex - 1][colIndex] = cell.getStringCellValue().trim();
                                break;
                            case NUMERIC:
                                data[rowIndex - 1][colIndex] = cell.getNumericCellValue();
                                break;
                            case BOOLEAN:
                                data[rowIndex - 1][colIndex] = cell.getBooleanCellValue();
                                break;
                            case FORMULA:
                                data[rowIndex - 1][colIndex] = cell.getCellFormula();
                                break;
                            default:
                                data[rowIndex - 1][colIndex] = null;
                        }
                    }
                }
            }
            rowIndex++;
        }

        workbook.close();
        excelFile.close();

        Map<String, StudentInfo> grouped = groupByRegNo(headers, data);

        // ✅ Print grouped info
        System.out.println("\n----- Grouped Student Information -----");
        for (Map.Entry<String, StudentInfo> entry : grouped.entrySet()) {
            String[] keyParts = entry.getKey().split("_", 2);
            String regNo = keyParts[0];
            String semester = keyParts.length > 1 ? keyParts[1] : "";

            StudentInfo s = entry.getValue();
            System.out.println("Reg No: " + regNo);
            System.out.println("  Exam Semester: " + semester);
            System.out.println("  Subjects and Marks:");
            for (Map.Entry<String, String> subject : s.subjectsAndMarks.entrySet()) {
                System.out.println("    " + subject.getKey() + ": " + subject.getValue());
            }
            System.out.println("--------------------------------------");
        }

        return grouped;
    }

    private static boolean isRowEmpty(Row row) {
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) return false;
        }
        return true;
    }

    public static Map<String, StudentInfo> groupByRegNo(String[] headers, Object[][] data) {
        int regNoCol = -1, semesterCol = -1, subjectCol = -1;

        for (int i = 0; i < headers.length; i++) {
            String header = headers[i].trim().toLowerCase();
            if (header.contains("student registration number")) regNoCol = i;
            else if (header.contains("semester")) semesterCol = i;
            else if (header.contains("subject")) subjectCol = i;
        }

        if (regNoCol == -1 || semesterCol == -1 || subjectCol == -1) {
            throw new IllegalStateException("Could not identify required columns.");
        }

        List<Integer> marksCols = new ArrayList<>();
        for (int i = 0; i < headers.length; i++) {
            if (i != regNoCol && i != semesterCol && i != subjectCol) {
                marksCols.add(i);
            }
        }

        Map<String, StudentInfo> grouped = new LinkedHashMap<>();

        for (Object[] row : data) {
            if (row.length <= Math.max(regNoCol, subjectCol)) continue;

            String regNo = (row[regNoCol] != null) ? String.valueOf(row[regNoCol]).trim() : "";

            // ✅ Ensure semester is printed without ".0"
            String semester = "";
            if (row[semesterCol] instanceof Number) {
                semester = String.valueOf(((Number) row[semesterCol]).intValue());
            } else if (row[semesterCol] != null) {
                semester = row[semesterCol].toString().trim();
            }

            String subject = (row[subjectCol] != null) ? String.valueOf(row[subjectCol]).trim() : "";
            String key = regNo + "_" + semester;
            final String finalSemester = semester;
            StringBuilder marks = new StringBuilder();
            for (int idx : marksCols) {
                if (idx < row.length && row[idx] != null) {
                    marks.append(String.valueOf(row[idx]).trim()).append("; ");
                }
            }
            if (marks.length() > 2) marks.setLength(marks.length() - 2);

            StudentInfo info = grouped.computeIfAbsent(key, k -> {
                StudentInfo s = new StudentInfo();
                s.examSemester = finalSemester;
                return s;
            });

            info.subjectsAndMarks.put(subject, marks.toString());
        }

        return grouped;
    }

    public static List<Map<String, Object>> getStudentData(Map<String, StudentInfo> grouped) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map.Entry<String, StudentInfo> entry : grouped.entrySet()) {
            Map<String, Object> studentData = new LinkedHashMap<>();
            String[] parts = entry.getKey().split("_", 2);
            String regNo = parts.length > 0 ? parts[0] : "";
            String semester = parts.length > 1 ? parts[1] : "";

            StudentInfo s = entry.getValue();
            studentData.put("Reg No", regNo);
            studentData.put("Exam semester", semester);
            studentData.put("Subjects", s.subjectsAndMarks);

            result.add(studentData);
        }

        return result;
    }

    public static class StudentInfo {
        public String examSemester;
        public Map<String, String> subjectsAndMarks = new LinkedHashMap<>();
    }
}
