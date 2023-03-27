package io.github.mizinchik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.mizinchik.pizzajoint.json.JsonReader;
import io.github.mizinchik.pizzajoint.json.JsonWriter;
import io.github.mizinchik.pizzajoint.Pizza;
import io.github.mizinchik.pizzajoint.PizzaDeliveryBoy;
import io.github.mizinchik.pizzajoint.PizzaCook;
import io.github.mizinchik.pizzajoint.PizzaJoint;
import io.github.mizinchik.pizzajoint.PizzaStorage;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the pizzeria made with a producer-consumer pattern.
 *
 * @author MIZINCHIK
 */
public class TestPizza {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Changes standard output stream before each method.
     */
    @BeforeEach
    public void setUpOut() {
        System.setOut(new PrintStream(out));
    }

    /**
     * Restores standard output system after each method.
     */
    @AfterEach
    public void restoreOut() {
        System.setOut(originalOut);
    }

    /**
     * Tests the ability of the Pizzeria to correctly spread the orders
     * and complete all of them and terminate itself.
     *
     * @throws Exception if something went wrong with the threads or de/serializing.
     */
    @Test
    @DisplayName("JSON logs' test")
    void testJson() throws Exception {
        Random random = new Random();
        List<Pizza> pizzas = new ArrayList<>();
        int capacity = 10;
        int threads = 100;
        for (int i = 0; i < 10 * threads; i++) {
            Pizza pizza = new Pizza(i, random.nextInt(1000) + 1000);
            pizzas.add(pizza);
        }
        JsonWriter.write(pizzas, JsonReader.pizzas);
        PizzaJoint joint = new PizzaJoint();
        PizzaStorage storage = new PizzaStorage(3, 5);
        List<PizzaCook> cooks = new ArrayList<>();
        List<PizzaDeliveryBoy> delivery = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            PizzaCook cook = new PizzaCook(joint, storage, random.nextInt(1000) + 1000,
                    random.nextInt(capacity / 2) + 1, i);
            PizzaDeliveryBoy boy = new PizzaDeliveryBoy(random.nextInt(capacity / 2) + 1, storage, i);
            cooks.add(cook);
            delivery.add(boy);
        }
        ExecutorService threadPool = Executors.newFixedThreadPool(threads * 2);
        for (PizzaCook cook : cooks) {
            threadPool.submit(cook);
        }
        for (PizzaDeliveryBoy boy : delivery) {
            threadPool.submit(boy);
        }
        threadPool.shutdown();
        assertTrue(threadPool.awaitTermination(1, TimeUnit.DAYS));
        for (int i = 0; i < 10 * threads; i++) {
            String string = "The order number " + i + " has been cooked";
            assertTrue(out.toString().contains(string));
            assertEquals(1, StringUtils.countMatches(out.toString(), string));
        }
    }
}