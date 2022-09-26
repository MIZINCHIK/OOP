package io.github.mizinchik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EmptyStackException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Stack class.
 * Testing its ability to handle
 * different classes and to
 * throw relevant exceptions.
 */
public class StackTest {
    Stack<Integer> intStack = new Stack<Integer>();
    Stack<String> stringStack = new Stack<String>();

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
        Stack<Integer> intStackSecondary = new Stack<Integer>();
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
        String actualMessageFirst = exceptionFirst.getMessage();
        String actualMessageSecond = exceptionSecond.getMessage();
    }

    /**
     * Checks whether the class throws
     * EmptyStackException correctly
     * in the push methods.
     * This must happen when a user
     * tries to pop more than there is
     * in a Stack.
     */
    @Test
    @DisplayName("Test space exceptions")
    void testSpaceExceptions() {
        assertEquals(0, intStack.count());
        Exception exceptionFirst = assertThrows(EmptyStackException.class, () -> {
            intStack.pop();
        });
        Exception exceptionSecond = assertThrows(EmptyStackException.class, () -> {
            intStack.popStack(1);
        });
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
        Stack<String> stringStackSecondary = new Stack<String>();
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

    /**
     * A kinda large test to check whether
     * reallocating memory works fine or not.
     */
    @Test
    @DisplayName("Kinda large test")
    void testKindaLarge() {
        assertEquals(0, intStack.count());
        intStack.push(2);
        assertEquals(1, intStack.count());
        intStack.push(2);
        assertEquals(2, intStack.count());
        intStack.push(2);
        assertEquals(3, intStack.count());
        intStack.push(2);
        assertEquals(4, intStack.count());
        intStack.push(2);
        assertEquals(5, intStack.count());
        intStack.push(2);
        assertEquals(6, intStack.count());
        intStack.push(2);
        assertEquals(7, intStack.count());
        intStack.push(2);
        assertEquals(8, intStack.count());
        intStack.push(2);
        assertEquals(9, intStack.count());
        intStack.push(2);
        assertEquals(10, intStack.count());
        intStack.push(2);
        assertEquals(11, intStack.count());
        intStack.push(2);
        assertEquals(12, intStack.count());
        intStack.push(2);
        assertEquals(13, intStack.count());
        intStack.push(2);
        assertEquals(14, intStack.count());
        intStack.push(2);
        assertEquals(15, intStack.count());
        intStack.push(2);
        assertEquals(16, intStack.count());
        Stack<Integer> intStackSecondary = intStack.popStack(15);
        assertEquals(15, intStackSecondary.count());
        assertEquals(1, intStack.count());
        assertEquals(2, intStack.pop());
        assertEquals(0, intStack.count());
    }
}
