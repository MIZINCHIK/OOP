package io.github.mizinchik;

import java.util.function.BiFunction;

public class Subtraction implements BiFunction<Double, Double, Double> {
    @Override
    public Double apply(Double minuend, Double subtrahend) {
        return minuend - subtrahend;
    }
}
