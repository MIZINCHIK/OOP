package io.github.mizinchik.pizzajoint;

import io.github.mizinchik.producerconsumer.WareHouse;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Storage for the ready orders.
 *
 * @author MIZINCHIK
 */
public class PizzaStorage implements WareHouse<Pizza> {
    private final Deque<Pizza> readyOrders = new LinkedList<>();
    private final int capacity;
    private volatile int vacant;
    private int cooks;
    private final Locks locks = new Locks();

    /**
     * Constructs a storage.
     *
     * @param capacity max amount of orders in stock
     * @param cooks amount of workers who put orders in storage
     */
    public PizzaStorage(int capacity, int cooks) {
        this.capacity = capacity;
        this.vacant = capacity;
        this.cooks = cooks;
    }

    /**
     * Puts a single order in stock.
     *
     * @param order to put in stock
     * @throws IllegalArgumentException if there is no place for it
     */
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

    /**
     * Puts a list of pizzas in stock.
     *
     * @param order list of pizzas
     * @param cook who is cooking these orders
     */
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

    /**
     * Hand a list of orders ready for delivery.
     *
     * @param capacity maximum amount of orders to hand
     * @return list of pizzas for delivery
     */
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

    /**
     * Check if the storage is full.
     *
     * @return true if stock is full
     */
    @Override
    public boolean isFull() {
        return vacant == 0;
    }

    /**
     * Check if the storage is empty.
     *
     * @return true if empty
     */
    @Override
    public boolean isEmpty() {
        return vacant == capacity;
    }

    /**
     * Get locks for producers and consumers
     *
     * @return Lock shared between every entity in the pizzeria.
     */
    @Override
    public Locks locks() {
        return locks;
    }

    /**
     * Check if there are still cooks on duty.
     *
     * @return true if the storage can still be filled
     */
    @Override
    public boolean isWorking() {
        return cooks > 0;
    }

    /**
     * A cook has terminated his shift.
     */
    public synchronized void cookLeft() {
        cooks--;
    }
}
