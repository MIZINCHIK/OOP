package io.github.mizinchik;

import java.util.function.BiFunction;

public class Power implements BiFunction<Double, Double, Double> {
    @Override
    public Double apply(Double base, Double power) {
        return Math.pow(base, power);
    }
}
