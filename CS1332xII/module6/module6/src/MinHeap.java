import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

     /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
    	if (data == null) {
    		throw new IllegalArgumentException();
    	}
    	if (backingArray.length == size + 1) {
    		T[] newBackingArray = (T[]) new Comparable[backingArray.length * 2];
    		for (int n = 0; n < size + 1; n++) {
    			newBackingArray[n] = backingArray[n];
    		}
    		backingArray = newBackingArray;
    	}
    	size += 1;
    	backingArray[size] = data;
    	upHeap(size);
    }
    
    private void upHeap(int index) {
    	if (index == 1) {
    		return;
    	}
    	T data = backingArray[index];
    	int parentInt = index / 2;
    	T parent = backingArray[parentInt];
    	if (parent.compareTo(data) > 0) {
    		backingArray[parentInt] = data;
    		backingArray[index] = parent;
    		upHeap(parentInt);
    	}
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
    	if (size == 0) {
    		throw new NoSuchElementException();
    	}
    	T returnData = backingArray[1];
    	backingArray[1] = backingArray[size];
    	backingArray[size] = null;
    	size -= 1;
    	downHeap(1);
    	return returnData;
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }
    
    private void downHeap(int index) {
    	T left = null;
    	T right = null;
    	boolean dataIsGreaterLeft, dataIsGreaterRight;
    	T data = backingArray[index];
    	if (index * 2 <= size) {
    		left = backingArray[index * 2];
        	dataIsGreaterLeft = data.compareTo(left) > 0;
    	} else {
    		dataIsGreaterLeft = false;
    	}
    	if (index * 2 + 1 <= size) {
        	right = backingArray[index * 2 + 1];
        	dataIsGreaterRight = data.compareTo(right) > 0;
    	} else {
    		dataIsGreaterRight = false;
    	}
    	if (left != null && right != null && dataIsGreaterLeft && dataIsGreaterRight) {
        	boolean rightIsGreater = right.compareTo(left) > 0;
    		if (rightIsGreater) {
    			backingArray[index * 2] = data;
    			backingArray[index] = left;
    			downHeap(index * 2);
    		} else {
    			backingArray[index * 2 + 1] = data;
    			backingArray[index] = right;
    			downHeap(index * 2 + 1);
    		}
    	} else if (left != null && dataIsGreaterLeft) {
    		backingArray[index * 2] = data;
    		backingArray[index] = left;
    		downHeap(index * 2);
    	} else if (right != null && dataIsGreaterRight) {
    		backingArray[index * 2 + 1] = data;
    		backingArray[index] = right;
    		downHeap(index * 2 + 1);
    	}
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}