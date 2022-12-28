package io.github.mizinchik;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the note-keeper classes.
 *
 * @author MIZINCHIK
 */
public class JournalTest {
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
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
     * Tests the reference example of how the app should work.
     */
    @Test
    @DisplayName("Reference test")
    void testReference() {
        Journal.main(new String[]{"remove"});
        var date = new Date();
        Journal.main(new String[]{"add", "Моя заметка", "Очень важная заметка"});
        Journal.main(new String[]{"show"});
        assertEquals(format.format(date) + " Моя заметка Очень важная заметка"
                + System.lineSeparator(), out.toString());
        Journal.main(new String[]{"show", format.format(date), format.format(date), "Моя"});
        assertEquals(format.format(date) + " Моя заметка Очень важная заметка"
                + System.lineSeparator()
                + format.format(date) + " Моя заметка Очень важная заметка"
                + System.lineSeparator(), out.toString());
        Journal.main(new String[]{"remove", "Моя заметка"});
        Journal.main(new String[]{"show"});
        assertEquals(format.format(date) + " Моя заметка Очень важная заметка"
                + System.lineSeparator()
                + format.format(date) + " Моя заметка Очень важная заметка"
                + System.lineSeparator(), out.toString());

    }

    /**
     * Tests everything that's left after the first test.
     */
    @Test
    @DisplayName("Complete test")
    void testComplete() {
        Journal.main(new String[]{"remove"});
        Journal.main(new String[]{"show"});
        assertEquals("", out.toString());
        var date = new Date();
        Journal.main(new String[]{"add", "asduighsadhgjsa", "sadfjhsdafbgdas"});
        Journal.main(new String[]{"add", "asduighsadhgjsa", "sadfjhsdafbgdas"});
        Journal.main(new String[]{"add", "asduighsadhgjsa", "sadfjhsdafbgdas"});
        Journal.main(new String[]{"add", "asduighsadhgjsa", "sadfjhsdafbgdas"});
        Journal.main(new String[]{"add", "asduighsadhgjsa", "sadfjhsdafbgdas"});
        Journal.main(new String[]{"add", "asduighsadhgjsa", "sadfjhsdafbgdas"});
        Journal.main(new String[]{"add", "asduighsadhgjsa", "sadfjhsdafbgdas"});
        Journal.main(new String[]{"add", "asduighsadhgjsa", "sadfjhsdafbgdas"});
        Journal.main(new String[]{"add", "asduighsadhgjsa", "sadfjhsdafbgdas"});
        Journal.main(new String[]{"show"});
        var singleString = format.format(date) + " asduighsadhgjsa sadfjhsdafbgdas"
                + System.lineSeparator();
        var soughtString = new String(new char[8]).replace("\0", singleString);
        assertEquals(soughtString, out.toString());
        var journal = new Journal();
        journal.remove(null);
        journal.show(null);
        assertEquals(soughtString, out.toString());
        var newDate = new Date();
        journal.add(new String[]{"sdasdasd", "saadsadsad"});
        journal.show(null);
        assertEquals(soughtString + format.format(newDate) + " sdasdasd saadsadsad"
                + System.lineSeparator(), out.toString());
    }
}