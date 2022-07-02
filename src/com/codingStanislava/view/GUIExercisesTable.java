package com.codingStanislava.view;


import com.codingStanislava.FrequencyTable;
import com.codingStanislava.FrequencyTableRow;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.*;

public class GUIExercisesTable extends JPanel {
    private boolean DEBUG = false;
    private Vector<Object[]> data = new Vector<>();
    private FrequencyTable frequencyTable = new FrequencyTable(".\\src\\com\\codingStanislava\\Logs_Course A_StudentsActivities.xls");

    public GUIExercisesTable() throws IOException {
        super(new GridLayout(1,0));
        FillDataE();
        int len = data.size();
        String[] columnNames = {"Name",
                "Absolute Frequency ",
                "Relative Frequency"};


        Object[][] objArray = new Object[len][3];
        for (int i =0; i < len; i++)
        {
            objArray[i] = data.elementAt(i);
        }


        final JTable table = new JTable(objArray, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 150));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }



    private void FillDataE() throws IOException {
        SortedMap<String, FrequencyTableRow> temp_data = frequencyTable.getExercises();
        Set s = temp_data.entrySet();
        Iterator i = s.iterator();
        //System.out.println("Test");
        while (i.hasNext()) {

            Map.Entry m = (Map.Entry)i.next();
            FrequencyTableRow temp_row =  (FrequencyTableRow)m.getValue();
            String key = (String)m.getKey();
            //System.out.println("Test2 | " + key + " | " + temp_row.AbsoluteFrequency + " | " + temp_row.RelativeFrequency);
            data.add(new Object[]{key, temp_row.AbsoluteFrequency/2, temp_row.RelativeFrequency});
        }

    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    private static void createAndShowGUI() throws IOException {
        //Create and set up the window.
        JFrame frame = new JFrame("FrequencyTableExercises");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        GUIExercisesTable newContentPane = new GUIExercisesTable();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
