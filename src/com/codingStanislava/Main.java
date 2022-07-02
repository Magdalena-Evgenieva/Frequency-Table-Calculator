package com.codingStanislava;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("1. Frequency table menu");
        ReadData data = new ReadData();
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice) {
            case 1:
                data.readDataLogs();
                break;
            case 2:
                data.readDataYear1();
                break;
            case 3:
                data.readDataYear2();
                break;
            case 4:
                data.iterateOverColumnsWiki();
                break;
            case 5:
                data.iterateOverColumnsAssignment();
                break;
            case 6:
                System.out.println("Exit");
                break;

        }
    }
}
