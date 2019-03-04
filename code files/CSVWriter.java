package com.alliedtesting;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class CSVWriter {
    private static final String SAMPLE_CSV_FILE = "./students.csv";

    public static void main(String[] args) throws IOException {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "Name", "University", "Faculty"));
        ) {
            csvPrinter.printRecord("1", "Ion Creange", "ULIM", "Literature");
            csvPrinter.printRecord("2", "Iosif Stalin", "MSU", "History");
            csvPrinter.printRecord("3", "Ivan Ivanov", "USM", "Physics");
            csvPrinter.printRecord(Arrays.asList("4", "Ilon Musk", "MIT", "Mathematics"));
            csvPrinter.printRecord("5", "Mihai Eminescu", "ABC", "Literature");
            csvPrinter.printRecord("6", "Vladimir Lenin", "MSU", "History");
            csvPrinter.printRecord("7", "Vasya Pupkin", "USM", "Law");
            csvPrinter.printRecord(Arrays.asList("8", "John Doe", "TIM", "Astrophysics"));

            csvPrinter.flush();
        }
    }
}
