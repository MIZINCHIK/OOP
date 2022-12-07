package io.github.mizinchik;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainTest {
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
     * Performs a reference test for our task.
     *
     * @throws Exception scanner does it
     */
    @Test
    @DisplayName("Tests the reference example")
    void testReference() throws Exception {
        String[] expression = {"sin", "+", "-", "1", "2", "1"};
        Main.main(expression);
        assertEquals("0.0" + System.lineSeparator(), out.toString());
    }

    @Test
    @DisplayName("Tests many expressions")
    void testLarge() throws Exception {
        String[] expression = {"sin", "+", "-", "1", "2", "1"};
        Main.main(expression);
        assertEquals("0.0" + System.lineSeparator(), out.toString());
        expression = new String[]{"*", "-", "5", "6", "7"};
        out.flush();
        Main.main(expression);
        assertEquals("0.0" + System.lineSeparator()
                + "-7.0" + System.lineSeparator(), out.toString());
        expression = new String[]{"pow", "3", "2"};
        Main.main(expression);
        assertEquals("0.0" + System.lineSeparator()
                + "-7.0" + System.lineSeparator()
                + "9.0" + System.lineSeparator(), out.toString());
        expression = new String[]{"log", "1.1", "9.9999"};
        Main.main(expression);
        assertEquals("0.0" + System.lineSeparator()
                + "-7.0" + System.lineSeparator()
                + "9.0" + System.lineSeparator()
                + "24.158753006985307" + System.lineSeparator(), out.toString());
        expression = new String[]{"pow", "1.001",
                "2222"};
        Main.main(expression);
        assertEquals("0.0" + System.lineSeparator()
                + "-7.0" + System.lineSeparator()
                + "9.0" + System.lineSeparator()
                + "24.158753006985307" + System.lineSeparator()
                + "9.21552664298739" + System.lineSeparator(), out.toString());
        expression = new String[]{"cos", "3"};
        Main.main(expression);
        assertEquals("0.0" + System.lineSeparator()
                + "-7.0" + System.lineSeparator()
                + "9.0" + System.lineSeparator()
                + "24.158753006985307" + System.lineSeparator()
                + "9.21552664298739" + System.lineSeparator()
                + "-0.9899924966004454" + System.lineSeparator(), out.toString());
        expression = new String[]{"pow", "log", "10", "10.01",
                "*", "23534", "sqrt", "cos", "7"};
        Main.main(expression);
        assertEquals("0.0" + System.lineSeparator()
                + "-7.0" + System.lineSeparator()
                + "9.0" + System.lineSeparator()
                + "24.158753006985307" + System.lineSeparator()
                + "9.21552664298739" + System.lineSeparator()
                + "-0.9899924966004454" + System.lineSeparator()
                + "7101.151407970923" + System.lineSeparator(), out.toString());
    }
}