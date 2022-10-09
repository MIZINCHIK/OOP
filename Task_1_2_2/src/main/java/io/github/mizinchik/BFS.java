package io.github.mizinchik;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Iterator class, implements BFS algorithm for
 * iterating nodes of a tree.
 *
 * @param <T> implements the Tree interface
 * @param <E> class of the value stored in the nodes
 * @author MIZINCHIK
 */
public class BFS<T extends Tree<T, E>, E> implements Iterator<T> {
    private final ArrayDeque<T> queue;

    /**
     * Constructor of the class.
     *
     * @param root of a tree to iterate
     */
    public BFS (T root) {
        this.queue = new ArrayDeque<>();
        queue.add(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        T nextNode = queue.remove();
        ArrayList<T> offspring = nextNode.getOffspring();
        queue.addAll(offspring);
        return nextNode;
    }
}
