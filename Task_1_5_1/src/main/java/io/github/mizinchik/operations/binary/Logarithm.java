package io.github.mizinchik.operations.binary;

import io.github.mizinchik.BinaryOperator;

/**
 * Logarithm class for a calculator method factory.
 * Binary function.
 *
 * @author MIZINCHIK
 */
public class Logarithm extends BinaryOperator {
    @Override
    public Double apply(Double... args) {
        return Math.log(args[1]) / Math.log(args[0]);
    }
}
