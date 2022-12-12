package io.github.mizinchik;

import java.util.EmptyStackException;
import java.util.Stack;

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
     * @param args of the command line
     * @throws IllegalArgumentException if not enough operands
     */
    public static void main(String[] args)
            throws IllegalArgumentException {
        Stack<Double> numbers = new Stack<>();
        for (int lastIndex = args.length - 1; lastIndex >= 0; lastIndex--) {
            try {
                numbers.push(Double.parseDouble(args[lastIndex]));
            } catch (NumberFormatException e) {
                try {
                    Operator function = new FunctionFactory()
                            .createFunction(args[lastIndex]);
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
            System.out.println(numbers.pop());
        } else {
            throw new IllegalArgumentException("Not enough operators");
        }
    }
}