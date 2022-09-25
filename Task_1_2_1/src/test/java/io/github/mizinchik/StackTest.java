package io.github.mizinchik;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    Class<Integer> intObj = Integer.class;
    Stack<Integer> intStack = new Stack<Integer>(intObj);

    @Test
    @DisplayName("Reference test")
    void testReference() {
        intStack.push(2);
        assertEquals(1, intStack.count());
        intStack.push(7);
        assertEquals(2, intStack.count());
        Stack<Integer> intStackSecondary = new Stack(intObj);
        intStackSecondary.push(4);
        assertEquals(1, intStackSecondary.count());
        intStackSecondary.push(8);
        assertEquals(2, intStackSecondary.count());
        intStack.pushStack(intStackSecondary);
        assertEquals(4, intStack.count());
        assertEquals(8, intStack.pop());
        intStackSecondary = intStack.popStack(2);
        assertEquals(4, intStackSecondary.pop());
        assertEquals(7, intStackSecondary.pop());
        assertEquals(1, intStack.count());
        assertEquals(2, intStack.pop());
    }

    /*@Test
    @DisplayName("Test exceptions")
    void testNullExceptions(){
        assertEquals(0, intStack.count());
        Exception exceptionFirst = assertThrows(NullPointerException.class, () -> {
            intStack.push(null);
        });
        Exception exceptionSecond = assertThrows(NullPointerException.class, () -> {
            intStack.pushStack(null);
        });
        String expectedMessage = "Pushing null";
        String actualMessageFirst = exceptionFirst.getMessage();
        String actualMessageSecond = exceptionSecond.getMessage();
        assertTrue(actualMessageFirst.contains(expectedMessage));
        assertTrue(actualMessageSecond.contains(expectedMessage));
    }*/
}
