package io.github.mizinchik.producerconsumer;

import java.util.List;

/**
 * Shop where the producers take their orders.
 *
 * @author MIZINCHIK
 * @param <T> Product
 */
public interface Shop<T extends Order> {
    /**
     * Get the list of orders for producing.
     *
     * @param capacity max amount of orders to hand
     * @return list of orders
     */
    List<T> nextOrder(int capacity);
}
