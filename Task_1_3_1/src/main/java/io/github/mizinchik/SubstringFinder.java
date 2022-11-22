package io.github.mizinchik;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.List;

/**
 * An interface describing a basic method
 * of finding all occurrences of a
 * given substring in a reader which
 * may be given directly or via a name
 * of a file.
 * Occurrences are represented as real indices
 * of characters in readers.
 * Files are decoded in UTF-8.
 *
 * @author MIZINCHIK
 */
public interface SubstringFinder {
    /**
     * Finds all occurrences of an inner string
     * in the given reader.
     *
     * @param reader source of text
     */
    List<Long> eatReader(Reader reader);

    /**
     * Finds all occurrences of the pattern
     * in the source.
     *
     * @param reader source of text
     * @param substring pattern to find
     */
    List<Long> eatReaderAndSubstring(Reader reader, String substring);

    /**
     * Finds all occurrences of the pattern
     * in the source.
     *
     * @param fileName source of text in UTF-8
     * @param substring pattern to find
     * @throws FileNotFoundException if fileName is incorrect
     */
    List<Long> eatReaderAndSubstring(String fileName, String substring)
            throws FileNotFoundException;

    /**
     * Returns the List of occurrences of a substring.
     *
     * @return indices of a substring occurrences
     */
    List<Long> getIndices();
}
