import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {
    	List<T> traversal = new ArrayList<>();
    	if (root != null) {
    		traversal.add(root.getData());
    		List<T> lTraversal = preorder(root.getLeft());
    		traversal.addAll(lTraversal);
    		List<T> rTraversal = preorder(root.getRight());
    		traversal.addAll(rTraversal);
    	}
    	return traversal;
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
    	List<T> traversal = new ArrayList<>();
    	if (root != null) {
    		List<T> lTraversal = inorder(root.getLeft());
    		traversal.addAll(lTraversal);
    		traversal.add(root.getData());
    		List<T> rTraversal = inorder(root.getRight());
    		traversal.addAll(rTraversal);
    	}
    	return traversal;    
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
    	List<T> traversal = new ArrayList<>();
    	if (root != null) {
    		List<T> lTraversal = postorder(root.getLeft());
    		traversal.addAll(lTraversal);
    		List<T> rTraversal = postorder(root.getRight());
    		traversal.addAll(rTraversal);
    		traversal.add(root.getData());
    	}
    	return traversal;        }
}