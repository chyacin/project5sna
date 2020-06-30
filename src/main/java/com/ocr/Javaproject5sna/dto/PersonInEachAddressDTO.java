package com.ocr.Javaproject5sna.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonInEachAddressDTO {
	
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private int age;
	private Set<String> medications = new HashSet<>();
	private Set<String> allergies = new HashSet<>();

	public PersonInEachAddressDTO() {

	}

	public PersonInEachAddressDTO(String firstName, String lastName, String address, String phoneNumber, int age,
			Set<String> medication, Set<String> allergy) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
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

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public PersonInEachAddressDTO addMedication(String medication) {
		medications.add(medication);
		return this;
	}

	public PersonInEachAddressDTO addAllergies(String allergy) {
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
