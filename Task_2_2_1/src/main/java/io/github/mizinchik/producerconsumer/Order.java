package io.github.mizinchik.producerconsumer;

/**
 * Order that's being made by producers
 * and delivered by deliverers.
 *
 * @author MIZINCHIK
 */
public interface Order {
    /**
     * Get the id.
     *
     * @return identifier
     */
    int id();

    /**
     * Get the time needed for delivering the order.
     *
     * @return time
     */
    int time();
}
