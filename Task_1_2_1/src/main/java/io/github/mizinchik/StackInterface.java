package io.github.mizinchik;

/**
 * @param <T>
 *           StackInterface describes a bunch of basic methods
 *           for a work with the stack data structure.
 *           The Stack class implementing these methods works with
 *           general classes.
 *
 * @author MIZINCHIK
 */
public interface StackInterface<T> {

    /**
     * @param elementToPush
     * Pushes a parameter of the general type
     * to the stack.
     * Automatically reallocates the memory
     * when needed.
     * Checks whether the passed element is null.
     */
    public void push(T elementToPush);

    /**
     * @param stackToPush
     * Pushes a parameter of the general array type
     * to the stack.
     * Automatically reallocates the memory
     * when needed.
     * Checks whether the passed array is null.
     */
    public void pushStack(Stack<T> stackToPush);

    /**
     * @return
     * Returns the last added to the stack element.
     * Checks if there are no elements left.
     */
    public T pop();

    /**
     * @param soughtSize
     * @return
     * Returns the Stack size of
     * soughtSize from the top of
     * the calling Stack.
     * Checks if there are not enough
     * elements in the calling one.
     */
    public Stack<T> popStack(int soughtSize);

    /**
     * @return
     * Returns the number of elements
     * currently placed in the Stack.
     */
    public int count();
}
