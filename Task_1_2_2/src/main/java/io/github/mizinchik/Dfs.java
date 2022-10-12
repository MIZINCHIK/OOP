package io.github.mizinchik;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Iterator class, implements Dfs algorithm for
 * iterating nodes of a tree.
 *
 * @param <T> implements the Tree interface
 * @param <E> class of the value stored in the nodes
 * @author MIZINCHIK
 */
public class Dfs<T extends Tree<T, E>, E> implements Iterator<T> {
    private final ArrayDeque<T> queue;
    private final int modCount;
    private final T root;

    /**
     * Constructor of the class.
     *
     * @param root of a tree to iterate
     */
    public Dfs(T subTreeRoot) {
        this.root = subTreeRoot;
        queue = new ArrayDeque<>();
        queue.add(root);
        modCount = root.getModCount();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() throws ConcurrentModificationException {
        int curModCount = root.getModCount();
        if (curModCount != modCount){
            throw new ConcurrentModificationException();
        }
        else {
            T nextNode = queue.removeLast();
            ArrayList<T> offspring = nextNode.getOffspring();
            queue.addAll(offspring);
            return nextNode;
        }    
    }
}
