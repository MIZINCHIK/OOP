package io.github.mizinchik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class EdgeImpl<I, L extends Number> implements Edge<GraphImpl<I, L>, EdgeImpl<I, L>, VertexImpl<I, L>, I, L> {
    private L length;
    private ArrayList<EdgeImpl<I, L>> adjacentEdgesAfter;
    private ArrayList<EdgeImpl<I, L>> adjacentEdgesBefore;
    private final VertexImpl<I, L> start;
    private final VertexImpl<I, L> end;
    private final GraphImpl<I, L> graph;
    private final String name;

    public EdgeImpl(L length, GraphImpl<I, L> graph, String name, VertexImpl<I, L> start, VertexImpl<I, L> end) throws IllegalArgumentException {
        HashMap<String, EdgeImpl<I, L>> names = graph.getEdges();
        if (names.containsKey(name)) {
            throw new IllegalArgumentException("Edge is already in the graph.");
        }
        else {
            this.length = length;
            this.graph = graph;
            this.name = name;
            this.start = start;
            this.end = end;
            this.adjacentEdgesAfter = end.getIncidentalEdgesStarting();
            this.adjacentEdgesBefore = start.getIncidentalEdgesEnding();
        }
    }

    @Override
    public L getLength() {
        return length;
    }

    @Override
    public void changeLength(L newLength) {
        length = newLength;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GraphImpl<I, L> getGraph() {
        return graph;
    }

    @Override
    public VertexImpl<I, L> getStart() {
        return start;
    }

    @Override
    public VertexImpl<I, L> getEnd() {
        return end;
    }

    @Override
    public ArrayList<EdgeImpl<I, L>> getAdjacentEdgesAfter() {
        return adjacentEdgesAfter;
    }

    @Override
    public ArrayList<EdgeImpl<I, L>> getAdjacentEdgesBefore() {
        return adjacentEdgesBefore;
    }

    public void deleteAdjacentEdge(EdgeImpl<I, L> edge) throws NoSuchElementException {
        if (!adjacentEdgesAfter.remove(edge) && !adjacentEdgesBefore.remove(edge)){
            throw new NoSuchElementException("No such adjacent edge to the given");
        }
    }
}
