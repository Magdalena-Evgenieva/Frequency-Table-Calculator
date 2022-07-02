package com.codingStanislava.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUIFrequencyTable {
    boolean isExercisesWindowsOpen = false;
    boolean isDatesWindowsOpen = false;
    public void createAndShowGUI() {


        JFrame frame = new JFrame("Frequency Table Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel(); // the panel is not visible in output
        JButton exercises_button = new JButton("Show Frequency Table based on exercises");
        JButton months_button = new JButton("Show Frequency Table based on months");

        exercises_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    if (!isExercisesWindowsOpen) {
                        GUIExercisesTable exercisesTable = new GUIExercisesTable();
                        exercisesTable.main(null);
                        isExercisesWindowsOpen = true;
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        months_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    if (!isDatesWindowsOpen) {
                        GUIDatesTable datesTable = new GUIDatesTable();
                        datesTable.main(null);
                        isDatesWindowsOpen = true;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        panel.add(exercises_button);
        panel.add(months_button);

        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        frame.getContentPane().add(exercises_button, c);
        c.gridx = 0;
        c.gridy = 1;
        frame.getContentPane().add( months_button, c);
        frame.setVisible(true);
    }
}

