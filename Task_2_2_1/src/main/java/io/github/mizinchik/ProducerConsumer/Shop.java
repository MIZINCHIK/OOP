package io.github.mizinchik.ProducerConsumer;

import io.github.mizinchik.ProducerConsumer.Order;

import java.util.List;

public interface Shop<T extends Order> {
    List<T> nextOrder(int capacity);
}
