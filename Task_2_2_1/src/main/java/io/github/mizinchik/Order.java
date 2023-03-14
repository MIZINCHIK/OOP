package io.github.mizinchik;

public interface Order {
    int amount();
    int time();
    boolean ready();
}
