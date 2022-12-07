package io.github.mizinchik;

import java.util.function.BiFunction;

/**
 * Method factory for binary functions in
 * a simple command-line calculator.
 *
 * @author MIZINCHIK
 */
public class BinaryFunctionFactory extends FunctionFactory {
    /**
     * @param functionName operation in an expression
     * @return a relevant arithmetic object
     * @throws IllegalArgumentException if the function in unknown
     */
    BiFunction<Double, Double, Double> createFunction(String functionName) throws IllegalArgumentException {
        return switch(functionName) {
            case "+" -> new Addition();
            case "-" -> new Subtraction();
            case "*" -> new Multiplication();
            case "/" -> new Division();
            case "log" -> new Logarithm();
            case "pow" -> new Power();
            default -> throw new IllegalArgumentException("No such function");
        };
    }
}
