package io.github.mizinchik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SubstringFinderImpl implements SubstringFinder {
    private String filePath;
    private String substring;
    private ArrayList<Integer> zArray;

    public void SubstringFinder(String fileName, String substring) throws UnsupportedOperationException {
        this.filePath = fileName;
        this.substring = substring;
        buildZArray();
    }

    private void buildZArray() throws UnsupportedOperationException {
        if (filePath == null || substring == null) {
            throw new UnsupportedOperationException("Can't build a Z array w/o a file and/or a substring");
        }
        else {
            try (var bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(this.filePath), StandardCharsets.UTF_8))) {
                zArray = new ArrayList<>();
                int currentLeftIndex = 0;
                int currentRightIndex = 0;
                char currentChar;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void eatFile(String fileName) throws IllegalArgumentException {
        if (fileName == null) {
            throw new IllegalArgumentException("null parsed as a file name");
        }
        else {
            this.filePath = fileName;
            if (this.substring != null) {
                buildZArray();
            }
        }
    }

    @Override
    public void eatSubstring(String substring) {
        if (substring == null) {
            throw new IllegalArgumentException("null parsed as a string name");
        }
        else {
            this.substring = substring;
            if (this.filePath != null) {
                buildZArray();
            }
        }
    }

    @Override
    public void eatFileAndSubstring(String fileName, String substring) {
        if (fileName == null) {
            throw new IllegalArgumentException("null parsed as a file name");
        }
        else if (substring == null) {
            throw new IllegalArgumentException("null parsed as a string name");
        }
        else {
            this.substring = substring;
            this.filePath = fileName;
            buildZArray();
        }
    }

    @Override
    public void printIndices() {

    }
}