package io.github.mizinchik;

import java.util.function.UnaryOperator;

/**
 * Sine class for a calculator method factory.
 * Unary function.
 *
 * @author MIZINCHIK
 */
public class Sine implements UnaryOperator<Double> {
    @Override
    public Double apply(Double number) {
        return Math.sin(number);
    }
}
