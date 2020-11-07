package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import structures.BinarySearchTree;

class BSTTest {
	BinarySearchTree<Integer,String> b;
	/** STAGES **/	
	public void setUpStage1() {
		b = new BinarySearchTree<Integer, String>();
	}
	
	public void setUpStage2() throws Exception {
		b = new BinarySearchTree<Integer, String>();
		b.addNode(5, "Michael");
		b.addNode(10, "Leonard");
		b.addNode(7, "Raphael");
		b.addNode(2, "Donatello");
		b.addNode(1, "Splinter");
	}
	
	/** TESTS 
	 * @throws Exception 
	 */
	@Test
	void testAdd() throws Exception {
		setUpStage1();
		b.addNode(2, "Kirito");
		assertEquals("Kirito", b.searchNode(2).getV(), "Should be Kirito");
	}
	
	@Test
	void testAdd2() throws Exception {
		setUpStage1();
		b.addNode(2, "Kirito");
		b.addNode(1, "Asuna");
		b.addNode(6, "Sinon");
		Assertions.assertThrows(Exception.class, () ->b.addNode(1, "Alice"), "Should throw an exception");
	}
	
	@Test
	void testUpdate() throws Exception {
		setUpStage2();
		b.updateNode(1, "Kirito");
		assertEquals("Kirito", b.searchNode(1).getV(), "Should be Kirito");
	}
	
	@Test
	void testSearch() throws Exception {
		setUpStage2();
		assertEquals("Michael", b.searchNode(5).getV(), "Should be Michael");
	}
	
	@Test
	void testDelete() throws Exception {
		setUpStage2();
		b.deleteNode(2);
		assertNull(b.searchNode(2));
	}
	
	@Test
	void testWeight() throws Exception {
		setUpStage2();
		assertEquals(5, b.getWeight(), "Should be 5");
	}
	
	@Test
	void testHeight() throws Exception {
		setUpStage2();
		assertEquals(3, b.getHeight(), "Should be 3");
	}
}
