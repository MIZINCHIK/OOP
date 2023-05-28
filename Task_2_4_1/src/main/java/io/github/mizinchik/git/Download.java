package io.github.mizinchik.git;

import java.io.IOException;

public class Download {
    public static boolean download(String repo) {
        String labs = "src/main/resources/labs";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("git", "clone", repo, labs);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
