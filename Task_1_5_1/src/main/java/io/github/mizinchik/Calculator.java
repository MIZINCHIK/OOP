package io.github.mizinchik;

import static io.github.mizinchik.FunctionFactory.createFunction;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Calculates prefix expressions of double values
 * with operators described in the corresponding package
 * into a double value.
 *
 * @author MIZINCHIK
 */
public class Calculator {
    /**
     * Takes an expression as a string
     * containing operators and operands. Returns the value of
     * the calculated expression.
     *
     * @param expressionRaw to calculate
     * @return result
     * @throws IllegalArgumentException if the string is incorrect
     */
    public static Double calculate(String expressionRaw) throws IllegalArgumentException {
        String[] expression = expressionRaw.split(" ");
        Stack<Double> numbers = new Stack<>();
        for (int lastIndex = expression.length - 1; lastIndex >= 0; lastIndex--) {
            try {
                numbers.push(Double.parseDouble(expression[lastIndex]));
            } catch (NumberFormatException e) {
                try {
                    Operator function = createFunction(expression[lastIndex]);
                    if (function.isBinary()) {
                        Double firstArg = numbers.pop();
                        Double secondArg = numbers.pop();
                        numbers.push(function.apply(firstArg, secondArg));
                    } else {
                        Double arg = numbers.pop();
                        numbers.push(function.apply(arg));
                    }
                } catch (EmptyStackException j) {
                    throw new IllegalArgumentException("Not enough operands");
                }
            }
        }
        if (numbers.size() == 1) {
            return numbers.pop();
        } else {
            throw new IllegalArgumentException("Not enough operators");
        }
    }
}
