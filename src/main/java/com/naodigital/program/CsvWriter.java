package com.naodigital.program;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class CsvWriter {
    public void writeCsvFile(String filePath, List<Map<String, Object>> data) throws IOException {
        if (data == null || data.isEmpty()) {
            System.out.println("No data to write to CSV.");
            return;
        }
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader(data.get(0).keySet().toArray(new String[0])));
        ) {
            for (Map<String, Object> record : data) {
                csvPrinter.printRecord(record.values());
            }
            csvPrinter.flush();
            System.out.println("Successfully wrote data to " + filePath);
        }
    }
}
