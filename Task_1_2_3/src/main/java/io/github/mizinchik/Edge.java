package io.github.mizinchik;

public interface Edge<V extends Vertex<I>, I, L> {
    L getLength();

    void changeLength(L newLength);

    String getName();

    V getStart();

    V getEnd();
}
