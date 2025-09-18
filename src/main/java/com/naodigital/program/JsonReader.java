package com.naodigital.program;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Handles reading and parsing JSON files.
 * This class uses the Gson library to convert a JSON file's content
 * into a generic Java data structure.
 * @author Yahel Perez
 * @version 1.0 (Sprint 2)
 */
public class JsonReader {

    /**
     * Reads a JSON file containing an array of objects and parses it into a List of Maps.
     * @param filePath The path to the JSON file to be read.
     * @return A List of Maps, where each map represents a JSON object.
     * @throws IOException if there is an error reading the file (e.g., file not found).
     * @throws JsonSyntaxException if the file content is not valid JSON.
     */
    public List<Map<String, Object>> readJsonFile(String filePath) throws IOException, JsonSyntaxException {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();
        try (Reader reader = new FileReader(filePath)) {
            List<Map<String, Object>> data = gson.fromJson(reader, type);
            System.out.println("Successfully read and parsed " + filePath);
            return data;
        }
    }
}