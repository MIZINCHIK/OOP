package io.github.mizinchik;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class implementing the Tree interface in
 * the most natural way.
 *
 * @param <T> class of the value stored in the nodes
 * @author MIZINCHIK
 */
public class TreeImpl<T> implements Tree<TreeImpl<T>, T> {
    private TreeImpl<T> root;
    private TreeImpl<T> parent;
    private T insideVal;
    private final ArrayList<TreeImpl<T>> nodes;
    private static boolean bfsOverDfs;

    /**
     * Constructor of the class.
     */
    public TreeImpl(){
        nodes = new ArrayList<>();
        bfsOverDfs = false;
    }

    /**
     * Adds an element as a child of
     * this node and returns the
     * corresponding node.
     *
     * @param element to be stored in the nodes
     * @return the newly created node
     */
    @Override
    public TreeImpl<T> add(T element) {
        if (root == null) {
            root = this;
            insideVal = element;
            return this;
        }
        else {
            TreeImpl<T> newNode = new TreeImpl<>();
            newNode.insideVal = element;
            newNode.parent = this;
            newNode.root = root;
            nodes.add(newNode);
            return newNode;
        }
    }

    /**
     * Adds an element as a child of
     * the tree node and returns the
     * corresponding node.
     *
     * @param tree a node the method creates child for
     * @param element to be stored in the nodes
     * @return the newly created node
     */
    @Override
    public TreeImpl<T> add(TreeImpl<T> tree, T element) throws NoSuchElementException {
        if (tree == null) {
            throw new NoSuchElementException();
        }
        else{
            return tree.add(element);
        }
    }

    /**
     * Deletes the node, makes its parent
     * the parent of all of its children.
     * Returns the parent of the deleted node.
     * Doesn't allow to delete the root.
     *
     * @return the parent of the deleted node
     */
    @Override
    public TreeImpl<T> deleteNode() {
        if (root == this) {
            return null;
        }
        else {
            for(TreeImpl<T> child : nodes) {
                child.parent = parent;
            }
            ArrayList<TreeImpl<T>> offspring = getOffspring();
            parent.nodes.remove(this);
            parent.nodes.addAll(offspring);
            return parent;
        }
    }

    /**
     * Splits a tree into two:
     * one from the original root w/o
     * the deleted node and its offspring
     * and another from the deleted node.
     * Splitting by the root changes nothing.
     *
     * @return an old root
     */
    @Override
    public TreeImpl<T> deleteSplit() {
        if ((root == null) || (root == this)) {
            return this;
        }
        else {
            TreeImpl<T> prevRoot = root;
            parent.nodes.remove(this);
            parent = null;
            root = this;
            for (TreeImpl<T> node : this) {
                node.root = this;
            }
            return prevRoot;
        }
    }

    /**
     * Returns the root of the tree
     * corresponding to the node calling
     * the method for.
     *
     * @return root of the tree
     */
    @Override
    public TreeImpl<T> getRoot() {
        return root;
    }

    /**
     * Returns the parent of a calling node.
     *
     * @return parent node
     */
    @Override
    public TreeImpl<T> getParent() {
        return parent;
    }

    /**
     * Returns the offspring of a calling node.
     *
     * @return offspring ArrayList
     */
    @Override
    public ArrayList<TreeImpl<T>> getOffspring() {
        return nodes;
    }

    /**
     * Returns the value stored in the node.
     *
     * @return value in the node
     */
    @Override
    public T getValue() {
        return insideVal;
    }

    @Override
    public Iterator<TreeImpl<T>> iterator() {
        if (bfsOverDfs) {
            return new BFS<>(root);
        }
        else {
            return new DFS<>(root);
        }
    }

    /**
     * Checks whether default iterator is BFS.
     *
     * @return true if BFS is prior to DFS and vice versa
     */
    public static boolean isBfsOverDfs() {
        return bfsOverDfs;
    }

    /**
     * Sets default iterator to BFS.
     */
    public static void setBfs() {
        bfsOverDfs = true;
    }

    /**
     * Sets default iterator to DFS.
     */
    public static  void setDfs() {
        bfsOverDfs = false;
    }
}