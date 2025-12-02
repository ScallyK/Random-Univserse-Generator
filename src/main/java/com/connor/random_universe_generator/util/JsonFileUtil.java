package com.connor.random_universe_generator.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    // Serializes an object to a JSON file
    public static <T> void writeToFile(T object, String filename) {

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), object);
            System.out.println("Saved output to " + filename);
        } 

        catch (IOException e) {
            System.err.println("Failed to write JSON to file: " + filename);
        }
    }
}
