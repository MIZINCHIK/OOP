package io.github.mizinchik.util;

import java.io.IOException;

public class Download {
    public static boolean download(String repo, String folder) {
        String labs = "src/main/resources/labs/";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("git", "clone", repo, labs + folder);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
