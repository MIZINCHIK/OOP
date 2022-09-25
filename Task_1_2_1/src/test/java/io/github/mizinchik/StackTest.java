package io.github.mizinchik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Stack class.
 * Testing its ability to handle
 * different classes and to
 * throw relevant exceptions.
 */
public class StackTest {
    Class<Integer> intObj = Integer.class;
    Class<String> stringObj = String.class;
    Stack<Integer> intStack = new Stack<Integer>(intObj);
    Stack<String> stringStack = new Stack<String>(stringObj);

    /**
     * Checks the reference test
     * with a bunch of operations.
     */
    @Test
    @DisplayName("Reference test")
    void testReference() {
        intStack.push(2);
        assertEquals(1, intStack.count());
        intStack.push(7);
        assertEquals(2, intStack.count());
        Stack<Integer> intStackSecondary = new Stack<Integer>(intObj);
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

    /**
     * Checks whether the class throws
     * NullPointerException correctly
     * in the pop methods.
     * This must happen when a user
     * passes null to mentioned functions.
     */
    @Test
    @DisplayName("Test null exceptions")
    void testNullExceptions() {
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
    }

    /**
     * Checks whether the class throws
     * IllegalStateException correctly
     * in the push methods.
     * This must happen when a user
     * tries to pop more than there is
     * in a Stack.
     */
    @Test
    @DisplayName("Test space exceptions")
    void testSpaceExceptions() {
        assertEquals(0, intStack.count());
        Exception exceptionFirst = assertThrows(IllegalStateException.class, () -> {
            intStack.pop();
        });
        Exception exceptionSecond = assertThrows(IllegalStateException.class, () -> {
            intStack.popStack(1);
        });
        String expectedMessage = "Not enough elements in stack";
        String actualMessageFirst = exceptionFirst.getMessage();
        String actualMessageSecond = exceptionSecond.getMessage();
        assertTrue(actualMessageFirst.contains(expectedMessage));
        assertTrue(actualMessageSecond.contains(expectedMessage));
    }

    /**
     * Checks whether Stack can handle
     * the String class.
     * Uses the reference Integer test
     * as a base.
     */
    @Test
    @DisplayName("Stack of Strings")
    void testStringsStack() {
        stringStack.push("two");
        assertEquals(1, stringStack.count());
        stringStack.push("se7en");
        assertEquals(2, stringStack.count());
        Stack<String> stringStackSecondary = new Stack(stringObj);
        stringStackSecondary.push("fore!");
        assertEquals(1, stringStackSecondary.count());
        stringStackSecondary.push("ate");
        assertEquals(2, stringStackSecondary.count());
        stringStack.pushStack(stringStackSecondary);
        assertEquals(4, stringStack.count());
        assertEquals("ate", stringStack.pop());
        stringStackSecondary = stringStack.popStack(2);
        assertEquals("fore!", stringStackSecondary.pop());
        assertEquals("se7en", stringStackSecondary.pop());
        assertEquals(1, stringStack.count());
        assertEquals("two", stringStack.pop());
    }
}
