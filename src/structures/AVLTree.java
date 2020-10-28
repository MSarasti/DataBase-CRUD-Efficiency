package structures;

public class AVLTree<K extends Comparable<K>, V> {
	private AVLNode<K,V> root;
	/*
	public boolean delete(K key){
	    AVLNode<K,V> current = root;
	    AVLNode<K,V> parent = root;
	    boolean isLeftChild = false;
	    while(current.getKey().compareTo(key)){
	      parent = current;
	      if(value < current.getValue()){
	        // Move to the left if searched value is less
	        current = current.getLeft();
	        isLeftChild = true;
	      }
	      else{
	        // Move to the right if searched value is >=
	        current = current.right;
	        isLeftChild = false;
	      }
	      if(current == null){
	        return false;
	      }
	    }
	    // if reached here means node to be deleted is found
	    
	    // Leaf node deletion case
	    if(current.left == null && current.right == null){
	      //System.out.println("Leaf node deletion case");
	      // if root node is to be deleted
	      if(current == root){
	        root = null;
	      }
	      // left child
	      else if(isLeftChild){
	        parent.left = null;
	      }
	      // right child
	      else{
	        parent.right = null;
	      }
	    }
	    // Node to be deleted has one child case
	    // Node to be deleted has right child
	    else if(current.left == null){
	      //System.out.println("One right child deletion case");
	      // if root node is to be deleted
	      if(current == root){
	        root = current.right;
	      }
	      // if deleted node is left child
	      else if(isLeftChild){
	        parent.left = current.right;
	      }
	      // if deleted node is right child
	      else{
	        parent.right = current.right;
	      }
	    }
	    // Node to be deleted has left child
	    else if(current.right == null){
	      //System.out.println("One left child deletion case");
	      if(current == root){
	        root = current.left;
	      }
	      // if deleted node is left child
	      else if(isLeftChild){
	        parent.left = current.left;
	      }
	      // if deleted node is right child
	      else{
	        parent.right = current.left;
	      }
	    }
	    // Node to be deleted has two children case
	    else{
	      //System.out.println("Two children deletion case");
	      // find in-order successor of the node to be deleted
	    	AVLNode successor = findSuccessor(current);
	      if(current == root){
	        root = successor;
	      }
	      // if deleted node is left child
	      else if(isLeftChild){
	        parent.left = successor;
	      }
	      // if deleted node is right child
	      else{
	        parent.right = successor;
	      }
	      successor.left = current.left;
	    }
	    return true;
	  }*/
}
