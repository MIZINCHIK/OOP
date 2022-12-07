package io.github.mizinchik;

import java.util.function.UnaryOperator;

/**
 * Method factory for unary functions in
 * a simple command-line calculator.
 *
 * @author MIZINCHIK
 */
public class UnaryFunctionFactory extends FunctionFactory {
    /**
     * Bakes instances of unary arithmetic function classes.
     *
     * @param functionName operation in an expression
     * @return a relevant arithmetic object
     * @throws IllegalArgumentException if the function in unknown
     */
    UnaryOperator<Double> createFunction(String functionName) throws IllegalArgumentException {
        return switch(functionName) {
            case "sqrt" -> new SquareRoot();
            case "sin" -> new Sine();
            case "cos" -> new Cosine();
            default -> throw new IllegalArgumentException("No such function");
        };
    }
}
