package io.github.mizinchik.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Reads from a JSON file.
 *
 * @author MIZINCHIK
 */
public class JsonReader {
    private static final String levels =
            "src/main/resources/io/github/mizinchik/levels/levels.json";

    /**
     * Reads level settings from the file.
     *
     * @param levelId in a list in the file
     * @return level config
     * @throws IOException in case of problems with the file
     */
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
