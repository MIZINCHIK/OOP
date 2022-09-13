package io.github.mizinchik;

import java.util.Scanner;

public class Main {

    //swaps elements in an 'int' array
    public static void swap(int[] arr, int fst, int snd){
        int tmp = arr[snd];
        arr[snd] = arr[fst];
        arr[fst] = tmp;
    }

    //repair tree from start position
    //assuming all the subtrees are ok
    public static void siftDown(int[] arr, int start, int end){
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
    public static void heapify(int[] arr, int arrayLength){
        int end = arrayLength - 1;
        int start = end / 2;
        //sift down from the last array-wise parent to the first one
        while (start >= 0){
            siftDown(arr, start, end);
            start--;
        }
    }

    //sorts an 'int' array
    public static void heapSort(int[] arr, int arrayLength){
        heapify(arr, arrayLength);
        int end = arrayLength - 1;
        while (end > 0){
            //put the last element in the root
            swap(arr, end, 0);
            end--;
            siftDown(arr, 0, end);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sequenceIn = scanner.next();
        String sequenceNew = sequenceIn.substring(1, sequenceIn.length() - 1);
        //get strings with sought ints in tokens
        String[] tokens = sequenceNew.split(",");
        int arrayLength = tokens.length;
        int[] arrayIn = new int[arrayLength];
        //creating an 'int' array from tokens
        for (int i = 0; i < arrayLength; i++){
            arrayIn[i] = Integer.parseInt(tokens[i]);
        }
        heapSort(arrayIn, arrayLength);
        //printing in a valid format
        System.out.print("{" + arrayIn[0]);
        for (int i = 1; i < arrayLength; i++){
            System.out.print("," + arrayIn[i]);
        }
        System.out.print("}");
    }
}