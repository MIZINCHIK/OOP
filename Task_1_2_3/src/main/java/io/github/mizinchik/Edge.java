package io.github.mizinchik;

/**
 * General interface describing an oriented
 * edge of a graph.
 *
 * @param <I> objects inside vertices
 * @param <L> length of an edge
 */
public interface Edge<I, L> {
    /**
     * Accesses length of the edge.
     *
     * @return length of the edge
     */
    L getLength();

    /**
     * Sets length of the edge.
     *
     * @param newLength edge length to set
     */
    void changeLength(L newLength);

    /**
     * Accesses the name of the edge.
     *
     * @return name of the edge
     */
    String getName();

    /**
     * Accesses vertex from which the edge starts.
     *
     * @return starting endpoint of the edge
     */
    Vertex<I> getStart();

    /**
     * Accesses vertex to which the edge goes.
     *
     * @return ending endpoint of the edge
     */
    Vertex<I> getEnd();
}
