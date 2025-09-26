package com.naodigital.program;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handles writing data to a CSV file.
 * This class uses the Apache Commons CSV library to create a CSV file
 * from a List of Maps.
 * @author Yahel Perez
 * @version 1.3 (Sprint 3 - Final)
 */
public class CsvWriter {

    /**
     * Writes a list of data records (as Maps) to a specified CSV file.
     * @param filePath The path where the CSV file will be created.
     * @param data The list of data to write. Each element (Map) represents a row.
     * @param delimiter The character to use for separating values.
     * @throws IOException if there is an error writing to the file.
     */
    public void writeCsvFile(String filePath, List<Map<String, Object>> data, char delimiter) throws IOException {
        if (data == null || data.isEmpty()) {
            System.out.println("No data to write to CSV.");
            return;
        }

        // Get the headers from the first record. The order is guaranteed by the LinkedHashMap.
        String[] headers = data.get(0).keySet().toArray(new String[0]);

        // Create a CSV format with the specified delimiter and headers.
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setDelimiter(delimiter)
                .setHeader(headers)
                .build();

        // Use try-with-resources to automatically close the writer and printer.
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
                CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)
        ) {
            // Loop through each record (Map) in our data list.
            for (Map<String, Object> record : data) {
                // THE FIX: We create a list of values in the same order as the headers.
                List<Object> orderedValues = new ArrayList<>();
                for (String header : headers) {
                    orderedValues.add(record.get(header));
                }
                // Now, we pass the correctly ordered list of values to the printer.
                csvPrinter.printRecord(orderedValues);
            }
            csvPrinter.flush();
            System.out.println("Successfully wrote data to " + filePath);
        }
    }
}