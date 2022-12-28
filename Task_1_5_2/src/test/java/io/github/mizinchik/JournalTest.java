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

    @Test
    @DisplayName("Reference test")
    void testReference() {
        Journal.main(new String[]{"remove"});
        var date = new Date();
        Journal.main(new String[]{"add", "Моя заметка", "Очень важная заметка"});
        Journal.main(new String[]{"show"});
        assertEquals(format.format(date) + " Моя заметка Очень важная заметка" + System.lineSeparator(), out.toString());
        Journal.main(new String[]{"show", format.format(date), format.format(date), "Моя"});
        assertEquals(format.format(date) + " Моя заметка Очень важная заметка" + System.lineSeparator()
                + format.format(date) + " Моя заметка Очень важная заметка"  + System.lineSeparator(), out.toString());
        Journal.main(new String[]{"remove", "Моя заметка"});
        Journal.main(new String[]{"show"});
        assertEquals(format.format(date) + " Моя заметка Очень важная заметка"  + System.lineSeparator()
                + format.format(date) + " Моя заметка Очень важная заметка"  + System.lineSeparator(), out.toString());

    }

    @Test
    @DisplayName("Complete test")
    void testComplete() {
    }
}