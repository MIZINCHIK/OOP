package io.github.mizinchik;

import static io.github.mizinchik.Calculator.calculate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for a simple command-line calculator.
 *
 * @author MIZINCHIK
 */
public class CalculatorTest {
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
     */
    @Test
    @DisplayName("Tests the reference example")
    void testReference() {
        String[] expression = {"sin", "+", "-", "1", "2", "1"};
        assertEquals(0.0, calculate(expression));
    }

    /**
     * Tests various complicated expressions.
     */
    @Test
    @DisplayName("Tests many expressions")
    void testLarge() {
        String[] expression = {"sin", "+", "-", "1", "2", "1"};
        assertEquals(0.0, calculate(expression));
        expression = new String[]{"*", "-", "5", "6", "7"};
        assertEquals(-7.0, calculate(expression));
        expression = new String[]{"pow", "3", "2"};
        assertEquals(9.0, calculate(expression));
        expression = new String[]{"log", "1.1", "9.9999"};
        assertEquals(24.158753006985307, calculate(expression));
        expression = new String[]{"pow", "1.001", "2222"};
        assertEquals(9.21552664298739, calculate(expression));
        expression = new String[]{"cos", "3"};
        assertEquals(-0.9899924966004454, calculate(expression));
        expression = new String[]{"pow", "log", "10", "10.01",
            "*", "23534", "sqrt", "cos", "7"};
        assertEquals(7101.151407970923, calculate(expression));
        expression = new String[]{"/", "0", "0"};
        assertEquals(Double.NaN, calculate(expression));
        expression = new String[]{"/", "500", "1234612"};
        assertEquals(4.049855339167285E-4, calculate(expression));
    }

    /**
     * Tests all the cases of throwing exceptions.
     */
    @Test
    @DisplayName("Exceptions test")
    void testException() {
        String[] expression = {"asdsad", "+", "-", "1", "2", "1"};
        assertThrows(IllegalArgumentException.class, () -> calculate(expression));
        String[] expressionTwo = new String[]{"pow", "3"};
        assertThrows(IllegalArgumentException.class, () -> calculate(expressionTwo));
        String[] expressionThree = new String[]{"log"};
        assertThrows(IllegalArgumentException.class, () -> calculate(expressionThree));
        String[] expressionFour = new String[]{"1", "2"};
        assertThrows(IllegalArgumentException.class, () -> calculate(expressionFour));
    }
}