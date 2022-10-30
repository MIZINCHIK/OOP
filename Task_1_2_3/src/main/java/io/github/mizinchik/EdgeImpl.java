package io.github.mizinchik;

public class EdgeImpl<I> implements Edge<VertexImpl<I>, I, Double> {
    private Double length;
    private final VertexImpl<I> start;
    private final VertexImpl<I> end;
    private final String name;

    public EdgeImpl(Double length, String name, VertexImpl<I> start, VertexImpl<I> end) {
        this.length = length;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public Double getLength() {
        return length;
    }

    @Override
    public void changeLength(Double newLength) {
        length = newLength;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public VertexImpl<I> getStart() {
        return start;
    }

    @Override
    public VertexImpl<I> getEnd() {
        return end;
    }
}
