package io.github.mizinchik;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PizzaStorage implements WareHouse<Pizza> {
    private final Deque<Pizza> readyOrders = new LinkedList<>();
    private final int capacity;
    private int vacant;
    private final Object lockProducers = new Object();
    private final Object lockConsumers = new Object();

    public PizzaStorage(int capacity) {
        this.capacity = capacity;
        this.vacant = capacity;
    }

    public void waitFullStorage() throws InterruptedException {
        synchronized (lockProducers) {
            lockProducers.wait();
        }
    }

    public void notifyAllProducers() {
        synchronized (lockProducers) {
            lockProducers.notifyAll();
        }
    }

    public void waitEmpty() throws InterruptedException {
        synchronized (lockConsumers) {
            lockConsumers.wait();
        }
    }

    public void notifyAllConsumers() {
        synchronized (lockConsumers) {
            lockConsumers.notifyAll();
        }
    }

    @Override
    public int vacantSpace() {
        return vacant;
    }

    @Override
    public int readyWares() {
        return capacity - vacant;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void addWare(Pizza order) throws IllegalArgumentException {
        synchronized (readyOrders) {
            if (order.amount() > vacant) {
                throw new IllegalArgumentException("The storage can't accommodate that much pizzas");
            } else if (!order.ready()) {
                throw new IllegalArgumentException("The order isn't ready");
            } else {
                vacant -= order.amount();
                readyOrders.add(order);
            }
        }
    }

    @Override
    public List<Pizza> giveOrder(int capacity) {
        synchronized (readyOrders) {
            List<Pizza> order = new LinkedList<>();
            for (Pizza pizza : readyOrders) {
                if (pizza.amount() < capacity) {
                    vacant += pizza.amount();
                    capacity -= pizza.amount();
                    order.add(pizza);
                }
            }
            return order;
        }
    }
}
