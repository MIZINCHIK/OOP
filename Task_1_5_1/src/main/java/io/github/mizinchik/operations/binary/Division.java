package io.github.mizinchik.operations.binary;

import io.github.mizinchik.BinaryOperator;

/**
 * Division class for a calculator method factory.
 * Binary function.
 *
 * @author MIZINCHIK
 */
public class Division extends BinaryOperator {
    @Override
    public Double apply(Double... args) {
        return args[0] / args[1];
    }
}
