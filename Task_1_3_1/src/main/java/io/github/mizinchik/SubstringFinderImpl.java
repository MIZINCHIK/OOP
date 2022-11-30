package io.github.mizinchik;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class providing a basic method
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
public class SubstringFinderImpl implements SubstringFinder {
    private String substring;
    private Reader reader;
    private final Map<Long, Integer> zarray;
    private final Map<Long, Integer> stringMap;
    private List<Long> indices;

    /**
     * Constructor.
     *
     * @param fileName source of text
     * @param substring pattern
     * @throws IllegalStateException if either reader of string are null
     * @throws FileNotFoundException if fileName is incorrect
     */
    public SubstringFinderImpl(String fileName, String substring)
            throws IllegalStateException, FileNotFoundException {
        this.substring = substring;
        try (var iReader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8)) {
            reader = iReader;
            zarray = new HashMap<>();
            stringMap = new HashMap<>();
            indices = new ArrayList<>();
            buildZarray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructor.
     *
     * @param reader source of text
     * @param substring pattern
     * @throws IllegalStateException if either reader of string are null
     */
    public SubstringFinderImpl(Reader reader, String substring)
            throws IllegalStateException {
        this.substring = substring;
        this.reader = reader;
        zarray = new HashMap<>();
        stringMap = new HashMap<>();
        indices = new ArrayList<>();
        buildZarray();
    }

    /**
     * Builds a map from indices of each occurrence of a pattern prefix in a reader
     * to their lengths. Prints them in operation.
     *
     * @throws IllegalStateException if substring or reader are null
     */
    private void buildZarray() throws IllegalStateException {
        if (reader == null || substring == null) {
            throw new IllegalStateException("Can't build a Z array w/o"
                    + " a reader and/or a substring");
        }
        try (var bufferedReader = new BufferedReader(reader);
            var stringReager = new BufferedReader(new StringReader(substring))) {
            fillZarray(stringReager, stringMap, true);
            fillZarray(bufferedReader, zarray, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Z-Function computing for a source.
     *
     * @param bufferedReader source
     * @throws IOException if reader fails
     */
    private void fillZarray(BufferedReader bufferedReader, Map<Long, Integer> mapToFill, boolean isString)
            throws IOException {
        long longLeftIndex = -1;
        long longRightIndex = -1;
        long currentChar = bufferedReader.read();
        for (long i = 0; currentChar != -1; i++) {
            if (isString && i == 0) {
                mapToFill.put(0L, 0);
            } else if (i > longRightIndex) {
                longLeftIndex = i;
                longRightIndex = i;
                boolean beenInCycle = false;
                while (longRightIndex - longLeftIndex < substring.length()
                        && substring.charAt((int) (longRightIndex - longLeftIndex))
                        == currentChar) {
                    beenInCycle = true;
                    currentChar = bufferedReader.read();
                    longRightIndex++;
                }
                if (!beenInCycle) {
                    currentChar = bufferedReader.read();
                }
                mapToFill.put(i, (int) (longRightIndex - longLeftIndex));
                longRightIndex--;
                if (!isString) {
                    for (Map.Entry<Long, Integer> entry : zarray.entrySet()) {
                        if (entry.getValue() == substring.length()) {
                            indices.add(entry.getKey());
                        }
                    }
                    mapToFill.clear();
                }
            } else {
                long insideIndex = (i - longLeftIndex);
                if (stringMap.get(insideIndex) < (longRightIndex - i + 1)) {
                    mapToFill.put(i, stringMap.get(insideIndex));
                } else {
                    longLeftIndex = i;
                    longRightIndex++;
                    while (longRightIndex - longLeftIndex < substring.length()
                            && substring.charAt((int) (longRightIndex - longLeftIndex))
                            == currentChar) {
                        currentChar = bufferedReader.read();
                        longRightIndex++;
                    }
                    mapToFill.put(i, (int) (longRightIndex - longLeftIndex));
                    longRightIndex--;
                }
                if (!isString) {
                    for (Map.Entry<Long, Integer> entry : zarray.entrySet()) {
                        if (entry.getValue() == substring.length()) {
                            indices.add(entry.getKey());
                        }
                    }
                    mapToFill.clear();
                }
            }
        }
    }

    /**
     * Finds all occurrences of an inner string
     * in the given reader.
     *
     * @param reader source of text
     */
    @Override
    public List<Long> eatReader(Reader reader) {
        this.reader = reader;
        try (var bufferedReader = new BufferedReader(reader);) {
            zarray.clear();
            fillZarray(bufferedReader, zarray, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return indices;
    }

    /**
     * Finds all occurrences of the pattern
     * in the source.
     *
     * @param reader source of text
     * @param substring pattern to find
     */
    @Override
    public List<Long> eatReaderAndSubstring(Reader reader, String substring) {
        this.substring = substring;
        this.reader = reader;
        this.indices = new ArrayList<>();
        buildZarray();
        return indices;
    }


    /**
     * Finds all occurrences of the pattern
     * in the source.
     *
     * @param fileName source of text in UTF-8
     * @param substring pattern to find
     * @throws FileNotFoundException if fileName is incorrect
     */
    @Override
    public List<Long> eatReaderAndSubstring(String fileName, String substring)
            throws FileNotFoundException {
        this.substring = substring;
        this.reader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
        this.indices = new ArrayList<>();
        buildZarray();
        return indices;
    }

    /**
     * Returns the List of occurrences of a substring.
     *
     * @return indices of a substring occurrences
     */
    @Override
    public List<Long> getIndices() {
        return indices;
    }
}