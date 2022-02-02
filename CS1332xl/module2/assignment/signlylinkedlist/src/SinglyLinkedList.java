import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the front of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
    	if (data == null) {
    		throw new IllegalArgumentException();
    	}
    	if (head == null) {
    		head = new SinglyLinkedListNode<T>(data);
    		if (tail == null) {
    			tail = head;
    		}
    	} else {
    		SinglyLinkedListNode<T> newFront = new SinglyLinkedListNode<>(data);
    		newFront.setNext(head);
    		head = newFront;
    	}
    	size += 1;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
    	if (data == null) {
    		throw new IllegalArgumentException();
    	}
    	if (tail == null) {
    		tail = new SinglyLinkedListNode<T>(data);
    		if (head == null) {
    			head = tail;
    		}
    	} else {
    		SinglyLinkedListNode<T> newBack = new SinglyLinkedListNode<>(data);
    		tail.setNext(newBack);
    		tail = newBack;
    	}
    	size += 1;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
    	if (size == 0) {
    		throw new NoSuchElementException();
    	}
    	// Check if tail == head
		T data = head.getData();
    	if (size == 1) {
    		head = null;
    		tail = null;
    	} else {
    		head = head.getNext();
    	}
    	size -= 1;
    	return data;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
    	if (size == 0) {
    		throw new NoSuchElementException();
    	}
    	T data = tail.getData();
    	if (size == 1) {
    		head = null;
    		tail = null;
    	} else {
    		SinglyLinkedListNode<T> current = head;
    		while (current.getNext() != tail) {
    			current = current.getNext();
    		}
    		tail = current;
    		tail.setNext(null);
    	}
    	return data;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}