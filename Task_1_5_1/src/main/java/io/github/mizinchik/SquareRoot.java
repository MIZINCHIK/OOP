package io.github.mizinchik;

import java.util.function.UnaryOperator;

/**
 * Square root class for a calculator method factory.
 * Unary function.
 *
 * @author MIZINCHIK
 */
public class SquareRoot implements UnaryOperator<Double> {
    @Override
    public Double apply(Double number) {
        return Math.sqrt(number);
    }
}
