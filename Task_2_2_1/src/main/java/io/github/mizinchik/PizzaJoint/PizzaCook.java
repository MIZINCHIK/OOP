package io.github.mizinchik.PizzaJoint;

import io.github.mizinchik.ProducerConsumer.Producer;
import java.util.LinkedList;
import java.util.List;

public class PizzaCook implements Producer, Runnable {
    private final List<Pizza> order;
    private final PizzaJoint joint;
    private final PizzaStorage storage;
    private final int time;
    private final int capacity;
    private int vacant;
    private final int id;

    public PizzaCook(PizzaJoint joint, PizzaStorage storage, int time, int capacity, int id) {
        this.order = new LinkedList<>();
        this.joint = joint;
        this.storage = storage;
        this.time = time;
        this.capacity = capacity;
        this.vacant = capacity;
        this.id = id;
    }

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

    @Override
    public int id() {
        return id;
    }

    @Override
    public void run() {
        produce();
    }
}
