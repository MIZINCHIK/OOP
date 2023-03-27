package io.github.mizinchik.producerconsumer;

/**
 * Delivers products made by Producer.
 *
 * @author MIZINCHIK
 */
public interface Deliverer {
    /**
     * Deliver products.
     */
    void deliver();

    /**
     * Get the id.
     *
     * @return identifier
     */
    int id();
}
