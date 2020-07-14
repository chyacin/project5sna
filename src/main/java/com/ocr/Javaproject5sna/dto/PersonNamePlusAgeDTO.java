package com.ocr.Javaproject5sna.dto;

import javax.validation.constraints.NotBlank;

public class PersonNamePlusAgeDTO {

	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private int age;

	public PersonNamePlusAgeDTO() {
	}

	public PersonNamePlusAgeDTO(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
