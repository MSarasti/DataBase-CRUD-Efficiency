package structures;

import java.util.ArrayList;

public class Trie {
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode(' ');
	}
	
	public void insert(String s) {
		TrieNode aux = root;
		TrieNode temp = null;
		for (char letter : s.toCharArray()) {
			temp = aux;
			TrieNode child = aux.getChild(letter);
			if(child!=null) {
				aux = child;
				child.setParent(temp);
			}else {
				aux.getChildren().add(new TrieNode(letter));
				aux = aux.getChild(letter);
				aux.setParent(temp);
			}
		}
		aux.setEndOfWord(true);
	}
	
	public boolean search(String s) {
		TrieNode temp = root;
		for(char letter : s.toCharArray()) {
			if(temp.getChild(letter)==null) {
				return false;
			}else {
				temp = temp.getChild(letter);
			}
		}
		if(temp.isEndOfWord()) {
			return true;
		}
		return false;
	}
	
	public ArrayList<String> getEveryWord(String s) {
		TrieNode temp = root;
		for(int i = 0; i < s.length();i++) {
			temp = temp.getChild(s.charAt(i));
			if(temp==null) {
				return new ArrayList<>();
			}
		}
		return temp.getWords();
	}
}
