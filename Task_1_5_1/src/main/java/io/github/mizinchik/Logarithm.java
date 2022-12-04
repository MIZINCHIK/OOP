package io.github.mizinchik;

import java.util.function.BiFunction;

public class Logarithm implements BiFunction<Double, Double, Double> {
    @Override
    public Double apply(Double base, Double number) {
        return Math.log(number) / Math.log(base);
    }
}
