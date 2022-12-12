package io.github.mizinchik;

public interface Operator {
    boolean isBinary();
    Double apply(Double... args);
}
