package com.alliedtesting;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class parseCommonCSV {

    private static final String SAMPLE_CSV_FILE_PATH = "students.csv";

    public static void main(String[] args) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader("ID", "Name", "University", "Faculty")
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing values by the names assigned to each column
                String id = csvRecord.get("ID");
                String name = csvRecord.get("Name");
                String univ = csvRecord.get("University");
                String faculty = csvRecord.get("Faculty");

               // System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("ID : " + id);
                System.out.println("Name : " + name);
                System.out.println("University : " + univ);
                System.out.println("Faculty : " + faculty);
                System.out.println("---------------\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
