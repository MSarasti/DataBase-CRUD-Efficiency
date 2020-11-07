package model;

import java.io.Serializable;
import java.util.Date;

import structures.AVLTree;

public class DataBase implements Serializable{
	private int code;
	private AVLTree<String, Person> FNameAVLTree;
	private AVLTree<String, Person> LNameAVLTree;
	private AVLTree<String, Person> FullNameAVLTree;
	private AVLTree<String, Person> CodeAVLTree;
	
	public void DataBase() {
		code = 1;
	}
	
	public void addPerson(String firstName, String lastName, String gender, Date birthDate, int height, String nationality) {
		Person temp = new Person(firstName, lastName, gender, birthDate, height, nationality);
		temp.setCode(""+code);
		FNameAVLTree.insert(firstName, temp);
		LNameAVLTree.insert(lastName, temp);
		FullNameAVLTree.insert(firstName+" "+lastName, temp);
		CodeAVLTree.insert(temp.getCode(), temp);
		code++;
	}
	
	public void deletePersonFName(String firstName) {
		Person temp = FNameAVLTree.search(firstName).getValue();
		FNameAVLTree.delete(firstName);
		LNameAVLTree.delete(temp.getLastName());
		FullNameAVLTree.delete(firstName+" "+temp.getLastName());
		CodeAVLTree.delete(temp.getCode());
	}
	
	public void deletePersonLName(String lastName) {
		Person temp = LNameAVLTree.search(lastName).getValue();
		FNameAVLTree.delete(temp.getFirstName());
		LNameAVLTree.delete(lastName);
		FullNameAVLTree.delete(temp.getFirstName()+" "+lastName);
		CodeAVLTree.delete(temp.getCode());
	}
	
	public void deletePersonFullName(String fullName) {
		Person temp = FullNameAVLTree.search(fullName).getValue();
		FNameAVLTree.delete(temp.getFirstName());
		LNameAVLTree.delete(temp.getLastName());
		FullNameAVLTree.delete(fullName);
		CodeAVLTree.delete(temp.getCode());
	}
	
	public void deletePersonCode(String code) {
		Person temp = CodeAVLTree.search(code).getValue();
		FNameAVLTree.delete(temp.getFirstName());
		LNameAVLTree.delete(temp.getLastName());
		FullNameAVLTree.delete(temp.getFirstName()+" "+temp.getLastName());
		CodeAVLTree.delete(code);
	}
}
