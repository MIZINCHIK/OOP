package io.github.mizinchik;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for a SubstringFinderImpl java class.
 *
 * @author MIZINCHIK
 */
public class SubstringFinderTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Changes standard output stream before each method.
     */
    @BeforeEach
    public void setUpOut() {
        System.setOut(new PrintStream(out));
    }

    /**
     * Restores standard output system after each method.
     */
    @AfterEach
    public void restoreOut() {
        System.setOut(originalOut);
    }

    /**
     * Runs tests in a file with a very common and overlapping pattern.
     *
     * @throws FileNotFoundException if file name is incorrect
     */
    @Test
    @DisplayName("Common pattern test")
    void testCommon() throws FileNotFoundException {
        var finder = new SubstringFinderImpl("./src/main/resources/CommonTest.txt", "aabaa");
        List<Long> result = finder.getIndices();
        Long[] array = {0L, 3L, 6L, 9L, 12L, 15L, 18L, 21L, 24L};
        List<Long> reference = Arrays.asList(array);
        assertIterableEquals(reference, result);
        result = finder.eatReaderAndSubstring("./src/main/resources/CommonTest.txt", "aab");
        array = new Long[]{0L, 3L, 6L, 9L, 12L, 15L, 18L, 21L, 24L, 27L};
        reference = Arrays.asList(array);
        assertIterableEquals(reference, result);
    }

    /**
     * Runs tests for minor methods.
     *
     * @throws FileNotFoundException if file name is incorrect
     */
    @Test
    @DisplayName("Minor methods test")
    void testMinor() throws FileNotFoundException {
        var reader = new InputStreamReader(
                new FileInputStream("./src/main/resources/RandomTest.txt"),
                StandardCharsets.UTF_8);
        var finder = new SubstringFinderImpl(reader, "bet");
        List<Long> result = finder.getIndices();
        Long[] array = {98L, 12349L, 16565L};
        List<Long> reference = Arrays.asList(array);
        assertIterableEquals(reference, result);
        reader = new InputStreamReader(new FileInputStream("./src/main/resources/RandomTest.txt"),
                StandardCharsets.UTF_8);
        result = finder.eatReaderAndSubstring(reader, "sdasd");
        reference = new ArrayList<>();
        assertIterableEquals(reference, result);
        reader = new InputStreamReader(new FileInputStream("./src/main/resources/RandomTest.txt"),
                StandardCharsets.UTF_8);
        result = finder.eatReader(reader);
        reference = new ArrayList<>();
        assertIterableEquals(reference, result);
        reader = new InputStreamReader(new FileInputStream("./src/main/resources/PieTest.txt"),
                StandardCharsets.UTF_8);
        assertThrows(IllegalStateException.class,
                () -> finder.eatReaderAndSubstring((Reader) null, "a"));
        result = finder.eatReaderAndSubstring(reader, "");
        array = new Long[]{0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L};
        reference = Arrays.asList(array);
        assertIterableEquals(reference, result);
    }

    /**
     * Runs test with a 16GB file.
     */
    @Test
    @DisplayName("Large file test")
    public void testLargeFile() {
        File file = new File("./src/main/resources/LargeTest.txt");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.setLength(100L);
            var result = new SubstringFinderImpl("./src/main/resources/LargeTest.txt", "sdads");
            var reference = new ArrayList<>();
            assertIterableEquals(reference, result.getIndices());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file.deleteOnExit();
    }
}