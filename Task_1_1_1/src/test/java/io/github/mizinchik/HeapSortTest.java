package io.github.mizinchik;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The ultimate heapsort-testing class.
 * Provides all the tests needed to submit the lab.
 *
 * @author MIZINCHIK
 */
public class HeapSortTest {

    /**
     * Checks correctness on an empty array.
     */
    @Test
    @DisplayName("Empty array")
    void testEmpty() {
        int [] referenceArray = new int [] {};
        int [] array = new int [] {};
        HeapSort.heapSort(array);
        assertArrayEquals(referenceArray, array,
                "Sorting an empty array went wrong");
    }

    /**
     * Checks correctness on a small array with a mixed order.
     */
    @Test
    @DisplayName("Small random array")
    void testSmallRandom() {
        int [] referenceArray = new int [] {1, 2, 3, 4, 5};
        int [] array = new int [] {5, 3, 2, 4, 1};
        HeapSort.heapSort(array);
        assertArrayEquals(referenceArray, array,
                "Sorting a small random array went wrong");
    }

    /**
     * Checks correctness on a small sorted array.
     */
    @Test
    @DisplayName("Sorted array")
    void testSorted() {
        int [] referenceArray = new int [] {1, 2, 3, 4, 5};
        int [] array = new int [] {1, 2, 3, 4, 5};
        HeapSort.heapSort(array);
        assertArrayEquals(referenceArray, array,
                "Sorting a sorted array went wrong");
    }

    /**
     * Checks correctness on a small array with a reversed order.
     */
    @Test
    @DisplayName("Reversed array")
    void testReversed() {
        int [] referenceArray = new int [] {1, 2, 3, 4, 5};
        int [] array = new int [] {5, 4, 3, 2, 1};
        HeapSort.heapSort(array);
        assertArrayEquals(referenceArray, array,
                "Sorting a reversed array went wrong");
    }

    /**
     * Checks correctness on a large random array.
     */
    @Test
    @DisplayName("Large array")
    void testLargeRandom() {
        Random random = new Random();
        int [] array = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }
        int [] sortedArray = new int[100000];
        System.arraycopy(array, 0, sortedArray, 0, 100000);
        Arrays.sort(sortedArray);
        HeapSort.heapSort(array);
        assertArrayEquals(sortedArray, array,
                "Sorting a large random array went wrong");
    }
}