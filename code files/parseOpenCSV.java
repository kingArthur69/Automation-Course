package com.alliedtesting;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class parseOpenCSV {

    public static void main(String[] args) {

        String csvFile = "students.csv";

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println( line[0] + "\t" +  line[1] + "\t"  + line[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
