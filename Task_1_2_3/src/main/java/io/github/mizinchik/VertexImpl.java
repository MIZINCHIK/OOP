package io.github.mizinchik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class VertexImpl<I, L extends Number> implements Vertex<GraphImpl<I, L>, EdgeImpl<I, L>, VertexImpl<I, L>, I, L> {
    private I insideVal;
    private ArrayList<VertexImpl<I, L>> adjacentVerticesAfter;
    private ArrayList<VertexImpl<I, L>> adjacentVerticesBefore;
    private ArrayList<EdgeImpl<I, L>> incidentalEdgesStarting;
    private ArrayList<EdgeImpl<I, L>> incidentalEdgesEnding;
    private final GraphImpl<I, L> graph;
    private final String name;

    public VertexImpl(I insideVal, GraphImpl<I, L> graph, String name) {
        this.insideVal = insideVal;
        this.graph = graph;
        this.name = name;
    }

    @Override
    public I getValue() {
        return insideVal;
    }

    @Override
    public void changeValue(I newValue) {
        insideVal = newValue;
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
    public ArrayList<VertexImpl<I, L>> getAdjacentAfter() {
        return adjacentVerticesAfter;
    }

    @Override
    public ArrayList<VertexImpl<I, L>> getAdjacentBefore() {
        return adjacentVerticesBefore;
    }

    @Override
    public ArrayList<EdgeImpl<I, L>> getIncidentalEdgesStarting() {
        return incidentalEdgesStarting;
    }

    @Override
    public ArrayList<EdgeImpl<I, L>> getIncidentalEdgesEnding() {
        return incidentalEdgesEnding;
    }


    public void deleteAdjacentVertex(VertexImpl<I, L> vertex) throws NoSuchElementException {
        if (!adjacentVerticesAfter.remove(vertex) && !adjacentVerticesBefore.remove(vertex)) {
            throw new NoSuchElementException("No such adjacent vertex to the given.");
        }
    }

    public void deleteIncidentalEdge(EdgeImpl<I, L> edge) throws NoSuchElementException {
        if (!incidentalEdgesStarting.remove(edge) && !incidentalEdgesEnding.remove(edge)){
            throw new NoSuchElementException("No such incidental edge to the given.");
        }
    }

    public void addIncidentalEdgeStarting(EdgeImpl<I, L> edge) {
        incidentalEdgesStarting.add(edge);
        edge.getEnd().adjacentVerticesBefore.add(this);
        adjacentVerticesAfter.add(edge.getEnd());
        edge.getEnd().incidentalEdgesEnding.add(edge);
    }

    public void addIncidentalEdgeEnding(EdgeImpl<I, L> edge) {
        edge.getStart().addIncidentalEdgeStarting(edge);
    }
}
