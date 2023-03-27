package io.github.mizinchik.PizzaJoint;

import io.github.mizinchik.ProducerConsumer.Order;

/**
 * Pizzas that are cooked at the pizzeria.
 *
 * @author MIZINCHIK
 * @param id identifies the order
 * @param time needed to deliver the order in milliseconds
 */
public record Pizza(int id, int time) implements Order {
}
