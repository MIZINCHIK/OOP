package io.github.mizinchik;

import java.util.List;
import java.util.Map;

/**
 * General graph interface.
 * May use whatever implementation of the
 * Edge and Vertex interfaces you like.
 *
 * @param <E> edges
 * @param <V> vertices
 * @param <I> values inside vertices
 * @param <L> length of edges
 */
public interface Graph<E extends Edge<V, I, L>, V extends Vertex<I>, I, L> {
    /**
     * Counts edges in the graph.
     *
     * @return quantity of edges in a graph
     */
    int getEdgesQuantity();

    /**
     * Counts vertices in the graph.
     *
     * @return quantity of vertices in a graph
     */
    int getVerticesQuantity();

    /**
     * Accesses an edge list of the graph.
     *
     * @return list of edges in a graph.
     */
    List<E> getEdgesList();

    /**
     * Accesses a vertex list of the graph.
     *
     * @return list of vertices in a graph
     */
    List<V> getVerticesList();

    /**
     * Accesses a mapping from names to vertices.
     *
     * @return mapping from vertices' names to vertices themselves
     */
    Map<String, V> getVertices();

    /**
     * Adds a vertex to the graph.
     *
     * @param name of a vertex
     * @param insideVal in vertices
     * @return vertex added
     */
    V addVertex(String name, I insideVal);

    /**
     * Accesses a vertex by its name.
     *
     * @param name of a vertex
     * @return vertex itself
     */
    V getVertex(String name);

    /**
     * Deletes a vertex from the graph.
     *
     * @param vertex to delete
     */
    void deleteVertex(V vertex);

    /**
     * Add an edge to the graph.
     *
     * @param name of an edge
     * @param start vertex from which the edge starts
     * @param end vertex in which the edge ends
     * @param edgeLength length of an edge
     * @return edge added
     */
    E addEdge(String name, V start, V end, L edgeLength);

    /**
     * Deletes an edge from the graph.
     *
     * @param edge to delete
     */
    void deleteEdge(E edge);

    /**
     * Performs a topological sort on a graph starting
     * from the startingPoint and ordering others by their
     * distance from it.
     *
     * @param startingPoint from which to sort
     * @return list of pairs Name-Length-from-the-starting-point
     */
    List<Map.Entry<String, L>> topSort(V startingPoint);
}
