package io.github.mizinchik;

import java.lang.reflect.Array;
import java.util.EmptyStackException;
import java.util.Objects;

/**
 *  Stack general class implements all the basic methods
 *  needed for working with the stack data structure and more.
 *  Not only you can push and pop the Objects of any java classes,
 *  but you can pass and get an appropriate Stack itself.
 *
 * @param <T> is a general Class Stack works with
 * @author MIZINCHIK
 */
public class Stack<T> implements StackInterface<T> {
    private int lastElementIndex;
    private int stackSize;
    private T[] stackContainer;

    /**
     * Constructs a Stack object from the name of a class
     * it uses.
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        stackSize = 10;
        lastElementIndex = -1;
        stackContainer = (T[]) new Object[stackSize];
    }

    private void enoughSpaceToPop(int toPop) throws EmptyStackException {
        if (lastElementIndex + 1 - toPop < 0) {
            throw new EmptyStackException();
        }
    }

    @SuppressWarnings("unchecked")
    private void changeContainerCapacity(int goalSpace) {
        T[] newContainer = (T[]) new Object[goalSpace];
        System.arraycopy(stackContainer, 0, newContainer, 0, stackSize);
        stackContainer = newContainer;
        stackSize = goalSpace;
    }

    @Override
    public void push(T elementToPush) throws NullPointerException {
        Objects.requireNonNull(elementToPush);
        if (stackSize == lastElementIndex + 1) {
            changeContainerCapacity(stackSize * 3 / 2);
        }
        stackContainer[++lastElementIndex] = elementToPush;
    }

    @Override
    public void pushStack(Stack<T> stackToPush) throws NullPointerException {
        Objects.requireNonNull(stackToPush);
        int freeStackSpace = stackSize - lastElementIndex - 1;
        int stackToPushSize = stackToPush.lastElementIndex + 1;
        if (freeStackSpace < stackToPushSize) {
            changeContainerCapacity((stackSize - freeStackSpace + stackToPushSize) * 3 / 2);
        }
        System.arraycopy(stackToPush.stackContainer, 0, stackContainer,
                lastElementIndex + 1, stackToPushSize);
        lastElementIndex += stackToPushSize;
    }

    @Override
    public T pop() throws EmptyStackException {
        enoughSpaceToPop(1);
        if (stackSize / (lastElementIndex + 1) > 2) {
            changeContainerCapacity(stackSize * 3 / 2);
        }
        return stackContainer[lastElementIndex--];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stack<T> popStack(int soughtSize) throws EmptyStackException {
        enoughSpaceToPop(soughtSize);
        Stack<T> newStack = new Stack<T>();
        T[] newContainer = (T[]) new Object[soughtSize * 3 / 2];
        System.arraycopy(stackContainer, lastElementIndex - soughtSize + 1,
                newContainer, 0, soughtSize);
        newStack.lastElementIndex = soughtSize - 1;
        newStack.stackSize = soughtSize * 3 / 2;
        newStack.stackContainer = newContainer;
        lastElementIndex -= soughtSize;
        if (stackSize / (lastElementIndex + 1) > 2) {
            changeContainerCapacity(stackSize * 3 / 2);
        }
        return newStack;
    }

    @Override
    public int count() {
        return lastElementIndex + 1;
    }

}