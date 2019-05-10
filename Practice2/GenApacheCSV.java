package Services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GenApacheCSV {
    public static void main(String[] args) {
        try(
                BufferedWriter writer = Files.newBufferedWriter(Paths.get("dates2.csv"));
                //CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Date"));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

        ) {
            csvPrinter.printRecord( "16.02.1999");
            csvPrinter.printRecord( "13.04.2013");
            csvPrinter.printRecord( "28.09.2018");
            csvPrinter.printRecord( "9.05.2019");

            csvPrinter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
