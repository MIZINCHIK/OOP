package io.github.mizinchik.PizzaJoint;

import io.github.mizinchik.Logger;
import io.github.mizinchik.ProducerConsumer.Deliverer;

import java.util.List;

public class PizzaDeliveryBoy implements Deliverer, Runnable {
    private final int capacity;
    private final PizzaStorage storage;
    private final int id;

    public PizzaDeliveryBoy(int capacity, PizzaStorage storage, int id) {
        this.capacity = capacity;
        this.storage = storage;
        this.id = id;
    }

    @Override
    public void deliver() throws RuntimeException {
        Logger.deliveryboyArrived(this);
        while(storage.isWorking() || !storage.isEmpty()) {
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

    @Override
    public int id() {
        return id;
    }

    @Override
    public void run() {
        deliver();
    }
}
