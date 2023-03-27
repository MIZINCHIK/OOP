package io.github.mizinchik.PizzaJoint.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.mizinchik.PizzaJoint.Pizza;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserialization of Pizza orders from JSON into List of Pizzas.
 *
 * @author MIZINCHIK
 */
public class JsonReader {
    public static String pizzas = "pizza.json";

    /**
     * Deserializes orders from the file which name is
     * defined in the static variable.
     *
     * @return list of Pizza orders
     * @throws Exception if file not found or has incorrect format or if reader throws something
     */
    public static List<Pizza> readOrders() throws Exception {
        var filePizzas = new File(pizzas);
        if (filePizzas.exists()) {
            try (var reader = new BufferedReader(new FileReader(filePizzas))) {
                Type type = new TypeToken<List<Pizza>>(){}.getType();
                Gson gson = new GsonBuilder().create();
                ArrayList<Pizza> list = gson.fromJson(reader, type);
                if (list == null) {
                    throw new Exception("Incorrect json-file");
                }
                return list;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new FileNotFoundException();
        }
    }
}
