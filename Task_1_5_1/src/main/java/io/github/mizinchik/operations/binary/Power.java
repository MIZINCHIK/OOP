package io.github.mizinchik.operations.binary;

import io.github.mizinchik.BinaryOperator;

/**
 * Power class for a calculator method factory.
 * Binary function.
 *
 * @author MIZINCHIK
 */
public class Power extends BinaryOperator {
    @Override
    public Double apply(Double... args) {
        return Math.pow(args[0], args[1]);
    }
}
