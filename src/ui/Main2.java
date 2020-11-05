package ui;
import structures.*;
public class Main2 {

	public static void main(String[] args) {
		AVLTree<Integer, String> tree = new AVLTree<>();
		tree.insert(10, "Hi"); 
        tree.insert(20, "Hello"); 
        tree.insert(30, "Nein"); 
        tree.insert(40, "Luck"); 
        tree.insert(50, "Be"); 
        tree.insert(25, "Die");
        
        System.out.println("Preorder traversal" + 
                " of constructed tree is : "); 
        tree.preOrder(tree.getRoot()); 
        
        tree.delete(20);
        tree.delete(30);
        System.out.println("");
        System.out.println("Preorder traversal" + 
                " of constructed tree is : "); 
        tree.preOrder(tree.getRoot()); 
        
        System.out.println("\n"+tree.search(10));
        System.out.println(tree.search(20));
	}

}
