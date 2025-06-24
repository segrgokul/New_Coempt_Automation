package dataProcessing;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.IOUtils;
public class ReadExcelFiles {

    public static Object[][] readExcel(String fileKey, String sheetKey) throws IOException {
        // Load the properties from the config file
    	   IOUtils.setByteArrayMaxOverride(200 * 1024 * 1024); // Set limit to 200 MB
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Get file path and sheet name for the given keys
        String filePath = prop.getProperty(fileKey + ".path");
        String sheetName = prop.getProperty(sheetKey + ".sheet");

        if (filePath == null || sheetName == null) {
            throw new IllegalArgumentException("Invalid file or sheet key.");
        }

        // Open the Excel file
        FileInputStream excelFile = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(excelFile);

        // Get the sheet by name
        XSSFSheet sheet = workbook.getSheet(sheetName);

        // Get the row and column count
        int rowCount = sheet.getPhysicalNumberOfRows();
        int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

        // Prepare an Object array to store the data
        Object[][] data = new Object[rowCount - 1][columnCount]; // -1 to skip header row

        // Iterating through rows (starting from row 1 to skip header)
        int rowIndex = 0;
        for (Row row : sheet) {
            // Skip header row
            if (rowIndex == 0) {
                rowIndex++;
                continue;
            }

            // If the row is empty, skip it
            if (isRowEmpty(row)) {
                break;
            }

            int colIndex = 0;
            for (Cell cell : row) {
                if (cell.getCellType() != CellType.BLANK) {
                    // Handle cell types accordingly
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
                            data[rowIndex - 1][colIndex] = null; // For any other unsupported types
                            break;
                    }
                }
                colIndex++;
            }
            rowIndex++;
        }

        // Close resources
        workbook.close();
        excelFile.close();

        return data; // Return the populated data array
    }

    // Helper method to check if a row is empty
    private static boolean isRowEmpty(Row row) {
        boolean isEmpty = true;
        // Iterate through all cells in the row
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            // If any cell is not blank, the row is not empty
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                isEmpty = false;
                break;  // No need to check further cells if one non-empty cell is found
            }
        }
        return isEmpty;
    }
}