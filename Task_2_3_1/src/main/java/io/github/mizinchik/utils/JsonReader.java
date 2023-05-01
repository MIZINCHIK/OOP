package io.github.mizinchik.utils;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

public class JsonReader {
    private static final String levels = "levels.json";
    public static Settings readLevel(int levelId) {
        File fileSettings = new File(levels);
        if (fileSettings.exists()) {
            Type type = new TypeToken<List<Settings>>(){}.getType();
            Gson gson = new GsonBuilder().create();
        }
    }
}
