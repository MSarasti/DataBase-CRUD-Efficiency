package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import structures.Trie;

class TrieTest {
	Trie t;
	/** STAGES **/
	public void setUpStage1() {
		t = new Trie();
	}
	
	public void setUpStage2() {
		t = new Trie();
		t.insert("hola");
		t.insert("hello");
		t.insert("game");
		t.insert("goma");
		t.insert("kirito");
		t.insert("kisune");
	}
	
	/** TESTS **/
	@Test
	void testInsert() {
		setUpStage1();
		t.insert("ginger");
		assertTrue(t.search("ginger"));
	}
	
	@Test
	void testInsert2() {
		setUpStage2();
		t.insert("ginger");
		assertTrue(t.search("game"));
		assertTrue(t.search("ginger"));
	}
	
	@Test
	void testInsert3() {
		setUpStage2();
		t.insert("ginger");
		ArrayList<String> a = t.getEveryWord("g");
		int total = 0;
		for(int i = 0; i < a.size(); i++) {
			total++;
		}
		assertEquals(3, total);
	}
	
	@Test
	void testSearch() {
		setUpStage1();
		boolean b = t.search("hola");
		assertFalse(b);
	}
	
	@Test
	void testSearch2() {
		setUpStage2();
		assertTrue(t.search("hola"));
	}
	
	@Test
	void testSearch3() {
		setUpStage2();
		t.insert("Sho");
		assertTrue(t.search("Sho"));
	}
	
	@Test
	void testGetEveryWord() {
		setUpStage1();
		ArrayList<String> a = t.getEveryWord("a");
		assertEquals(0, a.size(), "There shouldn't be any words in the trie");
	}
	
	@Test
	void testGetEveryWord2() {
		setUpStage2();
		ArrayList<String> a = t.getEveryWord("k");
		assertEquals(2, a.size(), "There should be two words that begin with k");
	}
	
	@Test
	void testGetEveryWord3() {
		setUpStage2();
		t.insert("ginger");
		ArrayList<String> a = t.getEveryWord("g");
		assertEquals(3, a.size(), "There should be two words that begin with g");
	}
	
	@Test
	void testGetEveryWord4() {
		setUpStage2();
		t.insert("kermit");
		ArrayList<String> a = t.getEveryWord("ki");
		assertEquals(2, a.size(), "There should be two words that begin with ki");
	}
}
