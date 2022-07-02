package com.codingStanislava;
import com.codingStanislava.FrequencyTable;
import com.codingStanislava.FrequencyTableRow;
import com.codingStanislava.RelativeCalculator;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.IOException;
import java.util.*;

public class FrequencyTableTest {

    FrequencyTable frequencyTable;
    boolean fileNoExist = true;
    boolean fileNotCorrect = false;
    boolean getDates = false;
    boolean getExercises = false;
    boolean testRelativeCalculations = false;
    @Test
    public void FrequencytableFullTest() throws IOException {
        if (fileNoExist){
            testGivingNonExistentFile();}
        if (fileNotCorrect) {
            testGivingFileWithoutCorrectRows();}
        if (getDates) {
            testCalculationsOfMonthsData();
        }
        if (getExercises) {
            testCalculationsOfExercisesData();
        }
        if (testRelativeCalculations) {
            areRelativeCalculationsCorrect();
        }
    }

    public void testGivingNonExistentFile()  {
        frequencyTable = new FrequencyTable(".\\aa.txt");

        Exception exception = assertThrows(IOException.class, () -> {
            frequencyTable.getDates();
        });

        String expectedMessage = "File not found.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


        Exception exception2 = assertThrows(IOException.class, () -> {
            frequencyTable.getExercises();
        });

        String expectedMessage2 = "File not found.";
        String actualMessage2 = exception2.getMessage();
        assertTrue(actualMessage2.contains(expectedMessage2));


    }


    public void testGivingFileWithoutCorrectRows()  {
        frequencyTable = new FrequencyTable(".\\src\\com\\codingStanislava\\Course A_StudentsResults_Year 1.xls");

        Exception exception = assertThrows(IOException.class, () -> {
            frequencyTable.getDates();
        });

        String expectedMessage = "No elements found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        Exception exception2 = assertThrows(IOException.class, () -> {
            frequencyTable.getExercises();
        });

        String expectedMessage2 = "No elements found";
        String actualMessage2 = exception2.getMessage();
        assertTrue(actualMessage2.contains(expectedMessage2));


    }


    public void areRelativeCalculationsCorrect()  {
        //------------------------------INITIAL DATA---------------------------------------------------
        SortedMap<String, FrequencyTableRow> testTable = new TreeMap<>();
        testTable.put("Anabale",new FrequencyTableRow("Anabale", 10, 0));
        testTable.put("Nun",new FrequencyTableRow("Nun", 7, 0));
        testTable.put("Devil",new FrequencyTableRow("Devil", 3, 0));

        //------------------------------EXPECTED DATA---------------------------------------------------

        SortedMap<String, FrequencyTableRow> expectedTable = new TreeMap<>();
        expectedTable.put("Anabale",new FrequencyTableRow("Anabale", 10, 0.5f));
        expectedTable.put("Nun",new FrequencyTableRow("Nun", 7, 0.35f));
        expectedTable.put("Devil",new FrequencyTableRow("Devil", 3, 0.15f));
        List<String> expectedTableList = new ArrayList<>(expectedTable.keySet());

        //------------------------------Actual  DATA---------------------------------------------------

        SortedMap<String, FrequencyTableRow> finalTable = RelativeCalculator.CalculateRelative(testTable);
        List<String> finalTableList = new ArrayList<>(finalTable.keySet());

        assertEquals(finalTableList.size(),expectedTableList.size());

        for (int i = 0; i < expectedTableList.size(); i++ )
        {
            FrequencyTableRow expectedRow = expectedTable.get(expectedTableList.get(i));
            FrequencyTableRow actualRow = finalTable.get(expectedTableList.get(i));
//            System.out.println(expectedRow.AbsoluteFrequency);
//            System.out.println(actualRow.AbsoluteFrequency);
            assertEquals(expectedRow, actualRow);
        }

    }


    public void testCalculationsOfExercisesData() throws IOException {
        frequencyTable = new FrequencyTable(".\\src\\com\\codingStanislava\\Logs_Course A_StudentsActivities.xls");
        SortedMap<String, FrequencyTableRow> actualData = frequencyTable.getExercises();
        List<String> actualDataList = new ArrayList<>(actualData.keySet());

        //------------------------------EXPECTED DATA---------------------------------------------------

        SortedMap<String, FrequencyTableRow> expectedTable = new TreeMap<>();
        expectedTable.put("Assignment: Качване на Упр. 1",new FrequencyTableRow("Assignment: Качване на Упр. 1", 90*2, 0.23746702f));
        expectedTable.put("Assignment: Качване на Упр. 2",new FrequencyTableRow("Assignment: Качване на Упр. 2", 97*2, 0.25593668f));
        expectedTable.put("Assignment: Качване на Упр. 3",new FrequencyTableRow("Assignment: Качване на Упр. 3", 89*2, 0.2348285f));
        expectedTable.put("Assignment: Качване на Упр. 4",new FrequencyTableRow("Assignment: Качване на Упр. 4", 62*2, 0.16358839f));
        expectedTable.put("Assignment: Качване на Упр. 5",new FrequencyTableRow("Assignment: Качване на Упр. 5", 41*2, 0.10817942f));
        List<String> expectedTableList = new ArrayList<>(expectedTable.keySet());
        //System.out.println(expectedTableList);

        assertEquals(actualDataList.size(),expectedTableList.size());


        for (int i = 0; i < expectedTableList.size(); i++ )
        {
            FrequencyTableRow expectedRow = expectedTable.get(expectedTableList.get(i));
            FrequencyTableRow actualRow = actualData.get(expectedTableList.get(i));
//            System.out.println(expectedRow.AbsoluteFrequency);
//            System.out.println(actualRow.AbsoluteFrequency);
            assertEquals(expectedRow, actualRow);
        }

    }


    public void testCalculationsOfMonthsData() throws IOException {
        frequencyTable = new FrequencyTable(".\\src\\com\\codingStanislava\\Logs_Course A_StudentsActivities.xls");
        SortedMap<String, FrequencyTableRow> actualTable = frequencyTable.getDates();
        List<String> actualTableList = new ArrayList<>(actualTable.keySet());

        //------------------------------EXPECTED DATA---------------------------------------------------

        SortedMap<String, FrequencyTableRow> expectedTable = new TreeMap<>();
        expectedTable.put("01",new FrequencyTableRow("01", 50*2, 0.13192612f));
        expectedTable.put("02",new FrequencyTableRow("02", 6*2, 0.015831135f));
        expectedTable.put("11",new FrequencyTableRow("11", 183*2, 0.4828496f));
        expectedTable.put("12",new FrequencyTableRow("12", 140*2, 0.36939314f));
        List<String> expectedTableList = new ArrayList<>(expectedTable.keySet());
        //System.out.println(expectedTableList);

        assertEquals(actualTableList,expectedTableList);

        for (int i = 0; i < expectedTableList.size(); i++ )
        {
            FrequencyTableRow expectedRow = expectedTable.get(expectedTableList.get(i));
            FrequencyTableRow actualRow = actualTable.get(expectedTableList.get(i));

            assertEquals(expectedRow, actualRow);
        }
    }
}


