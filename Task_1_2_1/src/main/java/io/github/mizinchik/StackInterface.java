package io.github.mizinchik;

/**
 * StackInterface describes a bunch of basic methods
 * for a work with the stack data structure.
 * The Stack class implementing these methods works with
 * general classes.
 *
 * @param <T> is a general Class Stack works with
 * @author MIZINCHIK
 */
public interface StackInterface<T> {

    /**
     * Pushes a parameter of the general type
     * o the stack.
     * Automatically reallocates the memory
     * when needed.
     * Checks whether the passed element is null.
     *
     * @param elementToPush is an element to put in the Stack
     */
    public void push(T elementToPush);

    /**
     * Pushes a parameter of the general array type
     * to the stack.
     * Automatically reallocates the memory
     * when needed.
     * Checks whether the passed array is null.
     *
     * @param stackToPush is a Stack to put into the Stack
     */
    public void pushStack(Stack<T> stackToPush);

    /**
     * Returns the last added to the stack element.
     * Checks if there are no elements left.
     *
     * @return an Object of the appropriate class
     */
    public T pop();

    /**
     * Returns the Stack size of
     * soughtSize from the top of
     * the calling Stack.
     * Checks if there are not enough
     * elements in the calling one.
     *
     * @param soughtSize is a size of a Stack to extract
     * @return a Stack of Objects of the appropriate class
     */
    public Stack<T> popStack(int soughtSize);

    /**
     * Returns the number of elements
     * currently placed in the Stack.
     *
     * @return the int number of elements in the Stack
     */
    public int count();
}
