package structures;

import java.util.Collection;

/**
 * Basic operations and algorithms used by a binary search tree
 * @param <K> Key
 * @param <V> Value
 */

public interface BinarySearchTreeInterface<K extends Comparable<K>, V> {
	
	/**
	 * Adds a new node to the binary search tree
	 * <b>post:</b> A new node has been added<br>
	 * @param K Key
	 * @param V Value
	 * @throws Exception
	 */
	
	public void addNode(K k, V v) throws Exception;
	
	/**
	 * Updates the Value v of the node with Key k
	 * @param K key
	 * @param V value
	 */
	
	public void updateNode(K k, V v);
	
	/**
	 * Searches a node in the binary search tree
	 * @param K key
	 * @return Node
	 */
	
	public Node<K, V> searchNode(K k);
	
	/**
	 * Deletes a node from the binary search tree
	 * @param K key
	 */
	
	public void deleteNode(K k);
	
	/**
	 * Orders the binary search tree inorder
	 * @param collection
	 */
	
	public void inOrder(Collection<V> collection);
	
	/**
	 * Orders the binary search tree preorder
	 * @param collection
	 */
	
	public void preOrder(Collection<V> collection);
	
	/**
	 * Orders the binary search tree postorder
	 * @param collection
	 */
	
	public void postOrder(Collection<V> collection);
	
	/**
	 * Calculates the weight of the binary search tree
	 * @return Binary search tree's weight
	 */
	
	public int getWeight();
	
	/**
	 * Calculates the height of the binary search tree
	 * @return Binary search tree's height
	 */
	
	public int getHeight();
}
