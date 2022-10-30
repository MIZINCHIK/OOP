package io.github.mizinchik;

public class VertexImpl<I> implements Vertex<I> {
    private I insideVal;
    private final String name;

    public VertexImpl(I insideVal, String name) {
        this.insideVal = insideVal;
        this.name = name;
    }

    @Override
    public I getValue() {
        return insideVal;
    }

    @Override
    public void changeValue(I newValue) {
        insideVal = newValue;
    }

    @Override
    public String getName() {
        return name;
    }
}
