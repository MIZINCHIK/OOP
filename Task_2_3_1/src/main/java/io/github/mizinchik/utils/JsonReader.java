package io.github.mizinchik.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;

public class JsonReader {
    private static final String levels = "src/main/resources/io/github/mizinchik/levels/levels.json";
    public static Settings readLevel(int levelId) throws IOException {
        File fileSettings = new File(levels);
        if (fileSettings.exists()) {
            var reader = new BufferedReader(new FileReader(fileSettings));
            Type type = new TypeToken<Settings[]>() {}.getType();
            Gson gson = new GsonBuilder().create();
            Settings[] array = gson.fromJson(reader, type);
            reader.close();
            return array[levelId - 1];
        } else {
            throw new FileNotFoundException();
        }
    }
}
