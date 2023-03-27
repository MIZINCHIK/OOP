package io.github.mizinchik.PizzaJoint;

import io.github.mizinchik.ProducerConsumer.Order;

public record Pizza(int id, int time) implements Order {
}
