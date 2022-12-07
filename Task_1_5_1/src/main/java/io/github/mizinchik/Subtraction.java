package io.github.mizinchik;

import java.util.function.BiFunction;

/**
 * Subtraction class for a calculator method factory.
 * Binary function.
 *
 * @author MIZINCHIK
 */
public class Subtraction implements BiFunction<Double, Double, Double> {
    @Override
    public Double apply(Double minuend, Double subtrahend) {
        return minuend - subtrahend;
    }
}
