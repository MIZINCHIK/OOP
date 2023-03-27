package io.github.mizinchik.Json;

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

public class JsonReader {
    public static String pizzas = "pizza.json";

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
