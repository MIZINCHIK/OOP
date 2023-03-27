package io.github.mizinchik.PizzaJoint;

import io.github.mizinchik.Json.JsonReader;
import io.github.mizinchik.ProducerConsumer.Shop;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PizzaJoint implements Shop<Pizza> {
    private final Deque<Pizza> placedOrders;

    public PizzaJoint() throws Exception {
        List<Pizza> list = JsonReader.readOrders();
        placedOrders = new LinkedList<>();
        placedOrders.addAll(list);
    }

    @Override
    public List<Pizza> nextOrder(int capacity) {
        synchronized (placedOrders) {
            List<Pizza> result = new LinkedList<>();
            for (int i = 0; i < capacity; i++) {
                Pizza taken = placedOrders.poll();
                if (taken == null) {
                    return result;
                }
                result.add(taken);
            }
            return result;
        }
    }
}
