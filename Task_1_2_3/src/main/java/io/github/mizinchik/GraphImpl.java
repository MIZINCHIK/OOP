package io.github.mizinchik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class GraphImpl<I, L extends Number> implements Graph<GraphImpl<I, L>, EdgeImpl<I, L>, VertexImpl<I, L>, I, L> {
    private ArrayList<VertexImpl<I, L>> vertices;
    private ArrayList<EdgeImpl<I, L>> edges;

    public void setVerticesAndEdges(ArrayList<VertexImpl<I, L>> vertices, ArrayList<EdgeImpl<I, L>> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    @Override
    public int getEdgesQuantity() {
        return edges.size();
    }

    @Override
    public int getVerticesQuantity() {
        return vertices.size();
    }

    @Override
    public HashMap<String, VertexImpl<I, L>> getVertices() {
        HashMap<String, VertexImpl<I, L>> verticesMap = new HashMap<>();
        for (VertexImpl<I, L> vertex : vertices) {
            verticesMap.put(vertex.getName(), vertex);
        }
        return verticesMap;
    }

    @Override
    public HashMap<String, EdgeImpl<I, L>> getEdges() {
        HashMap<String, EdgeImpl<I, L>> edgesMap = new HashMap<>();
        for (EdgeImpl<I, L> edge : edges) {
            edgesMap.put(edge.getName(), edge);
        }
        return edgesMap;
    }

    @Override
    public VertexImpl<I, L> addVertex(String name, I insideVal) throws IllegalArgumentException {
        HashMap<String, VertexImpl<I, L>> verticesMap = getVertices();
        if (verticesMap.containsKey(name)){
            throw new IllegalArgumentException("Such vertex already exists.");
        }
        else {
            var newVertex = new VertexImpl<I, L>(insideVal, this, name);
            vertices.add(newVertex);
            return newVertex;
        }
    }

    @Override
    public void deleteVertex(VertexImpl<I, L> vertex) throws NoSuchElementException {
        if (!vertices.remove(vertex)){
            throw new NoSuchElementException("No such vertex in the graph.");
        }
        for (VertexImpl<I, L> adjacentVertex : vertex.getAdjacentAfter()) {
            adjacentVertex.deleteAdjacentVertex(vertex);
        }
        for (VertexImpl<I, L> adjacentVertex : vertex.getAdjacentBefore()) {
            adjacentVertex.deleteAdjacentVertex(vertex);
        }
        for (EdgeImpl<I, L> incidentalEdge : vertex.getIncidentalEdgesEnding()) {
            deleteEdge(incidentalEdge);
        }
        for (EdgeImpl<I, L> incidentalEdge : vertex.getIncidentalEdgesStarting()) {
            deleteEdge(incidentalEdge);
        }
    }

    @Override
    public EdgeImpl<I, L> addEdge(String name, VertexImpl<I, L> start, VertexImpl<I, L> end, L edgeLength) {
        var newEdge = new EdgeImpl<I, L>(edgeLength, this, name, start, end);
        edges.add(newEdge);
        return newEdge;
    }

    @Override
    public void deleteEdge(EdgeImpl<I, L> edge) {
        if (!edges.remove(edge)) {
            throw new NoSuchElementException("No such edge in the graph.");
        }
        for (EdgeImpl<I, L> adjacentEdge : edge.getAdjacentEdgesAfter()) {
            adjacentEdge.deleteAdjacentEdge(edge);
        }
        for (EdgeImpl<I, L> adjacentEdge : edge.getAdjacentEdgesBefore()) {
            adjacentEdge.deleteAdjacentEdge(edge);
        }
        edge.getStart().deleteIncidentalEdge(edge);
        edge.getEnd().deleteIncidentalEdge(edge);
    }

    @Override
    public HashMap<VertexImpl<I, L>, L> topSort(VertexImpl<I, L> startingPoint) {
        return null;
    }
}