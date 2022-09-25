package io.github.mizinchik;

import java.lang.reflect.Array;

public class Stack<T> implements StackInterface<T> {
    private Class<T> clazz;
    private int lastElementIndex;
    private int stackSize;
    private T[] stackContainer;

    @SuppressWarnings("unchecked")
    public Stack(Class<T> clazz){
        this.clazz = clazz;
        this.stackSize = 10;
        this.lastElementIndex = -1;
        this.stackContainer = (T[]) Array.newInstance(this.clazz, this.stackSize);
    }

    private void enoughSpaceToPop(int toPop) throws IllegalStateException{
        if (this.lastElementIndex + 1 - toPop < 0){
            throw new IllegalStateException("Not enough elements in stack\n");
        }
    }

    private void pushNotNullSingle(T elementToPush) throws NullPointerException{
        if (elementToPush == null){
            throw new NullPointerException("Pushing null\n");
        }
    }

    private void pushNotNullStack(Stack<T> stackToPush) throws NullPointerException{
        if (stackToPush == null){
            throw new NullPointerException("Pushing null\n");
        }
    }

    @SuppressWarnings("unchecked")
    private void extendContainer(int goalSpace){
        T[] newContainer = (T[]) Array.newInstance(this.clazz, goalSpace);
        System.arraycopy(this.stackContainer, 0, newContainer, 0, this.stackSize);
        this.stackContainer = newContainer;
        this.stackSize = goalSpace;
    }

    @Override
    public void push(T elementToPush){
        pushNotNullSingle(elementToPush);
        if (this.stackSize == this.lastElementIndex + 1){
            extendContainer(this.stackSize * 3 / 2);
        }
        this.stackContainer[++this.lastElementIndex] = elementToPush;
    }

    @Override
    public void pushStack(Stack<T> stackToPush){
        pushNotNullStack(stackToPush);
        int freeStackSpace = this.stackSize - this.lastElementIndex - 1;
        int stackToPushSize = stackToPush.lastElementIndex + 1;
        if (freeStackSpace < stackToPushSize){
            extendContainer((this.stackSize - freeStackSpace + stackToPushSize) * 3 / 2);
        }
        System.arraycopy(stackToPush.stackContainer, 0, this.stackContainer,
                this.lastElementIndex + 1,stackToPushSize);
        this.lastElementIndex += stackToPushSize;
    }

    @Override
    public T pop(){
        enoughSpaceToPop(1);
        return this.stackContainer[this.lastElementIndex--];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stack<T> popStack(int soughtSize){
        enoughSpaceToPop(soughtSize);
        Stack<T> newStack = new Stack<T>(this.clazz);
        T[] newContainer = (T[]) Array.newInstance(this.clazz, soughtSize * 3 / 2);
        System.arraycopy(this.stackContainer, this.lastElementIndex - soughtSize + 1,
                newContainer, 0, soughtSize);
        newStack.clazz = this.clazz;
        newStack.lastElementIndex = soughtSize - 1;
        newStack.stackSize = soughtSize * 3 / 2;
        newStack.stackContainer = newContainer;
        this.lastElementIndex -= soughtSize;
        return newStack;
    }

    @Override
    public int count(){
        return this.lastElementIndex + 1;
    }

}