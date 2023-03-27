package io.github.mizinchik.producerconsumer;

/**
 * Produces products.
 *
 * @author MIZINCHIK
 */
public interface Producer {
    /**
     * Start producing.
     */
    void produce();

    /**
     * Get the id.
     *
     * @return identifier
     */
    int id();
}
