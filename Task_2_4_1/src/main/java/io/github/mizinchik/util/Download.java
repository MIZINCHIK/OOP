package io.github.mizinchik.util;

import java.io.IOException;

/**
 * Repository cloning tools.
 */
public class Download {
    public static final String labs = "src/main/resources/labs/";


    /**
     * Clones a repository.
     *
     * @param repo to clone
     * @param folder where to put in labDir
     * @param branch to clone
     * @return true if successful
     */
    public static boolean download(String repo, String folder, String branch) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("git",
                    "clone", "-b", branch, repo, labs + folder);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
