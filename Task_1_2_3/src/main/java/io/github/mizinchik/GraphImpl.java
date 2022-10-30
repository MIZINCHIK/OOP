package io.github.mizinchik;

import java.io.File;
import java.util.*;

import static java.lang.Double.NaN;

public class GraphImpl<I> implements Graph<EdgeImpl<I>, VertexImpl<I>, I, Double> {
    private HashMap<String, VertexImpl<I>> namesToVertices;
    private HashMap<String, ArrayList<EdgeImpl<I>>> edgeOutLists;
    private HashMap<String, HashMap<String, EdgeImpl<I>>> adjacencyMatrix;
    private final ArrayList<EdgeImpl<I>> edges;
    private final ArrayList<String> vertices;

    public GraphImpl(ArrayList<VertexImpl<I>> vertices, ArrayList<EdgeImpl<I>> edges) throws IllegalArgumentException {
        this.edges = edges;
        this.vertices = new ArrayList<>();
        builder(vertices);
    }

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
                    }
                    catch (NumberFormatException exception) {
                        curLength = NaN;
                    }
                    if (!Double.isNaN(curLength)) {
                        var curEdge = new EdgeImpl<>(curLength, null,
                                extractedVertices.get(i), extractedVertices.get(j));
                        extractedEdges.add(curEdge);
                    }
                }
            }
        }
        else if (mode == 'I') {
            line = scanner.nextLine();
            int edgesQuantity = Integer.parseInt(line);
            for (int i = 0; i < edgesQuantity; i++){
                line = scanner.nextLine();
                String[] distancesStr = line.split(" ");
                double curLength = Double.POSITIVE_INFINITY;
                int indexFrom = -1;
                int indexTo = -1;
                for (int j = 0; j < distancesStr.length; j++) {
                    try {
                        curLength = Double.parseDouble(distancesStr[j]);
                    }
                    catch (NumberFormatException exception) {
                        curLength = NaN;
                    }
                    if (!Double.isNaN(curLength)) {
                        if (curLength > 0) {
                            indexFrom = j;
                        }
                        else if (curLength == 0) {
                            if (indexFrom == -1) {
                                indexFrom = j;
                            }
                            else {
                                indexTo = j;
                            }
                        }
                        else {
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
                }
                else {
                    throw new UnsupportedOperationException("Incorrect incidence table.");
                }
            }
        }
        else if (mode == 'L') {
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
        }
        else {
            throw new UnsupportedOperationException("No support for this mode");
        }
        this.edges = extractedEdges;
        this.vertices = new ArrayList<>();
        builder(extractedVertices);
        line = scanner.nextLine();
        int startingPointInt = Integer.parseInt(line);
        VertexImpl<I> startingPoint = extractedVertices.get(startingPointInt);
        List<Map.Entry<String, Double>> topSort = topSort(startingPoint);
        for (Map.Entry<String, Double> entry : topSort) {
            System.out.print(entry.getKey() + "(" + entry.getValue() + ") ");
        }
    }

    private void builder(ArrayList<VertexImpl<I>> vertices) {
        buildVertices(vertices);
        buildEdgeOutList(vertices);
        buildHashMap(vertices);
        buildAdjacencyMatrix(vertices);
    }

    private void buildVertices(ArrayList<VertexImpl<I>> vertices) {
        for (VertexImpl<I> vertex : vertices) {
            if (this.vertices.contains(vertex.getName())) {
                throw new IllegalArgumentException("Duplicate names.");
            }
            else {
                this.vertices.add(vertex.getName());
            }
        }
    }

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

    private void buildHashMap(ArrayList<VertexImpl<I>> vertices){
        namesToVertices = new HashMap<>();
        for (VertexImpl<I> vertex : vertices) {
            namesToVertices.put(vertex.getName(), vertex);
        }
    }

    private void buildAdjacencyMatrix(ArrayList<VertexImpl<I>> vertices){
        adjacencyMatrix = new HashMap<>();
        for (VertexImpl<I> vertex : vertices) {
            var curMap = new HashMap<String, EdgeImpl<I>>();
            for (EdgeImpl<I> incidentalEdge : edgeOutLists.get(vertex.getName())) {
                curMap.put(incidentalEdge.getEnd().getName(), incidentalEdge);
            }
            adjacencyMatrix.put(vertex.getName(), curMap);
        }
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
    public ArrayList<EdgeImpl<I>> getEdgesList() {
        return edges;
    }

    @Override
    public ArrayList<VertexImpl<I>> getVerticesList() {
        var result = new ArrayList<VertexImpl<I>>();
        for (String vertex : vertices) {
            result.add(namesToVertices.get(vertex));
        }
        return result;
    }

    @Override
    public HashMap<String, VertexImpl<I>> getVertices() {
        return namesToVertices;
    }

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

    @Override
    public VertexImpl<I> getVertex(String name) {
        return namesToVertices.get(name);
    }

    @Override
    public void deleteVertex(VertexImpl<I> vertex) throws NoSuchElementException {
        vertices.remove(vertex.getName());
        edges.removeIf(edge -> edge.getEnd() == vertex || edge.getStart() == vertex);
        namesToVertices.remove(vertex.getName());
        edgeOutLists.remove(vertex.getName());
        adjacencyMatrix.remove(vertex.getName());
        for (String iteratedVertex : vertices) {
            edgeOutLists.get(iteratedVertex).removeIf(edge -> edge.getStart() == vertex || edge.getEnd() == vertex);
            adjacencyMatrix.get(iteratedVertex).remove(vertex.getName());
        }
    }

    @Override
    public EdgeImpl<I> addEdge(String name, VertexImpl<I> start, VertexImpl<I> end, Double edgeLength) {
        var newEdge = new EdgeImpl<>(edgeLength, name, start, end);
        edges.add(newEdge);
        edgeOutLists.get(start.getName()).add(newEdge);
        adjacencyMatrix.get(start.getName()).put(end.getName(), newEdge);
        return newEdge;
    }

    @Override
    public void deleteEdge(EdgeImpl<I> edge) {
        edges.remove(edge);
        for (String vertex : vertices) {
            edgeOutLists.get(vertex).remove(edge);
        }
        adjacencyMatrix.get(edge.getStart().getName()).remove(edge.getEnd().getName());
    }

    public Double[][] floydWarshall() {
        Double[][] adjacencyDoubles = new Double[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                String vertexI = vertices.get(i);
                String vertexJ = vertices.get(j);
                EdgeImpl<I> curEdge = adjacencyMatrix.get(vertexI).get(vertexJ);
                if (curEdge != null) {
                    adjacencyDoubles[i][j] = curEdge.getLength();
                }
                else {
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

    @Override
    public List<Map.Entry<String, Double>> topSort(VertexImpl<I> startingPoint) {
        Double[][] adjacencyDoubles = floydWarshall();
        if (negativeCycles(adjacencyDoubles)) {
            return null;
        }
        else {
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