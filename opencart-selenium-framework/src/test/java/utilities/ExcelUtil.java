package utilities;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class ExcelUtil {
    private final Workbook workbook;

    public ExcelUtil(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            this.workbook = WorkbookFactory.create(fis);
        } catch (Exception e) {
            throw new RuntimeException("Unable to read Excel file: " + filePath, e);
        }
    }

    public String getCellData(String sheetName, int row, int col) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row r = sheet.getRow(row);
        Cell cell = r.getCell(col);
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }
}
