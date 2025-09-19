package com.naodigital.program;

// Import necessary libraries
import org.apache.commons.csv.CSVFormat; // Defines the format of the CSV (e.g., comma delimiter).
import org.apache.commons.csv.CSVPrinter; // The main class for writing CSV records.
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Handles writing data to a CSV file. This class takes a Java data structure
 * and uses the Apache Commons CSV library to write it into a formatted CSV file.
 */
public class CsvWriter {

    /**
     * Writes a List of Maps to a CSV file.
     * @param filePath The path where the CSV file will be created.
     * @param data The data to write, where each Map represents a row.
     * @throws IOException If there is an error writing the file.
     */
    public void writeCsvFile(String filePath, List<Map<String, Object>> data) throws IOException {
        // First, check if there is any data to write. If not, exit the function.
        if (data == null || data.isEmpty()) {
            System.out.println("No data to write to CSV.");
            return;
        }

        // Use "try-with-resources" to automatically close the file writer and CSV printer.
        try (
                // Create a writer that can efficiently write text to a file.
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
                // Create a CSV printer using the writer. We configure it with a header row
                // by dynamically getting the keys from the first data record (the first Map).
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader(data.get(0).keySet().toArray(new String[0])));
        ) {
            // Loop through each Map (record) in our list of data.
            for (Map<String, Object> record : data) {
                // Write the values of the map as a single row in the CSV.
                csvPrinter.printRecord(record.values());
            }
            // Ensure all data in the buffer is written to the file.
            csvPrinter.flush();
            System.out.println("Successfully wrote data to " + filePath);
        }
    }
}