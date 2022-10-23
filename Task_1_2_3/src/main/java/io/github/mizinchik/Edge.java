package io.github.mizinchik;

import java.util.ArrayList;

public interface Edge<G extends Graph<G, E, V, I, L>, E extends Edge<G, E, V, I, L>, V extends Vertex<G, E, V, I, L>, I, L> {
    L getLength();

    void changeLength(L newLength);

    String getName();

    G getGraph();

    V getStart();

    V getEnd();

    ArrayList<E> getAdjacentEdgesAfter();

    ArrayList<E> getAdjacentEdgesBefore();
}
