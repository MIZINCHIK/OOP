package io.github.mizinchik;

public interface Shop<T extends Order> {
    T getNextOrder();
    boolean ordersAvailable();
}
