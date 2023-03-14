package io.github.mizinchik;

import java.util.Objects;

public final class Pizza implements Order {
    private final int amount;
    private final int time;
    private final boolean ready;

    public Pizza(int amount, int time, boolean ready) {
        this.amount = amount;
        this.time = time;
        this.ready = ready;
    }

    @Override
    public int amount() {
        return amount;
    }

    @Override
    public int time() {
        return time;
    }

    @Override
    public boolean ready() {
        return ready;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Pizza) obj;
        return this.amount == that.amount &&
                this.time == that.time &&
                this.ready == that.ready;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, time, ready);
    }

    @Override
    public String toString() {
        return "Pizza[" +
                "amount=" + amount + ", " +
                "time=" + time + ", " +
                "ready=" + ready + ']';
    }

}
