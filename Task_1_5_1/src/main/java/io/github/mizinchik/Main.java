package io.github.mizinchik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static io.github.mizinchik.Calculator.calculate;

/**
 * Command-line calculator.
 *
 * @author MIZINCHIk
 */
public class Main {
    /**
     * Executes a calculator. Prints the result of the calculation
     * to stdout.
     *
     * @throws IllegalArgumentException if not enough operands
     */
    public static void main()
            throws IllegalArgumentException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in))) {
            String expressionRaw = reader.readLine();
            String[] expression = expressionRaw.split(" ");
            Double result = calculate(expression);
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}