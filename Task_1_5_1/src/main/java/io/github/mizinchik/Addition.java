package io.github.mizinchik;

import java.util.function.BiFunction;

public class Addition implements BiFunction<Double, Double, Double> {
    @Override
    public Double apply(Double aDouble, Double aDouble2) {
        return aDouble + aDouble2;
    }
}
