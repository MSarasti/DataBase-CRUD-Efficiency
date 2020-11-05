package structures;

public class AVLNode<K extends Comparable<K>, V> {
	private int balanceFactor;
	private int height;
	private K key;
	private V value;
	private AVLNode<K,V> left;
	private AVLNode<K,V> right;
	
	public AVLNode(K key, V value) {
		this.balanceFactor = 0;
		this.height = 1;
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public void addNode(AVLNode<K, V> node) throws Exception {
		if(key.compareTo(node.getKey())==0)
			throw new Exception("Element already inserted in the tree");
		if(key.compareTo(node.getKey())>0) {
			if(left==null) {
				left = node;
			}else {
				left.addNode(node);
			}
		}else {
			if(right==null) {
				right = node;
			}else {
				right.addNode(node);
			}
		}
	}
	
	/**
	 * Searches a node in the node's subtree
	 * @param k searched : searched key in the subtree
	 * @return AVLNode
	 */
	public AVLNode<K, V> searchNode(K searched){
		if(this.key.compareTo(searched)==0) {
			return this;
		}else if(this.key.compareTo(searched)>0) {
			return left!=null ? left.searchNode(searched) : null;
		}else {
			return right!=null ? right.searchNode(searched) : null;
		}
	}
	
	/**
	 * Deletes a node from the node's subtree
	 * <b>pre:</b> The node to be deleted is != null<br>
	 * <b>post:</b> The node has been deleted
	 * @param k Key
	 * @return Updated subtree
	 */
	
	public AVLNode<K, V> deleteNode(K k) {
		if(isLeaf()) {
			return null;
		}
		if(this.key.compareTo(k)==0) {
			if(left==null) 
				return right;
			if(right==null) 
				return left;
			AVLNode<K, V> successor = right.getMin();
			right = right.deleteNode(successor.getKey());
			successor.left = left;
			successor.right = right;
			return successor;
		}
		
		else if(this.key.compareTo(k)>0) 
			left = left.deleteNode(k);
		else
			right = right.deleteNode(k);
		
		return this;
	}
	
	/**
	 * Returns the minimum node in the node's subtree
	 * @return Minimum node
	 */
	public AVLNode<K, V> getMin() {
		return left==null ? this : left.getMin();
	}
	
	/**
	 * Returns true if the node is a leaf, false if it's not a leaf
	 * @return Boolean statement
	 */
	public boolean isLeaf() {
		return left==null && right==null;
	}
	
	/**
	 * @return the balanceFactor
	 */
	public int getBalanceFactor() {
		return balanceFactor;
	}

	/**
	 * @param balanceFactor the balanceFactor to set
	 */
	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
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
