import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        int endInd = arr.length - 1;
        int lastSwap = 100;
        T temp = null;
        while (lastSwap != -1) {
        	lastSwap = -1;
        	for (int i = 1; i <= endInd; i++) {
        		if (comparator.compare(arr[i-1], arr[i]) > 0) {
        			temp = arr[i - 1];
        			arr[i - 1] = arr[i];
        			arr[i] = temp;
        			lastSwap = i - 1;
        		}
        	}
        	endInd = lastSwap;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        T temp = null;
        int currMaxInd = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
        	currMaxInd = 0;
        	for (int j = 0; j < i; j++) {
        		if (comparator.compare(arr[j], arr[currMaxInd]) > 0) {
        			currMaxInd = j;
        		}
        	}
        	temp = arr[i];
        	arr[i] = arr[currMaxInd];
        	arr[currMaxInd] = temp;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }
}