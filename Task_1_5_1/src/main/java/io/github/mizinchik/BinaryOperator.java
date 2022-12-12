package io.github.mizinchik;

/**
 * Class for operations taking two operand as arguments.
 *
 * @author MIZINCHIK
 */
public abstract class BinaryOperator implements Operator {
    /**
     * Checks if the operator is binary.
     * In this case it's always binary.
     *
     * @return always false
     */
    public boolean isBinary() {
        return true;
    }
}
