package com.codingStanislava;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class FrequencyTable {

    private  File file;
    private  SortedMap<String, FrequencyTableRow> exercises = new TreeMap<>();
    private  SortedMap<String, FrequencyTableRow> dates = new TreeMap<>();
    public  SortedMap<String, FrequencyTableRow> getExercises() throws IOException {
        ConvertTableToSMapExercises();
        return exercises;
    }


    public  SortedMap<String, FrequencyTableRow> getDates() throws IOException {
        ConvertTableToSMapDates();
        return dates;
    }

    public FrequencyTable(String filepath) {
        file = new File(filepath);
    }

    private  void ResetStatistics() {

        exercises = new TreeMap<>();
        dates = new TreeMap<>();
    }

    private  void ConvertTableToSMapDates() throws IOException{
        boolean exists = file.exists();

        if(!exists){
            throw new IOException("File not found.");
        }

        ResetStatistics();

        String month = new String();
        FileInputStream fis = new FileInputStream(file);
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);


        for (Row row : sheet) {
            if(row.getCell(1).toString().contains("Assignment: Качване на Упр.") && row.getCell(2).toString().contains("File submissions")){

                String date_str = row.getCell(0).toString();

                Pattern MY_PATTERN = Pattern.compile("/(.*)/");
                Matcher m = MY_PATTERN.matcher(date_str);
                while (m.find()) {
                    month = m.group(1);
                }

                if (dates.containsKey(month)) {

                    // Mapping
                    FrequencyTableRow date_temp = dates.get(month);
                    date_temp.AbsoluteFrequency += 1;

                    // Printing value for the corresponding key
                    dates.replace(month,date_temp);
                }
                else {
                    dates.put(month,new FrequencyTableRow( month, 1, 0 ));
                }
            }
        }

        if (dates.isEmpty()){
            IOException NoSuchElementsFound = new IOException("No elements found");
            throw NoSuchElementsFound;
        }
        CalculateRelative(this.dates);
    }

    private  void ConvertTableToSMapExercises() throws IOException{
        boolean exists = file.exists();

        if(!exists){
            throw new IOException("File not found.");
        }

        ResetStatistics();

        FileInputStream fis = new FileInputStream(file);
        HSSFWorkbook wb = new HSSFWorkbook(fis);
        HSSFSheet sheet = wb.getSheetAt(0);



        for (Row row : sheet) {
            if(row.getCell(1).toString().contains("Assignment: Качване на Упр.") && row.getCell(2).toString().contains("File submissions")){

                if (exercises.containsKey(row.getCell(1).toString())) {

                    // Mapping
                    FrequencyTableRow ex_temp = exercises.get(row.getCell(1).toString());
                    ex_temp.AbsoluteFrequency += 1;
                    // Printing value for the corresponding key
                    exercises.replace(row.getCell(1).toString(),ex_temp);
                }
                else {
                    exercises.put(row.getCell(1).toString(),new FrequencyTableRow( row.getCell(1).toString(), 1, 0 ));
                }
            }
        }

        if (exercises.isEmpty()){
            throw new IOException("No elements found");
        }
        CalculateRelative(this.exercises);
    }

    public void CalculateRelative(SortedMap<String, FrequencyTableRow> info){
        info = RelativeCalculator.CalculateRelative(info);

    }


//    public  void showExercisesFrequencyTable() throws IOException {
//        // File file= new File(".\\src\\com\\codingStanislava\\Logs_Course A_StudentsActivities.xls");
//        ConvertTableToSMapExercises();
//
//        PrintDataToConsole(exercises);
//    }
//
//    public  void showMonthsFrequencyTable() throws IOException {
//        // File file= new File(".\\src\\com\\codingStanislava\\Logs_Course A_StudentsActivities.xls");
//        ConvertTableToSMapDates();
//
//        PrintDataToConsole(dates);
//    }
//    private void PrintDataToConsole(SortedMap<String, FrequencyTableRow> exercises) {
//        Set s = exercises.entrySet();
//        Iterator i = s.iterator();
//        System.out.format("%-34s","Key  ");
//        System.out.format("%21s","Absolute frequency  ");
//        System.out.println(" Relative frequency");
//        while (i.hasNext()) {
//            Map.Entry m = (Map.Entry)i.next();
//            FrequencyTableRow temp =  (FrequencyTableRow)m.getValue();
//            String key = (String)m.getKey();
//            int absolute = temp.AbsoluteFrequency;
//            float relative = temp.RelativeFrequency;
//            System.out.format("%-35s",key);
//            System.out.format("%-21d",absolute/2); // Divide by 2 because there are 2 messages in the system for every file upload
//            System.out.format("%6f",relative);
//            System.out.println();
//        }
//    }




}
