package io.github.mizinchik;

public interface SubstringFinder {
    void eatFile(String filePath);

    void eatSubstring(String substring);

    void eatFileAndSubstring(String filePath, String substring);

    void printIndices();
}
