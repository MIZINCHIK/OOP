package io.github.mizinchik;

public class Locks {
    private final Object lockProducers = new Object();
    private final Object lockConsumers = new Object();

    public void waitFull() throws InterruptedException {
        synchronized (lockProducers) {
            lockProducers.wait();
        }
    }

    public void notifyAllProducers() {
        synchronized (lockProducers) {
            lockProducers.notifyAll();
        }
    }

    public void waitEmpty() throws InterruptedException {
        synchronized (lockConsumers) {
            lockConsumers.wait();
        }
    }

    public void notifyAllConsumers() {
        synchronized (lockConsumers) {
            lockConsumers.notifyAll();
        }
    }
}
