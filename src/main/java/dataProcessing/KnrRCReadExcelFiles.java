package dataProcessing;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class KnrRCReadExcelFiles {

    public static Map<String, StudentInfo> readExcel(String fileKey, String sheetKey) throws IOException {
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
        Row headerRow = sheet.getRow(0);
        String[] headers = new String[columnCount];

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

            if (isRowEmpty(row)) {
                break;
            }

            int colIndex = 0;
            for (Cell cell : row) {
                if (cell.getCellType() != CellType.BLANK) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data[rowIndex - 1][colIndex] = cell.getStringCellValue().trim();
                            break;
                        case NUMERIC:
                            data[rowIndex - 1][colIndex] = (long) cell.getNumericCellValue();
                            break;
                        case BOOLEAN:
                            data[rowIndex - 1][colIndex] = cell.getBooleanCellValue();
                            break;
                        case FORMULA:
                            data[rowIndex - 1][colIndex] = cell.getCellFormula();
                            break;
                        default:
                            data[rowIndex - 1][colIndex] = null;
                            break;
                    }
                }
                colIndex++;
            }
            rowIndex++;
        }

        workbook.close();
        excelFile.close();

        Map<String, StudentInfo> grouped = groupByRegNo(headers, data);

        // ✅ Pretty print grouped student info
        System.out.println("\n----- Grouped Student Information -----");
        for (Map.Entry<String, StudentInfo> entry : grouped.entrySet()) {
            String regNo = entry.getKey();
            StudentInfo s = entry.getValue();

            System.out.println("Reg No: " + regNo);
            System.out.println("  Exam Series: " + s.examSeries);
            System.out.println("  Award Name: " + s.awardName);
            System.out.println("  Sem Year: " + s.semYear);
            System.out.println("  Regulation: " + s.regulation);
            System.out.println("  Exam Type: " + s.examType);
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
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    public static Map<String, StudentInfo> groupByRegNo(String[] headers, Object[][] data) {
        int regNoCol = -1, examSeriesCol = -1, awardNameCol = -1, semYearCol = -1, regulationCol = -1, examTypeCol = -1, subjectCol = -1;

        for (int i = 0; i < headers.length; i++) {
            String header = headers[i].trim().toLowerCase();
            if (header.contains("student registration number")) regNoCol = i;
            else if (header.contains("exam series")) examSeriesCol = i;
            else if (header.contains("award name")) awardNameCol = i;
            else if (header.contains("sem")) semYearCol = i;
            else if (header.contains("regulation")) regulationCol = i;
            else if (header.contains("examtype")) examTypeCol = i;
            else if (header.contains("subject")) subjectCol = i;
        }

        if (regNoCol == -1 || examSeriesCol == -1 || awardNameCol == -1 || semYearCol == -1 || regulationCol == -1 || examTypeCol == -1 || subjectCol == -1) {
            throw new IllegalStateException("Could not identify required columns.");
        }

        List<Integer> marksCols = new ArrayList<>();
        for (int i = 0; i < headers.length; i++) {
            if (i != regNoCol && i != examSeriesCol && i != awardNameCol && i != semYearCol &&
                i != regulationCol && i != examTypeCol && i != subjectCol) {
                marksCols.add(i);
            }
        }

        Map<String, StudentInfo> grouped = new LinkedHashMap<>();

        for (Object[] row : data) {
            if (row.length <= Math.max(regNoCol, subjectCol)) continue;

            String regNo = (row[regNoCol] != null) ? String.valueOf(row[regNoCol]).trim() : "";
            String examSeries = (row[examSeriesCol] != null) ? String.valueOf(row[examSeriesCol]).trim() : "";
            String awardName = (row[awardNameCol] != null) ? String.valueOf(row[awardNameCol]).trim() : "";
            String semYear = (row[semYearCol] != null) ? String.valueOf(row[semYearCol]).trim() : "";
            String regulation = (row[regulationCol] != null) ? String.valueOf(row[regulationCol]).trim() : "";
            String examType = (row[examTypeCol] != null) ? String.valueOf(row[examTypeCol]).trim() : "";
            String subject = (row[subjectCol] != null) ? String.valueOf(row[subjectCol]).trim() : "";

            StringBuilder marks = new StringBuilder();
            for (int idx : marksCols) {
                if (idx < row.length && row[idx] != null) {
                    marks.append(String.valueOf(row[idx]).trim()).append("; ");
                }
            }

            if (marks.length() > 2) {
                marks.setLength(marks.length() - 2);
            }

            StudentInfo info = grouped.computeIfAbsent(regNo, k -> {
                StudentInfo s = new StudentInfo();
                s.examSeries = examSeries;
                s.awardName = awardName;
                s.semYear = semYear;
                s.regulation = regulation;
                s.examType = examType;
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
            studentData.put("Reg No", entry.getKey());

            StudentInfo s = entry.getValue();
            studentData.put("Exam Series", s.examSeries);
            studentData.put("Award Name", s.awardName);
            studentData.put("Sem Year", s.semYear);
            studentData.put("Regulation", s.regulation);
            studentData.put("Exam Type", s.examType);
            studentData.put("Subjects", s.subjectsAndMarks);

            result.add(studentData);
        }

        return result;
    }

    public static class StudentInfo {
        public String examSeries;
        public String awardName;
        public String semYear;
        public String regulation;
        public String examType;
        public Map<String, String> subjectsAndMarks = new LinkedHashMap<>();
    }
    
    
    
}
