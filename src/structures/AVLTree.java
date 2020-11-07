package structures;

/**
 * AVL Tree data structure
 * @author Miguel Sarasti
 * @param <K>
 * @param <V>
 */
public class AVLTree<K extends Comparable<K>, V> implements AVLInterface<K,V>{
	private AVLNode<K,V> root;
	
	/**
	 * AVLTree constructor
	 */
	public AVLTree() {
	}
	
	/**
	 * Adds a new node to the AVL tree
	 * <b>post:</b> A new node has been added<br>
	 * @param K Key
	 * @param V Value
	 */
	public void insert(K key, V value) {
		root = insert(root, key, value);
	}
	
	private AVLNode<K,V> insert(AVLNode<K,V> current, K key, V value){
		if(current == null) {
			current = new AVLNode<K,V>(key, value);
			return current;
		}else {
			if(current.getKey().compareTo(key)>0) {
				current.setLeft(insert(current.getLeft(), key, value));
			}else {
				current.setRight(insert(current.getRight(), key, value));
			}
			current.setHeight(Math.max(height(current.getLeft()), height(current.getRight()))+1);
			int balanceFactor = heightDiff(current);
            if(balanceFactor < -1) {
                if(heightDiff(current.getRight()) > 0) {
                    current.setRight(rightRotate(current.getRight()));
                    return leftRotate(current);
                }else {
                    return leftRotate(current);
                }
            }else if(balanceFactor > 1) {
                if (heightDiff(current.getLeft()) < 0) {
                	current.setLeft(leftRotate(current.getLeft()));
                    return rightRotate(current);
                } else {
                    return rightRotate(current);
                }
            }else;
		}
		return current;
	}
	
	/**
	 * Searches for a node in the AVL tree
	 * <b>post:</b> The searched node found or null if it wasn't fount.
	 * @param K key
	 * @return AVLNode
	 */
	public AVLNode<K,V> search(K key){
		if(!root.getKey().equals(key)) {
			return search(root, key);
		}
		return root;
	}
	
	private AVLNode<K,V> search(AVLNode<K,V> current, K key){
		if(current==null) {
			return current;
		}else {
			if(current.getKey().compareTo(key)==0) {
				return current;
			}else if(current.getKey().compareTo(key)>0) {
				return search(current.getLeft(), key);
			}else {
				return search(current.getRight(), key);
			}
		}
	}
	
	/**
	 * Deletes a node from the AVL tree
	 * @param K key
	 */
	public void delete(K key) {
		root = delete(root, key);
	}
	
	private AVLNode<K,V> delete(AVLNode<K,V> current, K key){
		if(current==null) {
			return current;
		}
		if(current.getKey().compareTo(key)>0) {
			current.setLeft(delete(current.getLeft(), key));
		}else if(current.getKey().compareTo(key)<0) {
			current.setRight(delete(current.getRight(), key));
		}else {
			if((current.getLeft()==null) || (current.getRight()==null)) {
				AVLNode<K,V> temp = null;
				if(temp==current.getLeft()) {
					temp = current.getRight();
				}else {
					temp = current.getLeft();
				}
				if(temp==null) {
					temp = current;
					current = null;
				}else {
					current = temp;
				}
			}else {
				AVLNode<K,V> temp = minKeyNode(current.getRight());
				current.setKey(temp.getKey());
				current.setValue(temp.getValue());
				current.setRight(delete(current.getRight(), temp.getKey()));
			}
		}
		if(current==null) {
			return current;
		}
		current.setHeight(Math.max(height(current.getLeft()), height(current.getRight()))+1);
		int balFacCurrent = heightDiff(current);
		int balFacLeft = heightDiff(current.getLeft());
		int balFacRight = heightDiff(current.getRight());
		if(balFacCurrent>1 && balFacLeft>=0) {
			return rightRotate(current);
		}
		if(balFacCurrent>1 && balFacLeft<0) {
			current.setLeft(leftRotate(current.getLeft()));
			return rightRotate(current);
		}
		if(balFacCurrent<-1 && balFacRight<=0) {
			return leftRotate(current);
		}
		if(balFacCurrent<-1 && balFacRight>0) {
			current.setRight(rightRotate(current.getRight()));
			return leftRotate(current);
		}
		return current;
	}
	
	/**
	 * Performs a left rotation to a current AVLNode
	 * @param current
	 * @return AVLNode r: rotated node.
	 */
	private AVLNode<K,V> leftRotate(AVLNode<K,V> current) {
        AVLNode<K,V> r = current.getRight();
        current.setRight(r.getLeft());
        r.setLeft(current);
        current.setHeight(Math.max(height(current.getLeft()), height(current.getRight()))+1);
        r.setHeight(Math.max(height(r.getLeft()), height(r.getRight()))+1);
        return r;
    }
	
	/**
	 * Performs a right rotation to a current AVL Node
	 * @param current
	 * @return AVLNode r: rotated node.
	 */
    private AVLNode<K,V> rightRotate(AVLNode<K,V> current) {
        AVLNode<K,V> r = current.getLeft();
        current.setLeft(r.getRight());
        r.setRight(current);
        current.setHeight(Math.max(height(current.getLeft()), height(current.getRight()))+1);
        r.setHeight(Math.max(height(r.getLeft()), height(r.getRight())) + 1);
        return r;
    }
    
    /**
     * Get the minimum key value of a subtree
     * @param current
     * @return AVLNode with the minimum key value found
     */
    private AVLNode<K,V> minKeyNode(AVLNode<K,V> current){
    	while(current.getLeft()!=null) {
    		current = current.getLeft();
    	}
    	return current;
    }
    
    /**
     * Calculates the height difference (Balance factor) of a subtree
     * @param current
     * @return the balance factor of the subtree
     */
    private int heightDiff(AVLNode<K,V> current) {
        if (current == null) {
            return 0;
        }
        return height(current.getLeft()) - height(current.getRight());
    }
    
    /**
     * Gets the height of a subtree
     * @param current
     * @return the height of the subtree
     */
    private int height(AVLNode<K,V> current) {
        if (current == null) {
            return 0;
        }
        return current.getHeight();
    }
    
    /**
     * @return the root of the AVLTree
     */
    public AVLNode<K, V> getRoot(){
    	return root;
    }
    
    /**
     * Prints the AVL tree in preOrder
     * @param node
     */
    public void preOrder(AVLNode<K,V> node) { 
        if (node != null) { 
            System.out.print(node.getKey() + " " + node.getValue()+ " "); 
            preOrder(node.getLeft()); 
            preOrder(node.getRight()); 
        } 
    }
}
