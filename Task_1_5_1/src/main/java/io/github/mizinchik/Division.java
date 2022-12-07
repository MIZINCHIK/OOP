package io.github.mizinchik;

import java.util.function.BiFunction;

/**
 * Division class for a calculator method factory.
 * Binary function.
 *
 * @author MIZINCHIK
 */
public class Division implements BiFunction<Double, Double, Double> {
    @Override
    public Double apply(Double divisible, Double divisor) {
        return divisible / divisor;
    }
}
