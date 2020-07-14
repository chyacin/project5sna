package com.ocr.Javaproject5sna.modelClass;

import javax.validation.constraints.NotBlank;

public class NamesModelClass {

	public NamesModelClass() {

	}

	@NotBlank
	public String firstName;
	@NotBlank
	public String lastName;

	public NamesModelClass(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
