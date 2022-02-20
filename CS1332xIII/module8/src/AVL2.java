import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 */
public class AVL2<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
    	if (data == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	root = rAdd(data, root);
    	size += 1;
    	
    }
    
    private AVLNode<T> rAdd(T data, AVLNode<T> subRoot) {

    	if (subRoot == null) {
    		return new AVLNode(data);
    	}
    	
    	if (data.equals(subRoot.getData())) {
    		size -= 1;
    		return subRoot;
    	}
    	
    	if (data.compareTo(subRoot.getData()) < 0) {
    		subRoot.setLeft(rAdd(data, subRoot.getLeft()));
    		subRoot = balance(subRoot);
    		return subRoot;
    	} else {
    		subRoot.setRight(rAdd(data, subRoot.getRight()));
    		subRoot = balance(subRoot);
    		return subRoot;
    	}
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     *    simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     *    replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     *    replace the data, NOT predecessor. As a reminder, rotations can occur
     *    after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If the data is null.
     * @throws java.util.NoSuchElementException   If the data is not found.
     */    
    public T remove(T data) {
     	T nodeData;
     	if (data == null) {
     		throw new IllegalArgumentException();
     	} else if (root != null && root.getData().equals(data)) {
     		size -= 1;
     		AVLNode<T> successor;
     		nodeData = root.getData();
 	    	if (root.getLeft() == null && root.getRight() == null) {
 	    		root = null;
 	    		return nodeData;
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
			root.setLeft(balance(root.getLeft()));
		    root.setRight(balance(root.getRight()));
			root = balance(root);
 	    	return nodeData;
     	} else {
             nodeData = findRemove(data, root);
			 root.setLeft(balance(root.getLeft()));
			 root.setRight(balance(root.getRight()));
             root = balance(root);
             size -= 1;
             return nodeData;
         }
     }
     
     private T findRemove(T data, AVLNode<T> node) {
     	if (node == null) {
     		throw new NoSuchElementException();
     	}	
     	// Check 
     	AVLNode<T> successor;
     	Integer dataVal = node.getData().compareTo(data);
     	if (dataVal < 0) {
     		// search right child
     		AVLNode<T> rightChild = node.getRight();
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
                        rightChild.setRight(balance(rightChild.getRight()));
         				rightChild.setData(successor.getData());
     				}
     			} else if (rightChild.getRight() == null) {
     				node.setRight(rightChild.getLeft());
     			} else {
     				node.setRight(rightChild.getRight());
     			}
     			node.setLeft(balance(node.getLeft()));
     			node.setRight(balance(node.getRight()));
 				return nodeData;
     		} else {
     			T nodeData = findRemove(data, rightChild);
     			node.setLeft(balance(node.getLeft()));
     			node.setRight(balance(node.getRight()));
     			return nodeData;

     		}
     	} else if (dataVal == 0) {
     		throw new NoSuchElementException();
     	} else {
 			// search left child
 			AVLNode<T> leftChild = node.getLeft();
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
                        leftChild.setRight(balance(leftChild.getRight()));
 	    				leftChild.setData(successor.getData());
 					}
 				} else if (leftChild.getRight() == null) {
 					node.setLeft(leftChild.getLeft());
 				} else {
 					node.setLeft(leftChild.getRight());
 				}
     			node.setLeft(balance(node.getLeft()));
     			node.setRight(balance(node.getRight()));
 				return nodeData;
 			} else {
 				T nodeData = findRemove(data, leftChild);
     			node.setLeft(balance(node.getLeft()));
     			node.setRight(balance(node.getRight()));
     			return nodeData;
 			}
     	}
     }
     
     private AVLNode<T> findSuccessor(AVLNode<T> node) {
     	if (node.getLeft().getLeft() == null) {
     		AVLNode<T> successor = node.getLeft();
     		node.setLeft(balance(successor.getRight()));
     		return successor;
     	} else {
     		AVLNode<T> successor = findSuccessor(node.getLeft());
     		node.setLeft(balance(node.getLeft()));
     		return successor;
     	}
     }

    /**
     * Updates the height and balance factor of a node using its
     * setter methods.
     *
     * Recall that a null node has a height of -1. If a node is not
     * null, then the height of that node will be its height instance
     * data. The height of a node is the max of its left child's height
     * and right child's height, plus one.
     *
     * The balance factor of a node is the height of its left child
     * minus the height of its right child.
     *
     * This method should run in O(1).
     * You may assume that the passed in node is not null.
     *
     * This method should only be called in rotateLeft(), rotateRight(),
     * and balance().
     *
     * @param currentNode The node to update the height and balance factor of.
     */
    private void updateHeightAndBF(AVLNode<T> currentNode) {
    	int leftHeight, rightHeight;
    	if (currentNode.getLeft() != null) {
    		leftHeight = currentNode.getLeft().getHeight();
    	} else {
    		leftHeight = -1;
    	}
    	if (currentNode.getRight() != null) {
    		rightHeight = currentNode.getRight().getHeight();
    	} else {
    		rightHeight = -1;
    	}
    	
    	currentNode.setHeight(Math.max(leftHeight, rightHeight) + 1);
    	
    	currentNode.setBalanceFactor(leftHeight - rightHeight);
    }

    /**
     * Method that rotates a current node to the left. After saving the
     * current's right node to a variable, the right node's left subtree will
     * become the current node's right subtree. The current node will become
     * the right node's left subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is right heavy. Therefore, you do not need to
     * perform any preliminary checks, rather, you can immediately perform a
     * left rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
     private AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
    	AVLNode<T> tempNode = currentNode.getRight();
    	currentNode.setRight(tempNode.getLeft());
    	tempNode.setLeft(currentNode);
    	updateHeightAndBF(currentNode);
    	updateHeightAndBF(tempNode);
    	return tempNode;
    }

    /**
     * Method that rotates a current node to the right. After saving the
     * current's left node to a variable, the left node's right subtree will
     * become the current node's left subtree. The current node will become
     * the left node's right subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is left heavy. Therefore, you do not need to perform
     * any preliminary checks, rather, you can immediately perform a right
     * rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateRight(AVLNode<T> currentNode) {
    	AVLNode<T> tempNode = currentNode.getLeft();
    	currentNode.setLeft(tempNode.getRight());
    	tempNode.setRight(currentNode);
    	updateHeightAndBF(currentNode);
    	updateHeightAndBF(tempNode);
    	return tempNode;
    }

    /**
     * This is the overarching method that is used to balance a subtree
     * starting at the passed in node. This method will utilize
     * updateHeightAndBF(), rotateLeft(), and rotateRight() to balance
     * the subtree. In part 2 of this assignment, this balance() method
     * will be used in your add() and remove() methods.
     *
     * The height and balance factor of the current node is first recalculated.
     * Based on the balance factor, a no rotation, a single rotation, or a
     * double rotation takes place. The current node is returned.
     *
     * You may assume that the passed in node is not null. Therefore, you do
     * not need to perform any preliminary checks, rather, you can immediately
     * check to see if any rotations need to be performed.
     *
     * This method should run in O(1).
     *
     * @param cur The current node under inspection.
     * @return The AVLNode that the caller should return.
     */
    private AVLNode<T> balance(AVLNode<T> currentNode) {
    	if (currentNode == null) {
    		return null;
    	}

        /* First, we update the height and balance factor of the current node. */
        updateHeightAndBF(currentNode);
        int bf = currentNode.getBalanceFactor();

        if ( bf <= -2 ) {
        	AVLNode<T> right = currentNode.getRight();
            if ( right != null && right.getBalanceFactor() == 1) {
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            currentNode = rotateLeft(currentNode);
        } else if ( bf >= 2 ) {
        	AVLNode<T> left = currentNode.getLeft();
            if ( left != null && left.getBalanceFactor() == -1 ) {
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            currentNode = rotateRight(currentNode);
        }

        return currentNode;
    }


    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree.
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}