package structures;

import java.util.ArrayList;
import java.util.LinkedList;

public class TrieNode {
	private boolean endOfWord;
	private char data;
	private LinkedList<TrieNode> children;
	private TrieNode parent;
	
	public TrieNode(char letter) {
		data = letter;
		endOfWord = false;
		children = new LinkedList<>();
	}
	
	public boolean isEndOfWord() {
		return endOfWord;
	}

	public void setEndOfWord(boolean endOfWord) {
		this.endOfWord = endOfWord;
	}

	public LinkedList<TrieNode> getChildren() {
		return children;
	}

	public void setChildren(LinkedList<TrieNode> children) {
		this.children = children;
	}

	public TrieNode getParent() {
		return parent;
	}

	public void setParent(TrieNode parent) {
		this.parent = parent;
	}

	public char getData() {
		return data;
	}

	public TrieNode getChild(char letter) {
		if(children!=null) {
			for(int i = 0; i<children.size(); i++) {
				TrieNode temp = children.get(i);
				if(Character.toLowerCase(temp.getData())==Character.toLowerCase(letter)) {
					return temp;
				}
			}
		}
		return null;
	}
	
	public ArrayList<String> getWords(){
		ArrayList<String> words = new ArrayList<>();
		if(endOfWord) {
			words.add(toString());
		}
		if(children!=null) {
			for(int i = 0; i<children.size(); i++) {
				TrieNode temp = children.get(i);
				if(temp!=null) {
					words.addAll(temp.getWords());
				}
				
			}
		}
		return words;
	}
	
	@Override
	public String toString() {
		if(parent==null) {
			return "";
		}else {
			return parent.toString()+""+data;
		}
	}
}
