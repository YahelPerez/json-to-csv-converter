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

public class JsonReader {
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