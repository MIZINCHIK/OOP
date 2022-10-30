package io.github.mizinchik;

public interface Vertex <I> {
    I getValue();

    void changeValue(I newValue);

    String getName();
}
