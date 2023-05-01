package io.github.mizinchik.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;

public class JsonReader {
    private static final String levels = "src/main/resources/io/github/mizinchik/levels/levels.json";
    public static Settings readLevel(int levelId) throws FileNotFoundException {
        File fileSettings = new File(levels);
        if (fileSettings.exists()) {
            try (var reader = new BufferedReader(new FileReader(fileSettings))) {
                Type type = new TypeToken<Settings[]>() {}.getType();
                Gson gson = new GsonBuilder().create();
                Settings[] array = gson.fromJson(reader, type);
                return array[levelId - 1];
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new FileNotFoundException();
        }
    }
}
