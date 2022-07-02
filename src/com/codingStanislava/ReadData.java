package com.codingStanislava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import com.codingStanislava.view.GUIFrequencyTable;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;

public class ReadData {
    static final int NUMBER_ROWS = 28090;
    public void readDataLogs() throws IOException {
        GUIFrequencyTable menu = new GUIFrequencyTable();
        menu.createAndShowGUI();
    }


    public void readDataYear1() throws IOException {
        FileInputStream fis = new FileInputStream(new File(".\\Course A_StudentsResults_Year 1.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue()+ "\t\t");
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue()+ "\t\t");

                        break;
                }
            }

            System.out.println();
        }
    }
    public void readDataYear2() throws IOException {
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\Stanislava1505\\Desktop\\vvps\\project\\PTS_VVPS_Kursova\\src\\com\\codingStanislava\\Course A_StudentsResults_Year 2.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue()+ "\t\t");
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue()+ "\t\t");

                        break;
                }
            }
            System.out.println();
        }
    }
    public void iterateOverColumnsWiki() throws IOException {
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\Stanislava1505\\Desktop\\vvps\\project\\PTS_VVPS_Kursova\\src\\com\\codingStanislava\\Logs_Course A_StudentsActivities.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);
        int columnIndex = 3;
        for(int rowIndex = 1; rowIndex<NUMBER_ROWS; rowIndex++) {
            Row row = CellUtil.getRow(rowIndex, sheet);
            Cell cell = CellUtil.getCell(row, columnIndex);
            //  System.out.println(cell.getStringCellValue());
            if (cell.getStringCellValue().equals("Wiki page updated")) {
                Row rowWiki = sheet.getRow(rowIndex);
                Iterator<Cell> cellItr = rowWiki.iterator();
                while (cellItr.hasNext()) {
                    System.out.println(cellItr.next().toString());
                }
            }
        }
    }
    public void iterateOverColumnsAssignment() throws IOException {
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\Stanislava1505\\Desktop\\vvps\\project\\PTS_VVPS_Kursova\\src\\com\\codingStanislava\\Logs_Course A_StudentsActivities.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);
        int columnIndex = 2;
        for(int rowIndex = 1; rowIndex<NUMBER_ROWS; rowIndex++) {
            Row row = CellUtil.getRow(rowIndex, sheet);
            Cell cell = CellUtil.getCell(row, columnIndex);
            //  System.out.println(cell.getStringCellValue());
            if (cell.getStringCellValue().equals("File submissions")) {
                Row rowFileSubmissions = sheet.getRow(rowIndex);
                Iterator<Cell> cellItr = rowFileSubmissions.iterator();
                while (cellItr.hasNext()) {
                    System.out.println(cellItr.next().toString());
                }
            }
        }
    }
}


