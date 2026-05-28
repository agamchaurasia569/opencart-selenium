package com.ecommerce.framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelUtil {

    private ExcelUtil() {
    }

    public static String getCellData(String sheetName, int rowNum, int colNum) {
        Path path = Paths.get("src", "test", "resources", "testdata", "LoginData.xlsx").toAbsolutePath().normalize();
        try (FileInputStream fis = new FileInputStream(path.toFile());
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            Row row = sheet.getRow(rowNum);
            if (row == null) {
                throw new IllegalArgumentException("Row not found: " + rowNum);
            }

            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(row.getCell(colNum));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read Excel file: " + path, e);
        }
    }

    public static String getUsername(int rowNum) {
        return getCellData("LoginData", rowNum, 0);
    }

    public static String getPassword(int rowNum) {
        return getCellData("LoginData", rowNum, 1);
    }
}
