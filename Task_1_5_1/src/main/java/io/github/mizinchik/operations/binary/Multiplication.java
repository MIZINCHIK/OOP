package io.github.mizinchik.operations.binary;

import io.github.mizinchik.BinaryOperator;

/**
 * Multiplication class for a calculator method factory.
 * Binary function.
 *
 * @author MIZINCHIK
 */
public class Multiplication extends BinaryOperator {
    @Override
    public Double apply(Double... args) {
        return args[0] * args[1];
    }
}
