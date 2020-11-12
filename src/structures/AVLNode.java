package structures;

import java.io.Serializable;

/**
 * AVL Tree node
 * @author Miguel Sarasti
 * @param <K>
 * @param <V>
 */
public class AVLNode<K extends Comparable<K>, V> implements Serializable{
	private static final long serialVersionUID = 1L;
	private int height;
	private K key;
	private V value;
	private AVLNode<K,V> left;
	private AVLNode<K,V> right;
	
	public AVLNode(K key, V value) {
		this.height = 1;
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * @return the height of the node
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height: the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the key of the node
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @param key: the key to set
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * @return the value of the node
	 */
	public V getValue() {
		return value;
	}

	/**
	 * @param value: the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * @return the left child node
	 */
	public AVLNode<K, V> getLeft() {
		return left;
	}

	/**
	 * @param left: the left child node to set
	 */
	public void setLeft(AVLNode<K, V> left) {
		this.left = left;
	}

	/**
	 * @return the right child node
	 */
	public AVLNode<K, V> getRight() {
		return right;
	}

	/**
	 * @param right: the right child node to set
	 */
	public void setRight(AVLNode<K, V> right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "Node ["+key+", "+value+"]";
	}
}
