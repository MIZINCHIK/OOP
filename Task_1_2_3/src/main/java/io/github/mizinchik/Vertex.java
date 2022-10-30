package io.github.mizinchik;

/**
 * General interface describing a simple
 * graph vertex.
 *
 * @param <I> object inside vertices
 */
public interface Vertex <I> {
    /**
     * Accesses an object stored in the vertex.
     *
     * @return object stored in a vertex
     */
    I getValue();

    /**
     * Assigns a new value to the vertex.
     *
     * @param newValue to assign to the vertex
     */
    void changeValue(I newValue);

    /**
     * Accesses to the vertex's name.
     *
     * @return name of a vertex
     */
    String getName();
}
