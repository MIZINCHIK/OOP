package io.github.mizinchik;

import java.util.EmptyStackException;
import java.util.Stack;

import static io.github.mizinchik.FunctionFactory.createFunction;

public class Calculator {
    public static Double calculate(String[] expression) throws IllegalArgumentException {
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
