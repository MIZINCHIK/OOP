package io.github.mizinchik.producerconsumer;

import io.github.mizinchik.pizzajoint.Locks;
import java.util.List;

/**
 * WareHouse where producers put
 * and deliverers take ready orders.
 *
 * @author MIZINCHIK
 * @param <T> Product
 */
public interface WareHouse<T extends Order> {
    /**
     * Add an order to the stock.
     *
     * @param order that's been produced
     */
    void addWare(T order);

    /**
     * Hand the orders for delivery.
     *
     * @param capacity max amount of orders to hand
     * @return list of orders
     */
    List<T> giveOrder(int capacity);

    /**
     * Check if the storage is full.
     *
     * @return true if stock is full
     */
    boolean isFull();

    /**
     * Check if the storage is empty.
     *
     * @return true if empty
     */
    boolean isEmpty();

    /**
     * Get locks for producers and consumers.
     *
     * @return Lock shared between every entity in the pizzeria.
     */
    Locks locks();

    /**
     * Check if there are still cooks on duty.
     *
     * @return true if the storage can still be filled
     */
    boolean isWorking();
}
