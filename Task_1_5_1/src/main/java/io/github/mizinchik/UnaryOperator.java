package io.github.mizinchik;

/**
 * Class for operations taking one operand as arguments.
 *
 * @author MIZINCHIK
 */
public abstract class UnaryOperator implements Operator {
    /**
     * Checks if the operator is binary.
     * In this case it's always unary.
     *
     * @return always false
     */
    public boolean isBinary() {
        return false;
    }
}
