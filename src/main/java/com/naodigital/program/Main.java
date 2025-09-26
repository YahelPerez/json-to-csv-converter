package com.naodigital.program;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Main class to run the JSON to CSV conversion process.
 * This class serves as the entry point for the application.
 * @author Yahel Perez
 * @version 1.2 (Sprint 3)
 */
public class Main {
    /**
     * The main method that executes the program.
     * @param args Command line arguments. Expects up to three arguments:
     * args[0] = path to the input JSON file.
     * args[1] = path for the output CSV file.
     * args[2] = the delimiter character (e.g., ';').
     */
    public static void main(String[] args) {
        // --- PARAMETER CONFIGURATION ---
        String inputFile = "input.json";
        String outputFile = "output.csv";
        char delimiter = ',';

        if (args.length >= 1) inputFile = args[0];
        if (args.length >= 2) outputFile = args[1];
        if (args.length >= 3) delimiter = args[2].charAt(0);

        System.out.println("Using configuration: IN=" + inputFile + ", OUT=" + outputFile + ", DELIMITER='" + delimiter + "'");
        // --- END OF CONFIGURATION ---

        System.out.println("Starting JSON to CSV conversion process...");

        JsonReader reader = new JsonReader();
        DataTransformer transformer = new DataTransformer();
        CsvWriter writer = new CsvWriter();

        try {
            List<Map<String, Object>> rawData = reader.readJsonFile(inputFile);
            List<Map<String, Object>> transformedData = transformer.transform(rawData);

            if (transformedData != null && !transformedData.isEmpty()) {
                // THE FIX IS HERE: Pass the 'delimiter' variable as the third argument.
                writer.writeCsvFile(outputFile, transformedData, delimiter);
            } else {
                System.out.println("No data available to write after transformation.");
            }
        } catch (IOException e) {
            System.err.println("Error during file processing: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }

        System.out.println("Conversion process finished.");
    }
}