package io.github.mizinchik;

import java.util.function.BiFunction;

/**
 * Addition class for a calculator method factory.
 * Binary function.
 *
 * @author MIZINCHIK
 */
public class Addition implements BiFunction<Double, Double, Double> {
    @Override
    public Double apply(Double aDouble, Double aDouble2) {
        return aDouble + aDouble2;
    }
}
