package io.github.mizinchik;

import static java.lang.Double.NaN;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

/**
 * General oriented simple graph class.
 * Uses EdgeImpl as its edges
 * and VertexImpl as its vertices.
 * Provides methods for parsing
 * a graph from a file in 3 modes:
 * A for adjacency matrix
 * I for incidence matrix
 * L for adjacency lists;
 * and for topological sort.
 * Adjacency matrix should look like:
 *
 * @param <I> values stored in vertices
 */
public class GraphImpl<I> implements Graph<EdgeImpl<I>, VertexImpl<I>, I, Double> {
    private Map<String, VertexImpl<I>> namesToVertices;
    private Map<String, ArrayList<EdgeImpl<I>>> edgeOutLists;
    private Map<String, HashMap<String, EdgeImpl<I>>> adjacencyMatrix;
    private final List<EdgeImpl<I>> edges;
    private final List<String> vertices;

    /**
     * Constructor.
     *
     * @param vertices list of vertices
     * @param edges list of edges
     * @throws IllegalArgumentException in case vertices' list contains duplicates by names
     */
    public GraphImpl(ArrayList<VertexImpl<I>> vertices, ArrayList<EdgeImpl<I>> edges)
            throws IllegalArgumentException {
        this.edges = edges;
        this.vertices = new ArrayList<>();
        builder(vertices);
    }

    /**
     * Constructor from a file.
     *
     * @param fileName from which to read a graph
     * @param mode A, L, or I as in the heading
     * @throws Exception scanner does
     */
    public GraphImpl(String fileName, char mode) throws Exception {
        File source = new File(fileName);
        Scanner scanner = new Scanner(source);
        var extractedVertices = new ArrayList<VertexImpl<I>>();
        var extractedEdges = new ArrayList<EdgeImpl<I>>();
        String line = scanner.nextLine();
        int verticesQuantity = Integer.parseInt(line);
        line = scanner.nextLine();
        for (int i = 0; i < verticesQuantity; i++) {
            String curName = line.substring(i, i + 1);
            var curVertex = new VertexImpl<I>(null, curName);
            extractedVertices.add(curVertex);
        }
        if (mode == 'A') {
            for (int i = 0; i < verticesQuantity; i++) {
                line = scanner.nextLine();
                String[] distancesStr = line.split(" ");
                for (int j = 0; j < distancesStr.length; j++) {
                    double curLength;
                    try {
                        curLength = Double.parseDouble(distancesStr[j]);
                    } catch (NumberFormatException exception) {
                        curLength = NaN;
                    }
                    if (!Double.isNaN(curLength)) {
                        var curEdge = new EdgeImpl<>(curLength, null,
                                extractedVertices.get(i), extractedVertices.get(j));
                        extractedEdges.add(curEdge);
                    }
                }
            }
        } else if (mode == 'I') {
            line = scanner.nextLine();
            int edgesQuantity = Integer.parseInt(line);
            for (int i = 0; i < edgesQuantity; i++) {
                line = scanner.nextLine();
                String[] distancesStr = line.split(" ");
                double curLength = Double.POSITIVE_INFINITY;
                int indexFrom = -1;
                int indexTo = -1;
                for (int j = 0; j < distancesStr.length; j++) {
                    try {
                        curLength = Double.parseDouble(distancesStr[j]);
                    } catch (NumberFormatException exception) {
                        curLength = NaN;
                    }
                    if (!Double.isNaN(curLength)) {
                        if (curLength > 0) {
                            indexFrom = j;
                        } else if (curLength == 0) {
                            if (indexFrom == -1) {
                                indexFrom = j;
                            } else {
                                indexTo = j;
                            }
                        } else {
                            curLength = -curLength;
                            indexTo = j;
                        }
                    }
                    if (indexFrom != -1 && indexTo != -1) {
                        break;
                    }
                }
                EdgeImpl<I> newEdge;
                if (indexTo != -1 && indexFrom != -1) {
                    newEdge = new EdgeImpl<>(curLength, null,
                            extractedVertices.get(indexFrom), extractedVertices.get(indexTo));
                    extractedEdges.add(newEdge);
                } else {
                    throw new UnsupportedOperationException("Incorrect incidence table.");
                }
            }
        } else if (mode == 'L') {
            for (int i = 0; i < verticesQuantity; i++) {
                line = scanner.nextLine();
                String[] lineSplit = line.split(" ");
                double edgesQuantity = Double.parseDouble(lineSplit[0]);
                for (int j = 1; j <= 2 * edgesQuantity; j += 2) {
                    int secondVertex = Integer.parseInt(lineSplit[j + 1]);
                    double curLength = Double.parseDouble(lineSplit[j]);
                    var newEdge = new EdgeImpl<>(curLength, null,
                            extractedVertices.get(i), extractedVertices.get(secondVertex));
                    extractedEdges.add(newEdge);
                }
            }
        } else {
            throw new UnsupportedOperationException("No support for this mode");
        }
        this.edges = extractedEdges;
        this.vertices = new ArrayList<>();
        builder(extractedVertices);
    }

    /**
     * Sorts the graph from the startingPoint
     * and prints the results to the standard out stream.
     *
     * @param startingPoint name of the vertex to start sorting from
     */
    public void sortPrinter(String startingPoint) {
        List<Map.Entry<String, Double>> topSort = topSort(namesToVertices.get(startingPoint));
        for (Map.Entry<String, Double> entry : topSort) {
            System.out.print(entry.getKey() + "(" + entry.getValue() + ") ");
        }
    }

    /**
     * Builds all the essential data structures
     * for the graph representation and
     * maintenance.
     *
     * @param vertices list of vertices
     */
    private void builder(ArrayList<VertexImpl<I>> vertices) {
        buildVertices(vertices);
        buildEdgeOutList(vertices);
        buildHashMap(vertices);
        buildAdjacencyMatrix(vertices);
    }

    /**
     * Builds a list of vertices' names.
     *
     * @param vertices list of graph's vertices
     */
    private void buildVertices(ArrayList<VertexImpl<I>> vertices) {
        for (VertexImpl<I> vertex : vertices) {
            if (this.vertices.contains(vertex.getName())) {
                throw new IllegalArgumentException("Duplicate names.");
            } else {
                this.vertices.add(vertex.getName());
            }
        }
    }

    /**
     * Builds a map from vertices' names
     * to the lists of edges starting in them.
     *
     * @param vertices list of graph's vertices
     */
    private void buildEdgeOutList(ArrayList<VertexImpl<I>> vertices) {
        edgeOutLists = new HashMap<>();
        for (VertexImpl<I> vertex : vertices) {
            var curList = new ArrayList<EdgeImpl<I>>();
            for (EdgeImpl<I> edge : edges) {
                if (Objects.equals(edge.getStart().getName(), vertex.getName())) {
                    curList.add(edge);
                }
            }
            edgeOutLists.put(vertex.getName(), curList);
        }
    }

    /**
     * Builds a map from vertices' names
     * to themselves.
     *
     * @param vertices list of graph's vertices
     */
    private void buildHashMap(ArrayList<VertexImpl<I>> vertices) {
        namesToVertices = new HashMap<>();
        for (VertexImpl<I> vertex : vertices) {
            namesToVertices.put(vertex.getName(), vertex);
        }
    }

    /**
     * Builds a map from vertices' names of
     * starting points to maps from names of
     *  ending points to the edges from starting
     *  points to ending points.
     *
     * @param vertices list of graph's vertices
     */
    private void buildAdjacencyMatrix(ArrayList<VertexImpl<I>> vertices) {
        adjacencyMatrix = new HashMap<>();
        for (VertexImpl<I> vertex : vertices) {
            var curMap = new HashMap<String, EdgeImpl<I>>();
            for (EdgeImpl<I> incidentalEdge : edgeOutLists.get(vertex.getName())) {
                curMap.put(incidentalEdge.getEnd().getName(), incidentalEdge);
            }
            adjacencyMatrix.put(vertex.getName(), curMap);
        }
    }

    /**
     * Counts edges in the graph.
     *
     * @return quantity of edges in a graph
     */
    @Override
    public int getEdgesQuantity() {
        return edges.size();
    }

    /**
     * Counts vertices in the graph.
     *
     * @return quantity of vertices in a graph
     */
    @Override
    public int getVerticesQuantity() {
        return vertices.size();
    }

    /**
     * Accesses an edge list of the graph.
     *
     * @return list of edges in a graph.
     */
    @Override
    public ArrayList<EdgeImpl<I>> getEdgesList() {
        return (ArrayList<EdgeImpl<I>>) edges;
    }

    /**
     * Accesses a vertex list of the graph.
     *
     * @return list of vertices in a graph
     */
    @Override
    public ArrayList<VertexImpl<I>> getVerticesList() {
        var result = new ArrayList<VertexImpl<I>>();
        for (String vertex : vertices) {
            result.add(namesToVertices.get(vertex));
        }
        return result;
    }

    /**
     * Accesses a mapping from names to vertices.
     *
     * @return mapping from vertices' names to vertices themselves
     */
    @Override
    public HashMap<String, VertexImpl<I>> getVertices() {
        return (HashMap<String, VertexImpl<I>>) namesToVertices;
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param name of a vertex
     * @param insideVal in vertices
     * @return vertex added
     */
    @Override
    public VertexImpl<I> addVertex(String name, I insideVal) throws IllegalArgumentException {
        VertexImpl<I> newVertex = new VertexImpl<>(insideVal, name);
        vertices.add(name);
        namesToVertices.put(name, newVertex);
        var newMap = new HashMap<String, EdgeImpl<I>>();
        adjacencyMatrix.put(name, newMap);
        ArrayList<EdgeImpl<I>> list = new ArrayList<>();
        edgeOutLists.put(name, list);
        return newVertex;
    }

    /**
     * Accesses a vertex by its name.
     *
     * @param name of a vertex
     * @return vertex itself
     */
    @Override
    public VertexImpl<I> getVertex(String name) {
        return namesToVertices.get(name);
    }

    /**
     * Deletes a vertex from the graph.
     *
     * @param vertex to delete
     */
    @Override
    public void deleteVertex(VertexImpl<I> vertex) throws NoSuchElementException {
        vertices.remove(vertex.getName());
        edges.removeIf(edge -> edge.getEnd() == vertex || edge.getStart() == vertex);
        namesToVertices.remove(vertex.getName());
        edgeOutLists.remove(vertex.getName());
        adjacencyMatrix.remove(vertex.getName());
        for (String iteratedVertex : vertices) {
            edgeOutLists.get(iteratedVertex).removeIf(edge -> edge.getStart() == vertex
                    || edge.getEnd() == vertex);
            adjacencyMatrix.get(iteratedVertex).remove(vertex.getName());
        }
    }

    /**
     * Add an edge to the graph.
     *
     * @param name of an edge
     * @param start vertex from which the edge starts
     * @param end vertex in which the edge ends
     * @param edgeLength length of an edge
     * @return edge added
     */
    @Override
    public EdgeImpl<I> addEdge(String name, VertexImpl<I> start,
                               VertexImpl<I> end, Double edgeLength) {
        var newEdge = new EdgeImpl<>(edgeLength, name, start, end);
        edges.add(newEdge);
        edgeOutLists.get(start.getName()).add(newEdge);
        adjacencyMatrix.get(start.getName()).put(end.getName(), newEdge);
        return newEdge;
    }

    /**
     * Deletes an edge from the graph.
     *
     * @param edge to delete
     */
    @Override
    public void deleteEdge(EdgeImpl<I> edge) {
        edges.remove(edge);
        for (String vertex : vertices) {
            edgeOutLists.get(vertex).remove(edge);
        }
        adjacencyMatrix.get(edge.getStart().getName()).remove(edge.getEnd().getName());
    }

    /**
     * Computes the shortest ways from each vertex to each
     * in case the vertices are of positive length.
     *
     * @return matrix of distances
     */
    public Double[][] floydWarshall() {
        Double[][] adjacencyDoubles = new Double[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                String vertexI = vertices.get(i);
                String vertexJ = vertices.get(j);
                EdgeImpl<I> curEdge = adjacencyMatrix.get(vertexI).get(vertexJ);
                if (curEdge != null) {
                    adjacencyDoubles[i][j] = curEdge.getLength();
                } else {
                    adjacencyDoubles[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        for (int i = 0; i < vertices.size(); i++) {
            adjacencyDoubles[i][i] = 0.0;
        }
        for (int k = 0; k < vertices.size(); k++) {
            for (int i = 0; i < vertices.size(); i++) {
                for (int j = 0; j < vertices.size(); j++) {
                    Double oldVal = adjacencyDoubles[i][j];
                    Double newVal = adjacencyDoubles[i][k] + adjacencyDoubles[k][j];
                    adjacencyDoubles[i][j] = oldVal > newVal ? newVal : oldVal;
                }
            }
        }
        return adjacencyDoubles;
    }

    /**
     * Checks whether the graph represented by the
     * distance matrix contains cycles of negative lengths.
     *
     * @param adjacencyDoubles matrix of distances
     * @return true if there are cycles of negative length
     */
    public boolean negativeCycles(Double[][] adjacencyDoubles) {
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (adjacencyDoubles[i][j] < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Performs a topological sort on a graph starting
     * from the startingPoint and ordering others by their
     * distance from it.
     *
     * @param startingPoint from which to sort
     * @return list of pairs Name-Length-from-the-starting-point
     */
    @Override
    public List<Map.Entry<String, Double>> topSort(VertexImpl<I> startingPoint) {
        Double[][] adjacencyDoubles = floydWarshall();
        if (negativeCycles(adjacencyDoubles)) {
            return null;
        } else {
            var resultMap = new HashMap<String, Double>();
            int startingIndex = vertices.indexOf(startingPoint.getName());
            for (int i = 0; i < vertices.size(); i++) {
                resultMap.put(vertices.get(i), adjacencyDoubles[startingIndex][i]);
            }
            List<Map.Entry<String, Double>> result = new ArrayList<>(resultMap.entrySet());
            result.sort(Map.Entry.comparingByValue());
            return result;
        }
    }
}