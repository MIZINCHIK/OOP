package io.github.mizinchik.pizzajoint;

import io.github.mizinchik.producerconsumer.Deliverer;
import java.util.List;

/**
 * Delivers order for the pizzeria.
 *
 * @author MIZINCHIK
 */
public class PizzaDeliveryBoy implements Deliverer, Runnable {
    private final int capacity;
    private final PizzaStorage storage;
    private final int id;

    /**
     * Construct a worker.
     *
     * @param capacity amount of orders they can handle
     * @param storage where they take their orders
     * @param id identifies them
     */
    public PizzaDeliveryBoy(int capacity, PizzaStorage storage, int id) {
        this.capacity = capacity;
        this.storage = storage;
        this.id = id;
    }

    /**
     * Wait for the storage to accumulate some orders,
     * get them and deliver. When all the cooks have ended up
     * with their shifts do the same.
     *
     * @throws RuntimeException if sleeping wasn't successful
     */
    @Override
    public void deliver() throws RuntimeException {
        Logger.deliveryboyArrived(this);
        while (storage.isWorking() || !storage.isEmpty()) {
            while (storage.isWorking() && storage.isEmpty()) {
                try {
                    storage.locks().waitEmpty();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            List<Pizza> order = storage.giveOrder(capacity);
            storage.locks().notifyAllProducers();
            for (Pizza pizza : order) {
                Logger.deliveringOrder(pizza, this);
            }
            for (Pizza pizza : order) {
                try {
                    Thread.sleep(pizza.time());
                    Logger.orderDelivered(pizza, this);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        Logger.deliveryboyLeft(this);
    }

    /**
     * Get the id.
     *
     * @return identification number
     */
    @Override
    public int id() {
        return id;
    }

    /**
     * Run the de-facto thread.
     */
    @Override
    public void run() {
        deliver();
    }
}
