package io.github.mizinchik;

public class PizzaDeliveryBoy implements Deliverer, Runnable {
    private final int speed;
    private final int amount;
    private PizzaStorage storage;

    public PizzaDeliveryBoy(int speed, int amount, PizzaStorage storage) {
        this.speed = speed;
        this.amount = amount;
    }

    @Override
    public void deliver() {
    }

    @Override
    public void run() {
        deliver();
    }
}
