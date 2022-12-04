package io.github.mizinchik;

import java.util.function.UnaryOperator;

public class UnaryFunctionFactory extends FunctionFactory {
    UnaryOperator<Double> createFunction(String functionName) throws IllegalArgumentException {
        return switch(functionName) {
            case "sqrt" -> new SquareRoot();
            case "sin" -> new Sinus();
            case "cos" -> new Cosinus();
            default -> throw new IllegalArgumentException("No such function");
        };
    }
}
