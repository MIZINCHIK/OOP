package io.github.mizinchik.pizzajoint;

/**
 * Writes logs of everything happening in the pizzeria
 * to the standard output.
 *
 * @author MIZINCHIK
 */
public class Logger {
    /**
     * The order has been taken for cooking.
     *
     * @param pizza order that's been taken
     * @param cook who's cooking
     */
    public static void cookingOrder(Pizza pizza, PizzaCook cook) {
        System.out.println("The order number " + pizza.id()
                + " has started to be cooked by the cook of id "
                + cook.id());
    }

    /**
     * The order has been cooked.
     *
     * @param pizza order that's been taken
     * @param cook who's been cooking
     */
    public static void orderCooked(Pizza pizza, PizzaCook cook) {
        System.out.println("The order number " + pizza.id()
                + " has been cooked by the cook of id " + cook.id());
    }

    /**
     * The order has been put into storage.
     *
     * @param pizza order that's been taken
     * @param cook who cooked
     */
    public static void orderPutToStorage(Pizza pizza, PizzaCook cook) {
        System.out.println("The order number " + pizza.id()
                + " has been put to storage by the cook of id " + cook.id());
    }

    /**
     * The order has been taken for delivery.
     *
     * @param pizza order that's been taken
     * @param boy who's delivering
     */
    public static void deliveringOrder(Pizza pizza, PizzaDeliveryBoy boy) {
        System.out.println("The order number " + pizza.id()
                + " has been handed to deliveryboy of id " + boy.id());
    }

    /**
     * The order has been delivered.
     *
     * @param pizza order that's been taken
     * @param boy who's delivering
     */
    public static void orderDelivered(Pizza pizza, PizzaDeliveryBoy boy) {
        System.out.println("The order number " + pizza.id()
                + " has been delivered by the deliveryboy of id " + boy.id());
    }

    /**
     * The cook has terminated his shift.
     *
     * @param cook who was working at the pizzeria
     */
    public static void cookLeft(PizzaCook cook) {
        System.out.println("The cook of id " + cook.id()
                + " has left the job");
    }

    /**
     * The deliveryman has terminated his shift.
     *
     * @param delivery who was working at the pizzeria
     */
    public static void deliveryboyLeft(PizzaDeliveryBoy delivery) {
        System.out.println("The deliveryman of id " + delivery.id()
                + " has left the job");
    }

    /**
     * The cook has started his shift.
     *
     * @param cook who is working at the pizzeria
     */
    public static void cookArrived(PizzaCook cook) {
        System.out.println("The cook of id " + cook.id()
                + " has arrived at the job");
    }

    /**
     * The deliveryman has started his shift.
     *
     * @param delivery who is working at the pizzeria
     */
    public static void deliveryboyArrived(PizzaDeliveryBoy delivery) {
        System.out.println("The deliveryman of id " + delivery.id()
                + " arrived at the job");
    }
}
