package com.naodigital.program;

import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a unit test class for the JsonReader class.
 * Its purpose is to test the functionality of JsonReader in isolation.
 */
class JsonReaderTest {

    private File tempFile; // A temporary file used for tests.
    private JsonReader reader; // The instance of the class we are testing.

    /**
     * This method runs BEFORE each test. It sets up the environment.
     */
    @BeforeEach
    void setUp() {
        reader = new JsonReader();
    }

    /**
     * This method runs AFTER each test. It cleans up resources.
     */
    @AfterEach
    void tearDown() {
        // Delete the temporary file after each test to keep things clean.
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    /**
     * Test case 1: The "Happy Path".
     * This test checks if the method works correctly with valid JSON.
     */
    @Test
    void readJsonFile_shouldParseCorrectly_whenJsonIsValid() throws IOException {
        // Arrange: Set up the test conditions. Create a temporary file with known, valid JSON.
        tempFile = File.createTempFile("test_valid", ".json");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("[{\"id\": 1.0, \"name\": \"Test Product\"}]");
        }

        // Act: Call the method we want to test.
        List<Map<String, Object>> result = reader.readJsonFile(tempFile.getPath());

        // Assert: Check if the result is what we expected.
        assertNotNull(result); // The result should not be null.
        assertEquals(1, result.size()); // The list should contain one item.
        assertEquals(1.0, result.get(0).get("id")); // The 'id' should be 1.0.
        assertEquals("Test Product", result.get(0).get("name")); // The 'name' should be "Test Product".
    }

    /**
     * Test case 2: File Not Found.
     * This test ensures the method throws the correct exception when the file doesn't exist.
     */
    @Test
    void readJsonFile_shouldThrowIOException_whenFileDoesNotExist() {
        // Arrange: Define a path to a file that we know does not exist.
        String nonExistentFilePath = "non_existent_file.json";

        // Act & Assert: Verify that calling the method with this path throws an IOException.
        assertThrows(IOException.class, () -> reader.readJsonFile(nonExistentFilePath));
    }

    /**
     * Test case 3: Malformed JSON.
     * This test ensures the method throws the correct exception for a badly formatted JSON file.
     */
    @Test
    void readJsonFile_shouldThrowJsonSyntaxException_whenJsonIsMalformed() throws IOException {
        // Arrange: Create a temporary file with invalid JSON (an extra comma).
        tempFile = File.createTempFile("test_malformed", ".json");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("[{\"id\": 1,}]");
        }

        // Act & Assert: Verify that calling the method with this file throws a JsonSyntaxException.
        assertThrows(JsonSyntaxException.class, () -> reader.readJsonFile(tempFile.getPath()));
    }
}