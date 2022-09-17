package io.github.mizinchik;

import java.util.Scanner;

public class HeapSort {

    //swaps elements in an 'int' array
    private static void swap(int[] arr, int first_elem, int second_elem){
        int temp = arr[second_elem];
        arr[second_elem] = arr[first_elem];
        arr[first_elem] = temp;
    }

    //repair tree from start position
    //assuming all the subtrees are ok
    private static void siftDown(int[] arr, int start, int end){
        int leftChildInd = start * 2;
        int rightChildInd = leftChildInd + 1;
        while (leftChildInd <= end){
            //find the largest of root, left and right children elements
            int swap = start;
            if (arr[swap] < arr[leftChildInd]){
                swap = leftChildInd;
            }
            if ((rightChildInd <= end) && (arr[swap] < arr[rightChildInd])){
                swap = rightChildInd;
            }
            //alright here and overall assuming subtrees are ok
            if (swap == start){
                return;
            }
            //if the largest isn't in the root we swap so that it would be,
            //and then we continue with the swapped index to bring peace
            //to its subtree
            else{
                swap(arr, start, swap);
                start = swap;
                leftChildInd = start * 2;
                rightChildInd = leftChildInd + 1;
            }
        }
    }

    //creates a correct heap order in an 'int' array
    private static void heapify(int[] arr){
        int end = arr.length - 1;
        int start = end / 2;
        //sift down from the last array-wise parent to the first one
        while (start >= 0){
            siftDown(arr, start, end);
            start--;
        }
    }

    //sorts an 'int' array
    public static void heapSort(int[] arr){
        heapify(arr);
        int end = arr.length - 1;
        while (end > 0){
            //put the last element in the root
            swap(arr, end, 0);
            end--;
            siftDown(arr, 0, end);
        }
    }
}