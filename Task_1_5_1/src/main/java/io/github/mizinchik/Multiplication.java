package io.github.mizinchik;

import java.util.function.BiFunction;

/**
 * Multiplication class for a calculator method factory.
 * Binary function.
 *
 * @author MIZINCHIK
 */
public class Multiplication implements BiFunction<Double, Double, Double> {
    @Override
    public Double apply(Double numberOne, Double numberTwo) {
        return numberOne * numberTwo;
    }
}
