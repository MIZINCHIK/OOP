package io.github.mizinchik.PizzaJoint.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Serialization of Lists of anything int JSON.
 *
 * @author MIZINCHIK
 */
public class JsonWriter {
    /**
     * Serializes the list given into JSON.
     *
     * @param list of any Objects
     * @param fileName where to write JSON
     */
    public static void write(List<?> list, String fileName) {
        var file = new File(fileName);
        try (var writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
