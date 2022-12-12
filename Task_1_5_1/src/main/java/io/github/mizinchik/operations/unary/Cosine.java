package io.github.mizinchik.operations.unary;

import io.github.mizinchik.UnaryOperator;

/**
 * Cosine class for a calculator method factory.
 * Unary function.
 *
 * @author MIZINCHIK
 */
public class Cosine extends UnaryOperator {
    @Override
    public Double apply(Double... args) {
        return Math.cos(args[0]);
    }
}
