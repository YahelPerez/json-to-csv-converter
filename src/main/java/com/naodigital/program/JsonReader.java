package com.naodigital.program;

// Import necessary libraries
import com.google.gson.Gson; // The main class from the Gson library for JSON processing.
import com.google.gson.JsonSyntaxException; // An exception for when the JSON format is wrong.
import com.google.gson.reflect.TypeToken; // Helps Gson understand complex data types like List<Map>.
import java.io.FileReader;
import java.io.IOException; // An exception for file reading errors (e.g., file not found).
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Handles reading and parsing JSON files. This class is responsible for taking a file path,
 * opening the file, and converting its JSON content into a Java data structure.
 */
public class JsonReader {

    /**
     * Reads a JSON file and parses it into a List of Maps.
     * @param filePath The path to the JSON file.
     * @return A List where each item is a Map representing a JSON object.
     * @throws IOException If the file cannot be found or read.
     * @throws JsonSyntaxException If the JSON is badly formatted.
     */
    public List<Map<String, Object>> readJsonFile(String filePath) throws IOException, JsonSyntaxException {
        // Create an instance of the Gson library.
        Gson gson = new Gson();
        // Define the specific, complex type (a List of Maps) that we expect from the JSON file.
        // This tells Gson how to structure the parsed data.
        Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();

        // Use a "try-with-resources" block to automatically close the file reader when done.
        try (Reader reader = new FileReader(filePath)) {
            // Use Gson to parse the file's content into our defined data structure.
            List<Map<String, Object>> data = gson.fromJson(reader, type);
            System.out.println("Successfully read and parsed " + filePath);
            // Return the parsed data.
            return data;
        }
    }
}