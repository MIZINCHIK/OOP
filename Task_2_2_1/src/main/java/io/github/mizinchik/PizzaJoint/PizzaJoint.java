package io.github.mizinchik.PizzaJoint;

import io.github.mizinchik.PizzaJoint.Json.JsonReader;
import io.github.mizinchik.ProducerConsumer.Shop;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Pizzeria. Cooks cook, delivery boys deliver.
 * Concurrent.
 *
 * @author MIZINCHIK
 */
public class PizzaJoint implements Shop<Pizza> {
    private final Deque<Pizza> placedOrders;

    /**
     * Creates a joint. Takes the orders from the
     * respective json file.
     *
     * @throws Exception if deserialization was not successful
     */
    public PizzaJoint() throws Exception {
        List<Pizza> list = JsonReader.readOrders();
        placedOrders = new LinkedList<>();
        placedOrders.addAll(list);
    }

    /**
     * Gives maximum amount of orders from the inner list
     * in the same form.
     *
     * @param capacity maximum amount of orders
     * @return list of orders
     */
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
