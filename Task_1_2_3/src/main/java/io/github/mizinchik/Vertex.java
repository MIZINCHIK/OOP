package io.github.mizinchik;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface Vertex <G extends Graph<G, E, V, I, L>, E extends Edge<G, E, V, I, L>, V extends Vertex<G, E, V, I, L>, I, L> {
    I getValue();

    void changeValue(I newValue);

    String getName();

    G getGraph();

    ArrayList<V> getAdjacentAfter();

    ArrayList<V> getAdjacentBefore();

    ArrayList<E> getIncidentalEdgesStarting();

    ArrayList<E> getIncidentalEdgesEnding();
}
