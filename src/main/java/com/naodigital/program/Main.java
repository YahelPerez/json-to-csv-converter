package com.naodigital.program;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String inputFile = "input.json";
        String outputFile = "output.csv";

        System.out.println("Starting JSON to CSV conversion process...");
        JsonReader reader = new JsonReader();
        CsvWriter writer = new CsvWriter();

        try {
            List<Map<String, Object>> data = reader.readJsonFile(inputFile);
            if (data != null && !data.isEmpty()) {
                writer.writeCsvFile(outputFile, data);
            } else {
                System.out.println("JSON file was empty or could not be read.");
            }
        } catch (IOException e) {
            System.err.println("Error during file processing: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
        System.out.println("Conversion process finished.");
    }
}