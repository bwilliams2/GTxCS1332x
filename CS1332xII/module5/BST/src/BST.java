import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
    	if (data == null) {
    		throw new IllegalArgumentException();
    	}
    	root = radd(data, root);
    }
    
    private BSTNode<T> radd(T data, BSTNode<T> node) {
    	if (node == null) {
    		size += 1;
    		return new BSTNode<T>(data);
    	}
    	if (data.equals(node.getData())) {
    		return node;
    	} else if (data.compareTo(node.getData()) > 0) {
    		node.setRight(radd(data, node.getRight()));
    	} else {
    		node.setLeft(radd(data, node.getLeft()));
    	}
    	return node;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
    	T nodeData;
    	if (data == null) {
    		throw new IllegalArgumentException();
    	} else if (root != null && root.getData().equals(data)) {
    		size -= 1;
    		BSTNode<T> successor;
    		nodeData = root.getData();
	    	if (root.getLeft() == null && root.getRight() == null) {
	    		root = null;
	    	} else if (root.getLeft() != null && root.getRight() == null) {
	    		root = root.getLeft();
	    	} else if (root.getRight() != null && root.getLeft() == null) {
	    		root = root.getRight();
	    	} else {
				if (root.getRight().getLeft() == null) {
					successor = root.getRight();
					successor.setLeft(root.getLeft());
					root = successor;
				} else {
    				successor = findSuccessor(root.getRight());
    				root.setData(successor.getData());
				}
	    	}
	    	return nodeData;
    	} else {
            nodeData = findRemove(data, root);
            // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD  HEADER)!
            size -= 1;
            return nodeData;
        }
    }
    
    private T findRemove(T data, BSTNode<T> node) {
    	if (node == null) {
    		throw new NoSuchElementException();
    	}	
    	// Check 
    	BSTNode<T> successor;
    	Integer dataVal = node.getData().compareTo(data);
    	if (dataVal < 0) {
    		// search right child
    		BSTNode<T> rightChild = node.getRight();
    		if (rightChild == null) {
    			throw new NoSuchElementException();
    		} else if (rightChild.getData().equals(data)) {
    			T nodeData = rightChild.getData();
    			if (rightChild.getLeft() != null && rightChild.getRight() != null) {
    				if (rightChild.getRight().getLeft() == null) {
    					successor = rightChild.getRight();
    					successor.setLeft(rightChild.getLeft());
    					node.setRight(successor);
    				} else {
        				successor = findSuccessor(rightChild.getRight());
        				rightChild.setData(successor.getData());
    				}
    			} else if (rightChild.getRight() == null) {
    				node.setRight(rightChild.getLeft());
    			} else {
    				node.setRight(rightChild.getRight());
    			}
				return nodeData;

    		} else {
    			return findRemove(data, rightChild);
    		}
    	} else if (dataVal == 0) {
    		throw new NoSuchElementException();
    	} else {
			// search left child
			BSTNode<T> leftChild = node.getLeft();
			if (leftChild == null) {
				throw new NoSuchElementException();
			} else if (leftChild.getData().equals(data)) {
				T nodeData = leftChild.getData();
				if (leftChild.getLeft() != null && leftChild.getRight() != null) {
					if (leftChild.getRight().getLeft() == null) {
						successor = leftChild.getRight();
						successor.setLeft(leftChild.getLeft());
						node.setLeft(successor);
					} else {
	    				successor = findSuccessor(leftChild.getRight());
	    				leftChild.setData(successor.getData());
					}
				} else if (leftChild.getRight() == null) {
					node.setLeft(leftChild.getLeft());
				} else {
					node.setLeft(leftChild.getRight());
				}
				return nodeData;
			} else {
				return findRemove(data, leftChild);
			}
    	}
    }
    
    private T rRemove(T data, BSTNode<T> node) {
    	if (node == null) {
    		throw new NoSuchElementException();
    	}
    	BSTNode<T> dataNode, successor;
    	T nodeData;
    	if (node.getData().compareTo(data) > 0) {
    		if (node.getLeft() != null && node.getLeft().getData().equals(data)) {
    			dataNode = node.getLeft();
    			nodeData = dataNode.getData();
    	    	if (dataNode.getLeft() == null && dataNode.getRight() == null) {
    	    		node.setLeft(null);
    	    	} else if (dataNode.getLeft() != null && dataNode.getRight() == null) {
    	    		node.setLeft(dataNode.getLeft());
    	    	} else if (dataNode.getRight() != null && dataNode.getLeft() == null) {
    	    		node.setLeft(dataNode.getRight());
    	    	} else {
    	    		successor = findSuccessor(dataNode.getRight());
    	    		dataNode.setData(successor.getData());
    	    	}
    		} else {
    			return rRemove(data, node.getLeft());
    		}
        	return nodeData;
    	} else {
    		if (node.getRight() != null && node.getRight().getData().equals(data)) {
    			dataNode = node.getRight();
    			nodeData = dataNode.getData();
    	    	if (dataNode.getLeft() == null && dataNode.getRight() == null) {
    	    		node.setRight(null);
    	    	} else if (dataNode.getLeft() != null && dataNode.getRight() == null) {
    	    		node.setRight(dataNode.getLeft());
    	    	} else if (dataNode.getRight() != null && dataNode.getLeft() == null) {
    	    		node.setRight(dataNode.getRight());
    	    	} else {
    	    		successor = findSuccessor(dataNode.getRight());
    	    		dataNode.setData(successor.getData());
    	    	}
    		} else {
    			return rRemove(data, node.getRight());
    		}
    	}
    	throw new NoSuchElementException();
    }
    
    private BSTNode<T> findSuccessor(BSTNode<T> node) {
    	if (node.getLeft().getLeft() == null) {
    		BSTNode<T> successor = node.getLeft();
    		node.setLeft(successor.getRight());
    		return successor;
    	} else {
    		return findSuccessor(node.getLeft());
    	}
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}