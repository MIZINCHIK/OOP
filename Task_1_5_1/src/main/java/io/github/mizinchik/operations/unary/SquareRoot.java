package io.github.mizinchik.operations.unary;

import io.github.mizinchik.UnaryOperator;

/**
 * Square root class for a calculator method factory.
 * Unary function.
 *
 * @author MIZINCHIK
 */
public class SquareRoot extends UnaryOperator {
    @Override
    public Double apply(Double... args) {
        return Math.sqrt(args[0]);
    }
}
