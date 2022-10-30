package io.github.mizinchik;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for GraphImpl, EdgeImpl
 * & VertexImpl classes.
 */
public class GraphTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Changes standard output stream before each method.
     */
    @BeforeEach
    public void setUpOut() {
        System.setOut(new PrintStream(out));
    }

    /**
     * Restores standard output system after each method.
     */
    @AfterEach
    public void restoreOut() {
        System.setOut(originalOut);
    }

    /**
     * Performs a reference test for our task.
     *
     * @throws Exception scanner does it
     */
    @Test
    @DisplayName("Tests the reference example")
    void testReference() throws Exception {
        var newGraph = new GraphImpl<Double>("./src/main/resources/AdjacentMatrix.txt", 'A');
        assertEquals(7, newGraph.getVerticesQuantity());
        assertEquals("C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) ", out.toString());
        newGraph = new GraphImpl<>("./src/main/resources/IncidenceMatrix.txt", 'I');
        assertEquals(7, newGraph.getVerticesQuantity());
        assertEquals("C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) ", out.toString());
        newGraph = new GraphImpl<>("./src/main/resources/AdjacencyList.txt", 'L');
        assertEquals(7, newGraph.getVerticesQuantity());
        assertEquals("C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) ", out.toString());
        assertThrows(UnsupportedOperationException.class, () ->
                new GraphImpl<>("./src/main/resources/AdjacencyList.txt", 'l'));
    }

    /**
     * Tests VertexImpl class.
     */
    @Test
    @DisplayName("VertexImpl class test")
    void testVertex() {
        var newVertex = new VertexImpl<>(5, "5");
        assertEquals(5, newVertex.getValue());
        assertEquals("5", newVertex.getName());
        newVertex.changeValue(6);
        assertEquals(6, newVertex.getValue());
    }

    /**
     * Tests EdgeImpl class.
     */
    @Test
    @DisplayName("EdgeImpl class test")
    void testEdge() {
        var start = new VertexImpl<Integer>(null, "start");
        var end = new VertexImpl<Integer>(null, "end");
        var newEdge = new EdgeImpl<>(10.0, null, start, end);
        assertEquals(10.0, newEdge.getLength());
        newEdge.changeLength(12.5);
        assertEquals(12.5, newEdge.getLength());
        newEdge.changeLength(NaN);
        assertEquals(NaN, newEdge.getLength());
        assertNull(newEdge.getName());
        assertEquals(start, newEdge.getStart());
        assertEquals(end, newEdge.getEnd());
    }

    /**
     * Tests standard methods of Graph interface.
     *
     * @throws Exception scanner does it
     */
    @Test
    @DisplayName("GraphImpl class test")
    void testGraph() throws Exception {
        var newGraph = new GraphImpl<Double>("./src/main/resources/AdjacentMatrix.txt", 'A');
        assertEquals(7, newGraph.getVerticesQuantity());
        assertEquals(20, newGraph.getEdgesQuantity());
        VertexImpl<Double> newVertex = newGraph.addVertex("New", null);
        List<Map.Entry<String, Double>> list =  newGraph.topSort(newGraph.getVertex("C"));
        for (Map.Entry<String, Double> entry : list) {
            System.out.print(entry.getKey() + "(" + entry.getValue() + ") ");
        }
        assertEquals("C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) New(Infinity) ",
                out.toString());
        var edge1 = newGraph.addEdge(null, newGraph.getVertex("C"), newVertex, -1.0);
        var edge2 = newGraph.addEdge(null, newVertex, newGraph.getVertex("C"), -1.0);
        newGraph.deleteEdge(edge1);
        newGraph.deleteEdge(edge2);
        list =  newGraph.topSort(newGraph.getVertex("C"));
        for (Map.Entry<String, Double> entry : list) {
            System.out.print(entry.getKey() + "(" + entry.getValue() + ") ");
        }
        assertEquals("C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) New(Infinity) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) New(Infinity) ",
                out.toString());
        newGraph.addEdge(null, newGraph.getVertex("C"), newGraph.getVertex("New"), -1.0);
        newGraph.addEdge(null, newGraph.getVertex("New"), newGraph.getVertex("C"), -1.0);
        assertNull(newGraph.topSort(newGraph.getVertex("C")));
        newGraph.deleteVertex(newGraph.getVertex("New"));
        list =  newGraph.topSort(newGraph.getVertex("C"));
        for (Map.Entry<String, Double> entry : list) {
            System.out.print(entry.getKey() + "(" + entry.getValue() + ") ");
        }
        assertEquals("C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) New(Infinity) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) New(Infinity) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) ", out.toString());
        HashMap<String, VertexImpl<Double>> vertices = newGraph.getVertices();
        assertTrue(vertices.containsKey("C"));
        assertFalse(vertices.containsKey("New"));
        ArrayList<EdgeImpl<Double>> oldEdges = newGraph.getEdgesList();
        ArrayList<VertexImpl<Double>> oldVertices = newGraph.getVerticesList();
        var superNewGraph = new GraphImpl<>(oldVertices, oldEdges);
        list =  superNewGraph.topSort(newGraph.getVertex("C"));
        for (Map.Entry<String, Double> entry : list) {
            System.out.print(entry.getKey() + "(" + entry.getValue() + ") ");
        }
        assertEquals("C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) New(Infinity) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) New(Infinity) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) "
                + "C(0.0) D(2.0) E(4.0) F(5.0) G(9.0) B(10.0) A(14.0) ", out.toString());
    }
}
