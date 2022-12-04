package io.github.mizinchik;

import java.util.function.BiFunction;

public class BinaryFunctionFactory extends FunctionFactory {
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
