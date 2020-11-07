package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.*;
import java.util.Date;
import javax.imageio.ImageIO;

public class Person implements Serializable{
	private String code;
	private String FirstName;
	private String LastName;
	private String gender;
	private Date birthDate;
	private int height;
	private String nationality;
	private String photo;
	
	public Person(String firstName, String lastName, String gender, Date birthDate, int height, String nationality) {
		FirstName = firstName;
		LastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.height = height;
		this.nationality = nationality;
		photo = "https://thispersondoesnotexist.com/";
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return FirstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthDate
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the photo
	 * @throws Exception 
	 */
	public BufferedImage getPhoto() throws Exception {
		return ImageIO.read(new URL(photo));
	}
	
}
