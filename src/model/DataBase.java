package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import structures.AVLTree;

public class DataBase implements Serializable{
	private static final long serialVersionUID = 1L;
	private AVLTree<String, Person> FNameAVLTree;
	private AVLTree<String, Person> LNameAVLTree;
	private AVLTree<String, Person> FullNameAVLTree;
	private AVLTree<String, Person> CodeAVLTree;
	private ArrayList<Person> persons;
	
	public DataBase() {
		FNameAVLTree = new AVLTree<String, Person>();
		LNameAVLTree = new AVLTree<String, Person>();
		FullNameAVLTree = new AVLTree<String, Person>();
		CodeAVLTree = new AVLTree<String, Person>();
		persons = new ArrayList<Person>();
	}
	
	public void genPerson(String firstName, String lastName, String gender, String nationality) {
		Person temp = new Person(firstName, lastName, gender, genBirth(), genHeight(), nationality);
		temp.setCode(""+UUID.randomUUID().toString());
		FNameAVLTree.insert(firstName, temp);
		LNameAVLTree.insert(lastName, temp);
		FullNameAVLTree.insert(firstName+" "+lastName, temp);
		CodeAVLTree.insert(temp.getCode(), temp);
	}
	
	public void addPerson(String firstName, String lastName, String gender, LocalDate birthDate, double height, String nationality) {
		Person temp = new Person(firstName, lastName, gender, birthDate, height, nationality);
		temp.setCode(""+UUID.randomUUID().toString());
		FNameAVLTree.insert(firstName, temp);
		LNameAVLTree.insert(lastName, temp);
		FullNameAVLTree.insert(firstName+" "+lastName, temp);
		CodeAVLTree.insert(temp.getCode(), temp);
	}
	
	public void updatePerson(String code, String firstName, String lastName, String gender, LocalDate birthDate, double height, String nationality) {
		Person temp = searchByCode(code);
		temp.setFirstName(firstName);
		temp.setLastName(lastName);
		temp.setGender(gender);
		temp.setBirthDate(birthDate);
		temp.setHeight(height);
		temp.setNationality(nationality);
	}
	
	public Person searchByFirstName(String name) {
		return FNameAVLTree.search(name).getValue();
	}
	
	public Person searchByLastName(String lastName) {
		return LNameAVLTree.search(lastName).getValue();
	}
	
	public Person searchByFullName(String fullName) {
		return FullNameAVLTree.search(fullName).getValue();
	}
	
	public Person searchByCode(String code) {
		return CodeAVLTree.search(code).getValue();
	}
	
	public void deletePersonFName(String firstName) {
		Person temp = searchByFirstName(firstName);
		FNameAVLTree.delete(firstName);
		LNameAVLTree.delete(temp.getLastName());
		FullNameAVLTree.delete(firstName+" "+temp.getLastName());
		CodeAVLTree.delete(temp.getCode());
	}
	
	public void deletePersonLName(String lastName) {
		Person temp = searchByLastName(lastName);
		FNameAVLTree.delete(temp.getFirstName());
		LNameAVLTree.delete(lastName);
		FullNameAVLTree.delete(temp.getFirstName()+" "+lastName);
		CodeAVLTree.delete(temp.getCode());
	}
	
	public void deletePersonFullName(String fullName) {
		Person temp = searchByFullName(fullName);
		FNameAVLTree.delete(temp.getFirstName());
		LNameAVLTree.delete(temp.getLastName());
		FullNameAVLTree.delete(fullName);
		CodeAVLTree.delete(temp.getCode());
	}
	
	public void deletePersonCode(String code) {
		Person temp = searchByCode(code);
		FNameAVLTree.delete(temp.getFirstName());
		LNameAVLTree.delete(temp.getLastName());
		FullNameAVLTree.delete(temp.getFirstName()+" "+temp.getLastName());
		CodeAVLTree.delete(code);
	}
	
	public double genHeight() {
		return Double.valueOf(String.format("%.2f", (Math.random() * (2.1 - 0.5)) + 0.5));
	}
	
	public LocalDate genBirth() {
		Random random = new Random();
		int minDay = (int) LocalDate.of(1920, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2015, 12, 31).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);

		return LocalDate.ofEpochDay(randomDay);
	}
	
	public ArrayList<Person> getPersonsByFirstName() {
		persons.clear();
		persons = FNameAVLTree.inOrder();
		return persons;
	}
	
	public ArrayList<Person> getPersonsByLastName() {
		persons.clear();
		persons = LNameAVLTree.inOrder();
		return persons;
	}
	
	public ArrayList<Person> getPersonsByFullName() {
		persons.clear();
		persons = FullNameAVLTree.inOrder();
		return persons;
	}
	
	public ArrayList<Person> getPersonsByCode() {
		persons.clear();
		persons = CodeAVLTree.inOrder();
		return persons;
	}
}
