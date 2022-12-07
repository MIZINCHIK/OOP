package io.github.mizinchik;

import java.util.function.UnaryOperator;

/**
 * Cosine class for a calculator method factory.
 * Unary function.
 *
 * @author MIZINCHIK
 */
public class Cosine implements UnaryOperator<Double> {
    @Override
    public Double apply(Double aDouble) {
        return Math.cos(aDouble);
    }
}
