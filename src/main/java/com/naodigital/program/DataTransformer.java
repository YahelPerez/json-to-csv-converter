package com.naodigital.program;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles the transformation of raw JSON data into a structured format suitable for CSV export.
 * This class defines the mapping logic from JSON fields to CSV columns.
 * @author Yahel Perez
 * @version 1.0 (Sprint 3)
 */
public class DataTransformer {

    /**
     * Transforms a list of generic data maps into a structured list where each map
     * contains specific keys in a defined order.
     *
     * This method implements the core mapping logic.
     *
     * @param rawData The raw list of data read from the JSON file.
     * @return A new list of maps, structured and ordered for the CSV file.
     */
    public List<Map<String, Object>> transform(List<Map<String, Object>> rawData) {
        if (rawData == null || rawData.isEmpty()) {
            return new ArrayList<>(); // Return an empty list if there's no data
        }

        // This will be our new list with the transformed data.
        List<Map<String, Object>> transformedData = new ArrayList<>();

        // Loop through each record (JSON object) from the original data.
        for (Map<String, Object> originalRecord : rawData) {
            // Use a LinkedHashMap to GUARANTEE the order of the columns in the CSV.
            Map<String, Object> transformedRecord = new LinkedHashMap<>();

            // --- TRANSFORMATION ALGORITHM ---
            // THE FIX IS HERE: Changed "publicationID" to "publicationId" to match the JSON file.
            transformedRecord.put("ID", originalRecord.get("publicationId"));
            transformedRecord.put("Publication Title", originalRecord.get("title"));
            transformedRecord.put("Author Name", originalRecord.get("author"));
            transformedRecord.put("Year", originalRecord.get("year"));
            transformedRecord.put("Journal", originalRecord.get("journal"));

            // Add the newly structured record to our list.
            transformedData.add(transformedRecord);
        }

        System.out.println("Data transformation complete.");
        return transformedData;
    }
}