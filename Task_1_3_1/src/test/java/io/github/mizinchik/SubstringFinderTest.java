package io.github.mizinchik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
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
     * Runs a reference test.
     *
     * @throws FileNotFoundException if file name is incorrect
     */
    @Test
    @DisplayName("Reference test")
    void testReference() throws FileNotFoundException {
        String string = "\u043f\u0438\u0440\u043e\u0433";
        var finder = new SubstringFinderImpl("./src/main/resources/PieTest.txt", string);
        assertEquals("7 \n", out.toString());
        finder.eatReaderAndSubstring("./src/main/resources/PieTest.txt", "\u043e");
        assertEquals("7 \n3 10 \n", out.toString());
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
        assertEquals("0 3 6 9 12 15 18 21 24 \n", out.toString());
        finder.eatReaderAndSubstring("./src/main/resources/CommonTest.txt", "aab");
        assertEquals("0 3 6 9 12 15 18 21 24 \n0 3 6 9 12 15 18 21 24 27 \n", out.toString());
    }

    /**
     * Runs tests for minor methods.
     *
     * @throws FileNotFoundException if file name is incorrect
     */
    @Test
    @DisplayName("Minor methods test")
    void testMinor() throws FileNotFoundException {
        var reader = new InputStreamReader(new FileInputStream("./src/main/resources/RandomTest.txt"), StandardCharsets.UTF_8);
        var finder = new SubstringFinderImpl(reader, "bet");
        assertEquals("98 12349 16565 \n", out.toString());
        reader = new InputStreamReader(new FileInputStream("./src/main/resources/RandomTest.txt"), StandardCharsets.UTF_8);
        finder.eatReaderAndSubstring(reader, "sdasd");
        assertEquals("98 12349 16565 \n\n", out.toString());
        reader = new InputStreamReader(new FileInputStream("./src/main/resources/RandomTest.txt"), StandardCharsets.UTF_8);
        finder.eatReader(reader);
        assertEquals("98 12349 16565 \n\n\n", out.toString());
        reader = new InputStreamReader(new FileInputStream("./src/main/resources/PieTest.txt"), StandardCharsets.UTF_8);
        assertThrows(UnsupportedOperationException.class, () -> finder.eatReaderAndSubstring((Reader) null, "a"));
        finder.eatReaderAndSubstring(reader, "");
        assertEquals("98 12349 16565 \n\n\n0 1 2 3 4 5 6 7 8 9 10 11 12 \n", out.toString());
    }

    /**
     * Runs test with a 16GB file.
     */
    @Test
    @DisplayName("Large file test")
    public void testLargeFile() {
        File file = new File("./src/main/resources/LargeTest.txt");
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")){
            randomAccessFile.setLength(16000000000L);
            new SubstringFinderImpl("./src/main/resources/LargeTest.txt", "sdads");
            assertEquals("\n", out.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file.deleteOnExit();
    }
}