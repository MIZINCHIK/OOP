package io.github.mizinchik;

import io.github.mizinchik.operations.binary.*;
import io.github.mizinchik.operations.unary.Cosine;
import io.github.mizinchik.operations.unary.Sine;
import io.github.mizinchik.operations.unary.SquareRoot;

/**
 * Method factory for binary functions in
 * a simple command-line calculator.
 *
 * @author MIZINCHIK
 */
public class FunctionFactory {
    /**
     * Bakes instances of binary arithmetic function classes.
     *
     * @param functionName operation in an expression
     * @return a relevant arithmetic object
     * @throws IllegalArgumentException if the function in unknown
     */
    Operator createFunction(String functionName)
            throws IllegalArgumentException {
        return switch (functionName) {
            case "+" -> new Addition();
            case "-" -> new Subtraction();
            case "*" -> new Multiplication();
            case "/" -> new Division();
            case "log" -> new Logarithm();
            case "pow" -> new Power();
            case "sqrt" -> new SquareRoot();
            case "sin" -> new Sine();
            case "cos" -> new Cosine();
            default -> throw new IllegalArgumentException("No such function");
        };
    }
}
