package io.github.mizinchik.operations.unary;

import io.github.mizinchik.UnaryOperator;

/**
 * Sine class for a calculator method factory.
 * Unary function.
 *
 * @author MIZINCHIK
 */
public class Sine extends UnaryOperator {
    @Override
    public Double apply(Double... args) {
        return Math.sin(args[0]);
    }
}
