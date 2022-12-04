package io.github.mizinchik;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args)
            throws IllegalArgumentException {
        Stack<Double> numbers = new Stack<>();
        for (int lastIndex = args.length - 1; lastIndex >= 0; lastIndex--) {
            if (Arrays.asList(FunctionFactory.getBinaryFunctions())
                    .contains(args[lastIndex])) {
                try {
                    Double firstArg = numbers.pop();
                    Double secondArg = numbers.pop();
                    BiFunction<Double, Double, Double> function = new BinaryFunctionFactory()
                            .createFunction(args[lastIndex]);
                    numbers.push(function.apply(firstArg, secondArg));
                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException("Not enough operands");
                }
            } else if (Arrays.asList(FunctionFactory.getUnaryFunctions())
                    .contains(args[lastIndex])) {
                try {
                    Double arg = numbers.pop();
                    UnaryOperator<Double> function = new UnaryFunctionFactory()
                            .createFunction(args[lastIndex]);
                    numbers.push(function.apply(arg));
                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException("Not enough operands");
                }
            } else {
                try {
                    numbers.push(Double.parseDouble(args[lastIndex]));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Not supported operator");
                }
            }
        }
        System.out.println(numbers.pop());
    }
}