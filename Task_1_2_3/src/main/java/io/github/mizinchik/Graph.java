package io.github.mizinchik;

import java.util.HashMap;

public interface Graph <G extends Graph<G, E, V, I, L>, E extends Edge<G, E, V, I, L>, V extends Vertex<G, E, V, I, L>, I, L> {
    int getEdgesQuantity();

    int getVerticesQuantity();

    HashMap<String, V> getVertices();

    HashMap<String, E> getEdges();

    V addVertex(String name, I insideVal);

    void deleteVertex(V vertex);

    E addEdge(String name, V start, V end, L edgeLength);

    void deleteEdge(E edge);

    HashMap<V, L> topSort (V startingPoint);
}
