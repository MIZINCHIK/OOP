package io.github.mizinchik;

import static io.github.mizinchik.TreeImpl.isBfsOverDfs;
import static io.github.mizinchik.TreeImpl.setBfs;
import static io.github.mizinchik.TreeImpl.setDfs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testing class for the TreeImpl class
 * implementing the Tree interface.
 *
 * @author MIZINCHIK
 */
public class TreeTest {
    TreeImpl<String> stringTree = new TreeImpl<>();

    /**
     * Runs a reference test.
     */
    @Test
    @DisplayName("Tests the reference code")
    void testReference() {
        assertNull(stringTree.getRoot());
        assertNull(stringTree.getParent());
        assertNull(stringTree.getValue());
        assertTrue(stringTree.getOffspring().isEmpty());
        assertFalse(isBfsOverDfs());
        TreeImpl<String> testNode = stringTree.add("A");
        assertEquals("A", testNode.getValue());
        assertNull(testNode.getParent());
        assertEquals(testNode, testNode.getRoot());
        assertNull(testNode.getParent());
        assertEquals(stringTree, testNode);
        testNode = stringTree.add("B");
        assertEquals(stringTree, testNode.getRoot());
        assertEquals(stringTree, testNode.getParent());
        assertEquals("B", testNode.getValue());
        assertTrue(testNode.getOffspring().isEmpty());
        assertEquals(testNode, stringTree.getOffspring().get(0));
        TreeImpl<String> testNode2 = stringTree.add(testNode, "AB");
        assertEquals("AB", testNode2.getValue());
        assertEquals("AB", testNode.getOffspring().get(0).getValue());
        assertEquals(stringTree, testNode2.getRoot());
        assertEquals(testNode, testNode2.getParent());
        testNode2 = stringTree.add(testNode, "BB");
        assertEquals("BB", testNode2.getValue());
        assertEquals("BB", testNode.getOffspring().get(1).getValue());
        assertEquals(stringTree, testNode2.getRoot());
        assertEquals(testNode, testNode2.getParent());
        ArrayList<String> referenceList = new ArrayList<>();
        referenceList.add("AB");
        referenceList.add("BB");
        ArrayList<String> realList = new ArrayList<>();
        assertFalse(isBfsOverDfs());
        setBfs();
        assertTrue(isBfsOverDfs());
        for (TreeImpl<String> node : stringTree) {
            if (node.getValue().contains("B") && node.getOffspring().isEmpty()) {
                realList.add(node.getValue());
            }
        }
        assertEquals(referenceList, realList);
    }

    /**
     * Tests Dfs and Bfs iterations.
     */
    @Test
    @DisplayName("Tests Dfs/Bfs")
    void testIterator() {
        stringTree = new TreeImpl<>();
        stringTree.add("A");
        TreeImpl<String> firstKnot = stringTree.add("B");
        firstKnot.add("BA");
        stringTree.add("C");
        stringTree.add("D");
        TreeImpl<String> thirdKnot = stringTree.add("E");
        assertNotNull(thirdKnot);
        firstKnot.add("BB");
        firstKnot.add("BC");
        TreeImpl<String> secondKnot = firstKnot.add("BD");
        secondKnot.add("BDA");
        secondKnot.add("BDB");
        secondKnot.add("BDC");
        secondKnot.add("BDD");
        thirdKnot.add("EA");
        thirdKnot.add("EB");
        thirdKnot.add("EC");
        thirdKnot.add("ED");
        ArrayList<String> referenceBfsArrayList = new ArrayList<>(Arrays.asList("A", "B",
                "C", "D", "E", "BA", "BB", "BC", "BD", "EA", "EB", "EC", "ED",
                "BDA", "BDB", "BDC", "BDD"));
        ArrayList<String> realListBfs = new ArrayList<>();
        setBfs();
        for (TreeImpl<String> node : stringTree) {
            realListBfs.add(node.getValue());
        }
        assertEquals(realListBfs, referenceBfsArrayList);
        ArrayList<String> referenceDfsArrayList = new ArrayList<>(Arrays.asList("A", "E",
                "ED", "EC", "EB", "EA", "D", "C", "B", "BD", "BDD", "BDC", "BDB", "BDA",
                "BC", "BB", "BA"));
        ArrayList<String> realListDfs = new ArrayList<>();
        setDfs();
        for (TreeImpl<String> node : stringTree) {
            realListDfs.add(node.getValue());
        }
        assertEquals(realListDfs, referenceDfsArrayList);
    }

    /**
     * Tests deleting nodes from a tree
     * and splitting a tree into two.
     */
    @Test
    @DisplayName("Tests deletion")
    void testDeletion() {
        TreeImpl<Integer> integerTree = new TreeImpl<>();
        integerTree.add(1);
        integerTree.add(2);
        TreeImpl<Integer> firstKnot = integerTree.add(3);
        integerTree.add(4);
        firstKnot.add(31);
        TreeImpl<Integer> secondKnot = firstKnot.add(32);
        firstKnot.add(33);
        firstKnot.add(34);
        secondKnot.add(321);
        secondKnot.add(322);
        secondKnot.add(323);
        secondKnot.add(324);
        ArrayList<TreeImpl<Integer>> testOffspring = secondKnot.getOffspring();
        TreeImpl<Integer> testNode = secondKnot.deleteNode();
        assertEquals(firstKnot, testNode);
        assertTrue(firstKnot.getOffspring().containsAll(testOffspring));
        assertEquals(integerTree, testNode.getRoot());
        testNode = firstKnot.deleteSplit();
        assertEquals(integerTree, testNode);
        assertEquals(firstKnot, firstKnot.getRoot());
        assertEquals(integerTree, integerTree.getRoot());
        for (TreeImpl<Integer> node : firstKnot) {
            assertEquals(firstKnot, node.getRoot());
        }
        for (TreeImpl<Integer> node : integerTree) {
            assertEquals(integerTree, node.getRoot());
        }
        testNode = integerTree.deleteNode();
        assertNull(testNode);
        testNode = integerTree.deleteSplit();
        assertEquals(integerTree, testNode);
    }
}
