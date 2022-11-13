package io.github.mizinchik;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubstringFinderImpl implements SubstringFinder {
    private String substring;
    private Reader reader;
    private final Map<Long, Integer> zArray;

    public SubstringFinderImpl(String fileName, String substring) throws UnsupportedOperationException, FileNotFoundException {
        this.substring = substring;
        this.reader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
        zArray = new HashMap<>();
        buildZArray();
    }

    public SubstringFinderImpl(Reader reader, String substring) throws UnsupportedOperationException {
        this.substring = substring;
        this.reader = reader;
        zArray = new HashMap<>();
        buildZArray();
    }

    private void buildZArray() throws RuntimeException {
        if (reader == null || substring == null) {
            throw new UnsupportedOperationException("Can't build a Z array w/o a reader and/or a substring");
        } else {
            try (var bufferedReader = new BufferedReader(reader)) {
                var stringArray = new ArrayList<Integer>();
                int intLeftIndex = 0;
                int intRightIndex = 0;
                stringArray.add(0, 0);
                for (int i = 1; i < substring.length(); i++) {
                    if (i > intRightIndex) {
                        intLeftIndex = i;
                        intRightIndex = i;
                        while (intRightIndex < substring.length() &&
                                substring.charAt(intRightIndex - intLeftIndex) ==
                                        substring.charAt(intRightIndex)) {
                            intRightIndex++;
                        }
                        stringArray.add(i, intRightIndex - intLeftIndex);
                        intRightIndex--;
                    } else {
                        int insideIndex = i - intLeftIndex;
                        if (stringArray.get(insideIndex) < (intRightIndex - i + 1)) {
                            stringArray.add(i, stringArray.get(insideIndex));
                        } else {
                            intLeftIndex = i;
                            while(intRightIndex < substring.length() &&
                                    substring.charAt(intRightIndex - intLeftIndex) ==
                                            substring.charAt(intRightIndex)) {
                                intRightIndex++;
                            }
                            stringArray.add(i, intRightIndex - intLeftIndex);
                            intRightIndex--;
                        }
                    }
                }
                long longLeftIndex = -1;
                long longRightIndex = -1;
                long currentChar = bufferedReader.read();
                for (long i = 0; currentChar != -1; i++) {
                    if (i > longRightIndex) {
                        longLeftIndex = i;
                        longRightIndex = i;
                        boolean beenInCycle = false;
                        while (longRightIndex - longLeftIndex < substring.length() &&
                                substring.charAt((int)(longRightIndex - longLeftIndex)) ==
                                        currentChar) {
                            beenInCycle = true;
                            currentChar = bufferedReader.read();
                            longRightIndex++;
                        }
                        if (!beenInCycle) {
                            currentChar = bufferedReader.read();
                        }
                        zArray.put(i, (int) (longRightIndex - longLeftIndex));
                        longRightIndex--;
                        for (Map.Entry<Long, Integer> entry : zArray.entrySet()) {
                            if (entry.getValue() == substring.length()) {
                                System.out.print(entry.getKey() + " ");
                            }
                        }
                        zArray.clear();
                    } else {
                        int insideIndex = (int)(i - longLeftIndex);
                        if (stringArray.get(insideIndex) < (longRightIndex - i + 1)) {
                            zArray.put(i, stringArray.get(insideIndex));
                        } else {
                            longLeftIndex = i;
                            longRightIndex++;
                            while(longRightIndex - longLeftIndex < substring.length() &&
                                    substring.charAt((int)(longRightIndex - longLeftIndex)) ==
                                            currentChar) {
                                currentChar = bufferedReader.read();
                                longRightIndex++;
                            }
                            zArray.put(i, (int)(longRightIndex - longLeftIndex));
                            longRightIndex--;
                        }
                        for (Map.Entry<Long, Integer> entry : zArray.entrySet()) {
                            if (entry.getValue() == substring.length()) {
                                System.out.print(entry.getKey() + " ");
                            }
                        }
                        zArray.clear();
                    }
                }
                System.out.print("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void eatReader(Reader reader) {
        this.reader = reader;
        buildZArray();
    }

    @Override
    public void eatReaderAndSubstring(Reader reader, String substring) {
        this.substring = substring;
        this.reader = reader;
        buildZArray();
    }

    @Override
    public void eatReaderAndSubstring(String fileName, String substring) throws FileNotFoundException {
        this.substring = substring;
        this.reader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
        buildZArray();
    }
}