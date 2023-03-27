package io.github.mizinchik.ProducerConsumer;

import io.github.mizinchik.Locks;
import io.github.mizinchik.ProducerConsumer.Order;

import java.util.List;

public interface WareHouse<T extends Order> {
    void addWare(T order);
    List<T> giveOrder(int capacity);
    boolean isFull();
    boolean isEmpty();
    Locks locks();
    boolean isWorking();
}
