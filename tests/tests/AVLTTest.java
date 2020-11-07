/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import structures.AVLTree;

class AVLTTest {
	AVLTree<Integer,String> tree;
	/** STAGES **/	
	public void setUpStage1() {
		tree= new AVLTree<Integer, String>();
	}
	
	public void setUpStage2() {
		tree= new AVLTree<Integer, String>();
		tree.insert(10, "Hi"); 
        tree.insert(20, "Hello"); 
        tree.insert(30, "Nein"); 
        tree.insert(40, "Luck"); 
        tree.insert(50, "Be"); 
        tree.insert(25, "Die");
	}
	
	/** TESTS **/
	@Test
	void testAdd() {
		setUpStage1();
		tree.insert(2, "Kirito");
		assertEquals("Kirito", tree.search(2).getValue(), "Should be Kirito");
	}
	
	@Test
	void testAdd2() {
		setUpStage2();
		tree.insert(2, "Kirito");
		tree.insert(1, "Asuna");
		tree.insert(6, "Sinon");
		assertEquals("Nein", tree.search(30).getValue(), "Should be Nein");
	}
	
	@Test
	void testSearch() {
		setUpStage2();
		assertEquals("Hello", tree.search(20).getValue(), "Should be Hello");
	}
	
	@Test
	void testSearch2() {
		setUpStage2();
		tree.insert(15, "Kirito");
		assertEquals("Kirito", tree.search(15).getValue(), "Should be Kirito");
	}
	
	@Test
	void testDelete() {
		setUpStage2();
		tree.delete(25);
		assertNull(tree.search(25));
	}
	
	@Test
	void testDelete2() {
		setUpStage2();
		tree.delete(50);
		assertNull(tree.search(50));
	}
	
	@Test
	void testBalance() {
		setUpStage1();
		tree.insert(1, "Kirito");
		tree.insert(2, "Asuna");
		tree.insert(3, "Sinon");
		tree.insert(10, "Eugeo");
		assertEquals("Asuna", tree.getRoot().getValue(), "Should be Asuna");
	}
	
	@Test
	void testBalance2() {
		setUpStage2();
		tree.insert(1, "Kirito");
		tree.insert(2, "Asuna");
		tree.insert(3, "Sinon");
		tree.insert(15, "Eugeo");
		assertEquals("Nein", tree.getRoot().getValue(), "Should be Nein");
	}
}
