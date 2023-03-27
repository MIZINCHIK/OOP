package io.github.mizinchik.PizzaJoint;

import io.github.mizinchik.ProducerConsumer.Producer;
import java.util.LinkedList;
import java.util.List;

/**
 * Cooks for the pizzeria.
 *
 * @author MIZINCHIK
 */
public class PizzaCook implements Producer, Runnable {
    private final List<Pizza> order;
    private final PizzaJoint joint;
    private final PizzaStorage storage;
    private final int time;
    private int vacant;
    private final int id;

    /**
     * Constructs the worker. The vacant space changes from 0 to capacity by the
     * logic of this whole package.
     *
     * @param joint where they work
     * @param storage where they put cooked orders
     * @param time to cook one order
     * @param capacity of orders, they can cook consequently
     * @param id identification of the worker
     */
    public PizzaCook(PizzaJoint joint, PizzaStorage storage, int time, int capacity, int id) {
        this.order = new LinkedList<>();
        this.joint = joint;
        this.storage = storage;
        this.time = time;
        this.vacant = capacity;
        this.id = id;
    }

    /**
     * Take as many orders as they can handle,
     * cook them, wait for the storage to leave some unoccupied space,
     * put it there, repeat. When there are no more orders to cook,
     * they leave.
     *
     * @throws RuntimeException if sleeping didn't go well
     */
    @Override
    public void produce() throws RuntimeException {
        Logger.cookArrived(this);
        List<Pizza> order = joint.nextOrder(vacant);
        vacant -= order.size();
        while (this.order.size() != 0 || order.size() != 0) {
            for (Pizza pizza : order) {
                Logger.cookingOrder(pizza, this);
            }
            try {
                Thread.sleep((long) time * order.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (Pizza pizza : order) {
                Logger.orderCooked(pizza, this);
            }
            this.order.addAll(order);
            while(storage.isFull()) {
                try {
                    storage.locks().waitFull();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int inOrder = this.order.size();
            storage.addWares(this.order, this);
            this.vacant += inOrder - this.order.size();
            storage.locks().notifyAllConsumers();
            order = joint.nextOrder(vacant);
            vacant -= order.size();
        }
        storage.cookLeft();
        Logger.cookLeft(this);
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
        produce();
    }
}
