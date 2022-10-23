package io.github.mizinchik;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class GraphInteger<I> extends GraphImpl<I, Integer> {
    public void GraphIntegerParser(String fileName, char mode) throws Exception {
        File source = new File(fileName);
        Scanner scanner = new Scanner(source);
        GraphInteger<I> graph = new GraphInteger<>();
        var vertices = new ArrayList<VertexImpl<I, Integer>>();
        var edges = new ArrayList<EdgeImpl<I, Integer>>();
        graph.setVerticesAndEdges(vertices, edges);
        if (mode == 'A') {
            String line = scanner.nextLine();
            int verticesQuantity = Integer.parseInt(line);
            line = scanner.nextLine();
            for (int i = 0; i < verticesQuantity; i++) {
                String curName = line.substring(i, i + 1);
                graph.addVertex(curName, null);
            }
            for (int i = 0; i < verticesQuantity; i++) {
                line = scanner.nextLine();
                String[] distancesStr = line.split(" ");
                for (int j = 0; j < distancesStr.length; j++) {
                    Integer curLength = Integer.parseInt(distancesStr[i]);
                    if (curLength != 0) {
                        graph.addEdge(null, vertices.get(i), vertices.get(j), curLength);
                    }
                }
            }
        }
        else if (mode == 'I') {
            String line = scanner.nextLine();
            int verticesQuantity = Integer.parseInt(line);
            line = scanner.nextLine();
            for (int i = 0; i < verticesQuantity; i++) {
                String curName = line.substring(i, i + 1);
                graph.addVertex(curName, null);
            }
            int edgesQuantity = Integer.parseInt(line);
            for (int i = 0; i < edgesQuantity; i++){
                line = scanner.nextLine();
                String[] distancesStr = line.split(" ");
                EdgeImpl<I, Integer> newEdge;
                int indexFrom = -1;
                int valueFrom = 0;
                int indexTo = -1;
                int valueTo = 0;
                for (int j = 0; j < distancesStr.length; j++) {
                    if (Integer.parseInt(distancesStr[j]) > 0) {
                        indexTo = j;
                        valueTo = Integer.parseInt(distancesStr[j]);
                    }
                    else if (Integer.parseInt(distancesStr[j]) < 0) {
                        indexFrom = j;
                        valueFrom = Integer.parseInt(distancesStr[j]);
                    }
                }
                if (indexTo == -1 || indexFrom == -1 || valueTo == 0 || valueFrom == 0 || valueTo != -valueFrom){
                    throw new IllegalArgumentException("Unsupported input value.");
                }
                else {
                    graph.addEdge(null, vertices.get(indexFrom), vertices.get(indexTo), valueTo);
                }
            }
        }
        else if (mode == 'L') {
            String line = scanner.nextLine();
            int verticesQuantity = Integer.parseInt(line);
            line = scanner.nextLine();
            for (int i = 0; i < verticesQuantity; i++) {
                String curName = line.substring(i, i + 1);
                graph.addVertex(curName, null);
            }
            for (int i = 0; i < verticesQuantity; i++) {
                line = scanner.nextLine();
                String[] lineSplit = line.split(" ");
                int edgesQuantity = Integer.parseInt(lineSplit[0]);
                for (int j = 1; j <= 2 * edgesQuantity; j += 2) {
                    int secondVertex = Integer.parseInt(lineSplit[j]);
                    int curLength = Integer.parseInt(lineSplit[j + 1]);
                    graph.addEdge(null, vertices.get(i), vertices.get(secondVertex), curLength);
                }
            }
        }
        else {
            throw new UnsupportedOperationException("No support for this mode");
        }
    }
}
