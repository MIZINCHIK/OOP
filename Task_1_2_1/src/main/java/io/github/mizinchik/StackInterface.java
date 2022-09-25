package io.github.mizinchik;

public interface StackInterface<T> {
    public void push(T elementToPush);
    public void pushStack(Stack<T> stackToPush);
    public T pop() throws Exception;
    public Stack<T> popStack(int soughtSize);
    public int count();
}
