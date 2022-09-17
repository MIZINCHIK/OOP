package io.github.mizinchik;

/**
 * The ultimate heapsort class.
 * <p>
 *  This class holds a heapsort, comparison-based sorting algorithm.
 * </p>
 * <p>
 *  Heapsort sorts an array of integers using methods heapify and siftDown.
 *  heapify turns an array into a binary heap, while siftDown repairs the tree
 *  from a specific position in a heap-array.
 * </p>
 * <p>
 *  The swap method plays a very minor role of swapping elements of an integer array.
 * </p>
 * @author MIZINCHIK
 */
public class HeapSort {

    /**
     * Swaps elements in an array.
     *
     * @param arr array in whic the swap performs
     * @param first_elem first element's index
     * @param second_elem second element's index
     */
    //swaps elements in an 'int' array
    private static void swap(int[] arr, int firstElem, int secondElem) {
        int temp = arr[secondElem];
        arr[secondElem] = arr[firstEelem];
        arr[firstElem] = temp;
    }

    /**
     * Repairs a tree from a starting point to the last element of a heap.
     * We assume that every subtree of the tree lower than the start are valid.
     *
     * @param arr array to fix
     * @param start starting position of sifting
     * @param end last element in an array
     */
    private static void siftDown(int[] arr, int start, int end) {
        int leftChildInd = start * 2;
        int rightChildInd = leftChildInd + 1;
        while (leftChildInd <= end) {
            //find the largest of root, left and right children elements
            int swap = start;
            if (arr[swap] < arr[leftChildInd]) {
                swap = leftChildInd;
            }
            if ((rightChildInd <= end) && (arr[swap] < arr[rightChildInd])) {
                swap = rightChildInd;
            }
            //alright here and overall assuming subtrees are ok
            if (swap == start) {
                return;
            }
            else {
                //if the largest isn't in the root we swap so that it would be,
                //and then we continue with the swapped index to bring peace
                //to its subtree
                swap(arr, start, swap);
                start = swap;
                leftChildInd = start * 2;
                rightChildInd = leftChildInd + 1;
            }
        }
    }

    /**
     * Transforms an array into a proper binary heap via siftDown's.
     *
     * @param arr an array to heapify
     */
    //creates a correct heap order in an 'int' array
    private static void heapify(int[] arr) {
        int end = arr.length - 1;
        int start = end / 2;
        //sift down from the last array-wise parent to the first one
        while (start >= 0) {
            siftDown(arr, start, end);
            start--;
        }
    }

    /**
     * Sorts an array by swapping the last element with the root, then
     * sifting the heap down, then decreasing range while having root in it
     * and repeating the process until the root is the last man standing.
     *
     * @param arr an array to sort
     */
    //sorts an 'int' array
    public static void heapSort(int[] arr) {
        heapify(arr);
        int end = arr.length - 1;
        while (end > 0) {
            //put the last element in the root
            swap(arr, end, 0);
            end--;
            siftDown(arr, 0, end);
        }
    }
}