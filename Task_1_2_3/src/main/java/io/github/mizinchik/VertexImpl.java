package io.github.mizinchik;

/**
 * General class implementing a simple
 * graph vertex.
 *
 * @param <I> object inside vertices
 */
public class VertexImpl<I> implements Vertex<I> {
    private I insideVal;
    private final String name;

    /**
     * Constructor.
     *
     * @param insideVal value to store in the vertex
     * @param name of the vertex
     */
    public VertexImpl(I insideVal, String name) {
        this.insideVal = insideVal;
        this.name = name;
    }

    /**
     * Accesses an object stored in the vertex.
     *
     * @return object stored in a vertex
     */
    @Override
    public I getValue() {
        return insideVal;
    }

    /**
     * Assigns a new value to the vertex.
     *
     * @param newValue to assign to the vertex
     */
    @Override
    public void changeValue(I newValue) {
        insideVal = newValue;
    }

    /**
     * Accesses to the vertex's name.
     *
     * @return name of a vertex
     */
    @Override
    public String getName() {
        return name;
    }
}
