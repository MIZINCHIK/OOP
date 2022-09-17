package io.github.mizinchik;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

public class HeapSortTest {

    @Test
    @DisplayName("Empty array")
    void testEmpty() {
        int [] referenceArray = new int [] {};
        int [] array = new int [] {};
        HeapSort.heapSort(array);
        assertArrayEquals(referenceArray, array,
            "Sorting an empty array went wrong");
    }

    @Test
    @DisplayName("Small random array")
    void testSmallRandom() {
        int [] referenceArray = new int [] {1, 2, 3, 4, 5};
        int [] array = new int [] {5, 3, 2, 4, 1};
        HeapSort.heapSort(array);
        assertArrayEquals(referenceArray, array,
            "Sorting a small random array went wrong");
    }

    @Test
    @DisplayName("Sorted array")
    void testSorted() {
        int [] referenceArray = new int [] {1, 2, 3, 4, 5};
        int [] array = new int [] {1, 2, 3, 4, 5};
        HeapSort.heapSort(array);
        assertArrayEquals(referenceArray, array,
            "Sorting a sorted array went wrong");
    }

    @Test
    @DisplayName("Reversed array")
    void testReversed() {
        int [] referenceArray = new int [] {1, 2, 3, 4, 5};
        int [] array = new int [] {5, 4, 3, 2, 1};
        HeapSort.heapSort(array);
        assertArrayEquals(referenceArray, array,
        "Sorting a reversed array went wrong");
    }

    @Test
    @DisplayName("Large array")
    void testLargeRandom() {
        Random random = new Random();
        int [] array = new int[100000];
        for (int i = 0; i < array.length; i++){
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
