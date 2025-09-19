package com.naodigital.program;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The main entry point for the application. This class coordinates the process
 * of reading from a JSON file and writing to a CSV file.
 */
public class Main {
    /**
     * The main method that is executed when the program runs.
     * @param args Command line arguments (not used in this project).
     */
    public static void main(String[] args) {
        // Define the file names for the input JSON and output CSV.
        String inputFile = "input.json";
        String outputFile = "output.csv";

        System.out.println("Starting JSON to CSV conversion process...");

        // Create instances (objects) of our reader and writer classes.
        JsonReader reader = new JsonReader();
        CsvWriter writer = new CsvWriter();

        // Use a "try-catch" block to handle potential errors during the file operations.
        try {
            // Step 1: Call the reader to parse the JSON file. The result is stored in 'data'.
            List<Map<String, Object>> data = reader.readJsonFile(inputFile);

            // Step 2: Check if the reader returned valid data before trying to write.
            if (data != null && !data.isEmpty()) {
                // Step 3: Call the writer to write the data to the CSV file.
                writer.writeCsvFile(outputFile, data);
            } else {
                System.out.println("JSON file was empty or could not be read.");
            }

        } catch (IOException e) {
            // This 'catch' block will execute if a file reading/writing error occurs.
            System.err.println("Error during file processing: " + e.getMessage());
        } catch (Exception e) {
            // This 'catch' block will handle any other unexpected errors (like bad JSON format).
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }

        System.out.println("Conversion process finished.");
    }
}