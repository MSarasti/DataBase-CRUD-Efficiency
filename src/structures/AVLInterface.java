package structures;

/**
 * Basic operations and algorithms used by an AVL tree
 * @param <K> Key
 * @param <V> Value
 */

public interface AVLInterface<K extends Comparable<K>, V> {
	
	/**
	 * Adds a new node to the AVL tree
	 * <b>post:</b> A new node has been added<br>
	 * @param K Key
	 * @param V Value
	 */
	public void insert(K key, V value);
	
	/**
	 * Searches for a node in the AVL tree
	 * <b>post:</b> The searched node found or null if it wasn't fount.
	 * @param K key
	 * @return AVLNode
	 */
	public AVLNode<K,V> search(K key);
	
	/**
	 * Deletes a node from the AVL tree
	 * @param K key
	 */
	public void delete(K key);
	
	
}
