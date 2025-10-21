package org.tcs.readers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Utility class to read data from Excel (.xlsx) files.
 */
public class ExcelReader {
    private static final Logger LOGGER = LogManager.getLogger(ExcelReader.class);

    /**
     * Reads the sheet into a list of maps (row-wise).
     */
    public static List<Map<String, String>> getData(String filePath, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new IllegalStateException("No header row in sheet: " + sheetName);
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> dataMap = new HashMap<>();
                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    String key = getCellValueAsString(headerRow.getCell(j));
                    String value = getCellValueAsString(row.getCell(j));
                    dataMap.put(key, value);
                }
                dataList.add(dataMap);
            }

            LOGGER.info("Loaded {} rows from sheet '{}'", dataList.size(), sheetName);

        } catch (IOException e) {
            LOGGER.error("Failed to read Excel file: {}", filePath, e);
        }
        return dataList;
    }

    public static Map<String, String> getTestCaseData(String filePath, String sheetName, String testCaseName) {
        List<Map<String, String>> allRowsData = getData(filePath, sheetName);
        Map<String, String> testCaseData = new HashMap<>();
        for (Map<String, String> rowData : allRowsData) {
            String testNameFromExcel = rowData.get("TestCaseName");
            if (testNameFromExcel != null && testNameFromExcel.equalsIgnoreCase(testCaseName)) {
                LOGGER.info("Returning data for test case {}", testCaseName);
                testCaseData = rowData;
            }
        }
        return testCaseData;
    }

    /**
     * Returns total row count.
     */
    public static int getRowCount(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            return (sheet != null) ? sheet.getLastRowNum() : 0;
        } catch (IOException e) {
            LOGGER.error("Failed to get row count for sheet {}", sheetName, e);
        }
        return 0;
    }

    /**
     * Returns total column count from header row.
     */
    public static int getColumnCount(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row header = (sheet != null) ? sheet.getRow(0) : null;
            return (header != null) ? header.getLastCellNum() : 0;
        } catch (IOException e) {
            LOGGER.error("Failed to get column count for sheet {}", sheetName, e);
        }
        return 0;
    }

    /**
     * Returns cell data at given row/column.
     */
    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row row = (sheet != null) ? sheet.getRow(rowNum) : null;
            Cell cell = (row != null) ? row.getCell(colNum) : null;
            return getCellValueAsString(cell);
        } catch (IOException e) {
            LOGGER.error("Failed to get cell data from sheet {}", sheetName, e);
        }
        return "";
    }

    /**
     * Returns all sheet names.
     */
    public static List<String> getSheetNames(String filePath) {
        List<String> sheetNames = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                sheetNames.add(workbook.getSheetName(i));
            }
        } catch (IOException e) {
            LOGGER.error("Failed to read sheet names from file: {}", filePath, e);
        }
        return sheetNames;
    }

    /**
     * Converts any cell value to String.
     */
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }
}
