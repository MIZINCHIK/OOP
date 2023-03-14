package io.github.mizinchik;

import java.util.Queue;

public class PizzaJoint implements Shop<Pizza> {
    private Queue<Pizza> placedOrders;

    @Override
    public Pizza getNextOrder() {
        return null;
    }

    @Override
    public boolean ordersAvailable() {
        return false;
    }
}
