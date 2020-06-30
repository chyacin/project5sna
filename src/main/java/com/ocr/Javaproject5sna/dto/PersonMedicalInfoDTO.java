package com.ocr.Javaproject5sna.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonMedicalInfoDTO {

	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private int age;
	private Set<String> medications = new HashSet<>();
	private Set<String> allergies = new HashSet<>();

	public PersonMedicalInfoDTO() {

	}

	public PersonMedicalInfoDTO(String firstName, String lastName, String address, String email, int age,
			Set<String> medication, Set<String> allergy) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PersonMedicalInfoDTO addMedication(String medication) {
		medications.add(medication);
		return this;
	}

	public PersonMedicalInfoDTO addAllergies(String allergy) {
		allergies.add(allergy);
		return this;

	}

	public Set<String> getMedications() {
		return medications.stream().collect(Collectors.toSet());
	}

	public Set<String> getAllergies() {
		return allergies.stream().collect(Collectors.toSet());
	}

	public void setMedications(Set<String> medications) {
		this.medications = medications;
	}

	public void setAllergies(Set<String> allergies) {
		this.allergies = allergies;
	}
}
