package model;

import java.io.Serializable;
import java.time.LocalDate;
import structures.AVLTree;

public class DataBase implements Serializable{
	private int Code;
	private AVLTree<String, Person> FNameAVLTree;
	private AVLTree<String, Person> LNameAVLTree;
	private AVLTree<String, Person> FullNameAVLTree;
	private AVLTree<String, Person> CodeAVLTree;
	
	public DataBase() {
		Code = 1;
	}
	
	public void addPerson(String firstName, String lastName, String gender, LocalDate birthDate, double height, String nationality) {
		Person temp = new Person(firstName, lastName, gender, birthDate, height, nationality);
		temp.setCode(""+Code);
		FNameAVLTree.insert(firstName, temp);
		LNameAVLTree.insert(lastName, temp);
		FullNameAVLTree.insert(firstName+" "+lastName, temp);
		CodeAVLTree.insert(temp.getCode(), temp);
		Code++;
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
