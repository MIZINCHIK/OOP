package io.github.mizinchik.PizzaJoint;

import io.github.mizinchik.PizzaJoint.Pizza;
import io.github.mizinchik.PizzaJoint.PizzaCook;
import io.github.mizinchik.PizzaJoint.PizzaDeliveryBoy;

public class Logger {
    public static void cookingOrder(Pizza pizza, PizzaCook cook) {
        System.out.println("The order number " + pizza.id() + " has started to be cooked by the cook of id "
                + cook.id());
    }

    public static void orderCooked(Pizza pizza, PizzaCook cook) {
        System.out.println("The order number " + pizza.id() + " has been cooked by the cook of id " + cook.id());
    }

    public static void orderPutToStorage(Pizza pizza, PizzaCook cook) {
        System.out.println("The order number " + pizza.id() + " has been put to storage by the cook of id " + cook.id());
    }

    public static void deliveringOrder(Pizza pizza, PizzaDeliveryBoy boy) {
        System.out.println("The order number " + pizza.id() + " has been handed to deliveryboy of id " + boy.id());
    }

    public static void orderDelivered(Pizza pizza, PizzaDeliveryBoy boy) {
        System.out.println("The order number " + pizza.id() + " has been delivered by the deliveryboy of id " + boy.id());
    }

    public static void cookLeft(PizzaCook cook) {
        System.out.println("The cook of id " + cook.id()
                + " has left the job");
    }

    public static void deliveryboyLeft(PizzaDeliveryBoy delivery) {
        System.out.println("The deliveryman of id " + delivery.id()
                + " has left the job");
    }

    public static void cookArrived(PizzaCook cook) {
        System.out.println("The cook of id " + cook.id()
                + " has arrived at the job");
    }

    public static void deliveryboyArrived(PizzaDeliveryBoy delivery) {
        System.out.println("The deliveryman of id " + delivery.id()
                + " arrived at the job");
    }
}
