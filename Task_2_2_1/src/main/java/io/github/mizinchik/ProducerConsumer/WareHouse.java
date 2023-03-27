package io.github.mizinchik.ProducerConsumer;

import io.github.mizinchik.PizzaJoint.Locks;
import java.util.List;

public interface WareHouse<T extends Order> {
    void addWare(T order);
    List<T> giveOrder(int capacity);
    boolean isFull();
    boolean isEmpty();
    Locks locks();
    boolean isWorking();
}
