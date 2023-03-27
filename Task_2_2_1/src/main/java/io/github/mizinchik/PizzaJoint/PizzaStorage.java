package io.github.mizinchik.PizzaJoint;

import io.github.mizinchik.ProducerConsumer.WareHouse;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PizzaStorage implements WareHouse<Pizza> {
    private final Deque<Pizza> readyOrders = new LinkedList<>();
    private final int capacity;
    private volatile int vacant;
    private int cooks;
    private final Locks locks = new Locks();

    public PizzaStorage(int capacity, int cooks) {
        this.capacity = capacity;
        this.vacant = capacity;
        this.cooks = cooks;
    }

    @Override
    public void addWare(Pizza order) throws IllegalArgumentException {
        synchronized (readyOrders) {
            if (vacant == 0) {
                throw new IllegalArgumentException("The storage can't accommodate that much pizzas");
            } else {
                vacant--;
                readyOrders.add(order);
            }
        }
    }

    public void addWares(List<Pizza> order, PizzaCook cook) {
        synchronized (readyOrders) {
            int canPut = Math.min(order.size(), this.vacant);
            Random random = new Random();
            for (int i = 0; i < canPut; i++) {
                Pizza ware = order.remove(random.nextInt(order.size()));
                addWare(ware);
                Logger.orderPutToStorage(ware, cook);
            }
        }
    }

    @Override
    public List<Pizza> giveOrder(int capacity) {
        synchronized (readyOrders) {
            List<Pizza> result = new ArrayList<>();
            for (int i = 0; i < capacity; i++) {
                Pizza pizza = readyOrders.poll();
                if (pizza == null) {
                    break;
                }
                vacant++;
                result.add(pizza);
            }
            return result;
        }
    }

    @Override
    public boolean isFull() {
        return vacant == 0;
    }

    @Override
    public boolean isEmpty() {
        return vacant == capacity;
    }

    @Override
    public Locks locks() {
        return locks;
    }

    @Override
    public boolean isWorking() {
        return cooks > 0;
    }

    public synchronized void cookLeft() {
        cooks--;
    }
}
