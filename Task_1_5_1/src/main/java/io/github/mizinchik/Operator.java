package io.github.mizinchik;

/**
 * Class for operators working on up to two
 * Double arguments and returning Double on apply.
 *
 * @author MIZINCHIK
 */
public interface Operator {
    /**
     * Checks if the operator takes 2 arguments.
     *
     * @return if the operator is binary
     */
    boolean isBinary();

    /**
     * Apply the operator to the operands.
     *
     * @param args operands
     * @return value of the preformed operation
     */
    Double apply(Double... args);
}
