package io.github.mizinchik;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
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
        this.reader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
        zarray = new HashMap<>();
        indices = new ArrayList<>();
        buildZarray();
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
        try (var bufferedReader = new BufferedReader(reader)) {
            List<Integer> stringList = fillStringList();
            fillZarray(bufferedReader, stringList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Z-Function computing for a pattern.
     *
     * @return list of z-function values for a String
     */
    private List<Integer> fillStringList() {
        var stringList = new ArrayList<Integer>();
        int intLeftIndex = 0;
        int intRightIndex = 0;
        stringList.add(0, 0);
        for (int i = 1; i < substring.length(); i++) {
            if (i > intRightIndex) {
                intLeftIndex = i;
                intRightIndex = i;
                while (intRightIndex < substring.length()
                        && substring.charAt(intRightIndex - intLeftIndex)
                        == substring.charAt(intRightIndex)) {
                    intRightIndex++;
                }
                stringList.add(i, intRightIndex - intLeftIndex);
                intRightIndex--;
            } else {
                int insideIndex = i - intLeftIndex;
                if (stringList.get(insideIndex) < (intRightIndex - i + 1)) {
                    stringList.add(i, stringList.get(insideIndex));
                } else {
                    intLeftIndex = i;
                    while (intRightIndex < substring.length()
                            && substring.charAt(intRightIndex - intLeftIndex)
                            == substring.charAt(intRightIndex)) {
                        intRightIndex++;
                    }
                    stringList.add(i, intRightIndex - intLeftIndex);
                    intRightIndex--;
                }
            }
        }
        return stringList;
    }

    /**
     * Z-Function computing for a source.
     *
     * @param bufferedReader source
     * @param stringList pattern z-function values
     * @throws IOException if reader fails
     */
    private void fillZarray(BufferedReader bufferedReader, List<Integer> stringList)
            throws IOException {
        long longLeftIndex = -1;
        long longRightIndex = -1;
        long currentChar = bufferedReader.read();
        for (long i = 0; currentChar != -1; i++) {
            if (i > longRightIndex) {
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
                zarray.put(i, (int) (longRightIndex - longLeftIndex));
                longRightIndex--;
                for (Map.Entry<Long, Integer> entry : zarray.entrySet()) {
                    if (entry.getValue() == substring.length()) {
                        indices.add(entry.getKey());
                    }
                }
                zarray.clear();
            } else {
                int insideIndex = (int) (i - longLeftIndex);
                if (stringList.get(insideIndex) < (longRightIndex - i + 1)) {
                    zarray.put(i, stringList.get(insideIndex));
                } else {
                    longLeftIndex = i;
                    longRightIndex++;
                    while (longRightIndex - longLeftIndex < substring.length()
                            && substring.charAt((int) (longRightIndex - longLeftIndex))
                            == currentChar) {
                        currentChar = bufferedReader.read();
                        longRightIndex++;
                    }
                    zarray.put(i, (int) (longRightIndex - longLeftIndex));
                    longRightIndex--;
                }
                for (Map.Entry<Long, Integer> entry : zarray.entrySet()) {
                    if (entry.getValue() == substring.length()) {
                        indices.add(entry.getKey());
                    }
                }
                zarray.clear();
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
        this.indices = new ArrayList<>();
        buildZarray();
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