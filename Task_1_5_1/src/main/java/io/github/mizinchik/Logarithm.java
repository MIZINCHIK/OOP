package io.github.mizinchik;

import java.util.function.BiFunction;

/**
 * Logarithm class for a calculator method factory.
 * Binary function.
 *
 * @author MIZINCHIK
 */
public class Logarithm implements BiFunction<Double, Double, Double> {
    @Override
    public Double apply(Double base, Double number) {
        return Math.log(number) / Math.log(base);
    }
}
