import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;


/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting {

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    	T[] sorted = rMergeSort(arr, comparator);
    	for (int i = 0; i < arr.length; i++) {
    		arr[i] = sorted[i];
    	}
    }
    
    private static <T> T[] rMergeSort(T[] arr, Comparator<T> comparator) {
    	if (arr.length == 1 || arr.length == 0) {
    		return arr;
    	} else if (arr.length == 2) {
    		if (comparator.compare(arr[0], arr[1]) > 0) {
    			T temp = arr[1];
    			arr[1] = arr[0];
    			arr[0] = temp;
    		}
    		return arr;
    	} else {
    		int split = arr.length / 2;
    		T[] left = sliceArray(arr, 0, split);
    		T[] right = sliceArray(arr, split, arr.length);

    		
    		left = rMergeSort(left, comparator);
    		right = rMergeSort(right, comparator);
    		mergeArrays(arr, left, right, comparator);
    	}
    	return arr;
    }
    
    private static <T> T[] sliceArray(T[] arr, int start, int end) {
    	T[] sliced = (T[]) new Object[end - start];
    	for (int i=0; i < sliced.length; i++) {
    		sliced[i] = arr[start + i];
    	}
    	return sliced;
    }
    
    private static <T> void mergeArrays(T[] merged, T[] leftArr, T[] rightArr,  Comparator<T> comparator) {
    	int i = 0;
    	int j = 0;
    	    	
    	while (i < leftArr.length && j < rightArr.length) {
    		int compared = comparator.compare(leftArr[i], rightArr[j]);
    		if (compared == 0) {
    			merged[i+j] = leftArr[i];
    			i += 1;
    			merged[i+j] = rightArr[j];
    			j += 1;
    		} else if (compared < 0) {
    			merged[i+j] = leftArr[i];
    			i += 1;
    		} else {
    			merged[i+j] = rightArr[j];
    			j += 1;
    		}
    	}
    	
    	while (i < leftArr.length) {
			merged[i+j] = leftArr[i];
			i += 1;		
    	}
    	
    	while (j < rightArr.length) {
			merged[i+j] = rightArr[j];
			j += 1;   		
    	}
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    	rLSDRadixSort(arr);
    }
    
    private static void rLSDRadixSort(int[] arr) {
    	int maxDigits = 0;
    	for (Integer num: arr) {
    		int thisDigits = num.toString().length();
    		if (maxDigits < thisDigits) {
    			maxDigits = thisDigits;
    		}
    	}

    	
    	for (int i = 0; i < maxDigits; i++) {
    		int modVal = (int) Math.pow(10, i);
        	ArrayList<Integer>[] sorted = new ArrayList[19];
        	for (int j = 0; j < sorted.length; j++) {
        		sorted[j] = new ArrayList<Integer>();
        	}
        	for (int j=0; j < arr.length; j++) {
        		int place = (arr[j] / modVal) % 10;
        		sorted[place + 9].add(arr[j]);
        	}
        	int j = 0;
        	for (int k=0; k < sorted.length; k++) {
        		boolean empty = sorted[k].isEmpty();
        		while (!sorted[k].isEmpty()) {
        			arr[j] = sorted[k].remove(0);
        			j += 1;
        		}
        	}
    	}
    	
    }
}