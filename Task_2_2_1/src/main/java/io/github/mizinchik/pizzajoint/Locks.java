package io.github.mizinchik.pizzajoint;

/**
 * Locks for producer-consumer pattern implementation.
 *
 * @author MIZINCHIK
 */
public class Locks {
    private final Object lockProducers = new Object();
    private final Object lockConsumers = new Object();

    /**
     * If the shared queue is full, producer waits until
     * the consumer has reduced the amount of products in it.
     *
     * @throws InterruptedException if thread was interrupted somehow
     */
    public void waitFull() throws InterruptedException {
        synchronized (lockProducers) {
            lockProducers.wait();
        }
    }

    /**
     * When the queue ceases to be full, notify the producers
     * for them to put the products in it.
     */
    public void notifyAllProducers() {
        synchronized (lockProducers) {
            lockProducers.notifyAll();
        }
    }

    /**
     * If the shared queue is empty, consumer waits until
     * the producer has increased the amount of products in it.
     *
     * @throws InterruptedException if thread was interrupted somehow
     */
    public void waitEmpty() throws InterruptedException {
        synchronized (lockConsumers) {
            lockConsumers.wait();
        }
    }

    /**
     * When the queue ceases to be empty, notify the consumers
     * for them to take out the products from it.
     */
    public void notifyAllConsumers() {
        synchronized (lockConsumers) {
            lockConsumers.notifyAll();
        }
    }
}
