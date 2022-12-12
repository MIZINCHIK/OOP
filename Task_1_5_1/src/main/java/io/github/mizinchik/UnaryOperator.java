package io.github.mizinchik;

public abstract class UnaryOperator implements Operator {
    public boolean isBinary() {
        return false;
    }
}
