package Services;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NetClientGet {

    private static final String CSV_FILE_PATH = "dates2.csv";

    public static void main(String[] args) {

        try {

            Workbook wb = new HSSFWorkbook();
            OutputStream fileOut = new FileOutputStream("valBook.xls");

            ValCurs valCurs;
            XStream xstream = new XStream();
            xstream.processAnnotations(ValCurs.class);
            xstream.processAnnotations(Valute.class);

            Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

            for(CSVRecord csvRecord : csvParser) {

                int i = 1;
                String date = csvRecord.get(0);

                URL url = new URL("https://bnm.md/en/official_exchange_rates?date="+ date);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                //conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                valCurs = (ValCurs) xstream.fromXML(br);
                //System.out.println(valCurs);
                Sheet sheet1 = wb.createSheet(date);
                Row row = sheet1.createRow(0);
                row.createCell(0).setCellValue("Valute");
                row.createCell(1).setCellValue("Value");

                for(Valute curVal: valCurs.getValutes()) {
                    Row row2 = sheet1.createRow(i);
                    row2.createCell(0).setCellValue(curVal.getName());
                    row2.createCell(1).setCellValue(curVal.getValue());
                    i++;
                }
                conn.disconnect();
            }
            wb.write(fileOut);

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
