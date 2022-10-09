package io.github.mizinchik;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Interface of an n-ary Tree data structure.
 * Provides methods for adding and deleting
 * nodes, for splitting trees and iterating
 * the whole tree.
 *
 * @param <T> A class implementing Tree
 * @param <E> A general class in the nodes of the Tree
 * @author MIZINCHIK
 */
public interface Tree<T extends Tree<T, E>, E> extends Iterable<T> {
    /**
     * Adds an element as a child of
     * this node and returns the
     * corresponding node.
     *
     * @param element to be stored in the nodes
     * @return the newly created node
     */
    T add(E element);

    /**
     * Adds an element as a child of
     * the tree node and returns the
     * corresponding node.
     *
     * @param tree a node the method creates child for
     * @param element to be stored in the nodes
     * @return the newly created node
     */
    T add(T tree, E element);

    /**
     * Deletes the node, makes its parent
     * the parent of all of its children.
     * Returns the parent of the deleted node.
     * Doesn't allow to delete the root.
     *
     * @return the parent of the deleted node
     */
    T deleteNode();

    /**
     * Splits a tree into two:
     * one from the original root w/o
     * the deleted node and its offspring
     * and another from the deleted node.
     * Splitting by the root changes nothing.
     *
     * @return an old root
     */
    T deleteSplit();

    /**
     * Returns the root of the tree
     * corresponding to the node calling
     * the method for.
     *
     * @return root of the tree
     */
    T getRoot();

    /**
     * Returns the parent of a calling node.
     *
     * @return parent node
     */
    T getParent();

    /**
     * Returns the offspring of a calling node.
     *
     * @return offspring ArrayList
     */
    ArrayList<T> getOffspring();

    /**
     * Returns the value stored in the node.
     *
     * @return value in the node
     */
    E getValue();

    @Override
    Iterator<T> iterator();
}
