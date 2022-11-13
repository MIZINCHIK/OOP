package io.github.mizinchik;

import java.io.FileNotFoundException;
import java.io.Reader;

public interface SubstringFinder {
    void eatReader(Reader reader);

    void eatReaderAndSubstring(Reader reader, String substring);

    void eatReaderAndSubstring(String fileName, String substring) throws FileNotFoundException;
}
