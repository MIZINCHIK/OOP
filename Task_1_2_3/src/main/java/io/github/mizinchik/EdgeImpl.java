package io.github.mizinchik;

/**
 * General interface implementing an oriented
 * edge of a graph.
 *
 * @param <I> values inside vertices
 */
public class EdgeImpl<I> implements Edge<I, Double> {
    private Double length;
    private final Vertex<I> start;
    private final Vertex<I> end;
    private final String name;

    /**
     * Constructor.
     *
     * @param length of the edge
     * @param name of the edge
     * @param start vertex where it starts
     * @param end vertex where it ends
     */
    public EdgeImpl(Double length, String name, VertexImpl<I> start, VertexImpl<I> end) {
        this.length = length;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    /**
     * Accesses length of the edge.
     *
     * @return length of the edge
     */
    @Override
    public Double getLength() {
        return length;
    }

    /**
     * Sets length of the edge.
     *
     * @param newLength edge length to set
     */
    @Override
    public void changeLength(Double newLength) {
        length = newLength;
    }

    /**
     * Accesses the name of the edge.
     *
     * @return name of the edge
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Accesses vertex from which the edge starts.
     *
     * @return starting endpoint of the edge
     */
    @Override
    public Vertex<I> getStart() {
        return start;
    }

    /**
     * Accesses vertex to which the edge goes.
     *
     * @return ending endpoint of the edge
     */
    @Override
    public Vertex<I> getEnd() {
        return end;
    }
}
