package io.github.mizinchik;

import java.util.List;
import java.util.Map;

public interface Graph <E extends Edge<V, I, L>, V extends Vertex<I>, I, L> {
    int getEdgesQuantity();

    int getVerticesQuantity();
    List<E> getEdgesList();

    List<V> getVerticesList();

    Map<String, V> getVertices();

    V addVertex(String name, I insideVal);

    V getVertex(String name);

    void deleteVertex(V vertex);

    E addEdge(String name, V start, V end, L edgeLength);

    void deleteEdge(E edge);

    List<Map.Entry<String, L>> topSort (V startingPoint);
}
