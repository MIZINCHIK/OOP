package io.github.mizinchik;

public record Pizza(int amount, int time, boolean ready) implements Order {
}
