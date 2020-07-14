package com.ocr.Javaproject5sna.model;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.ocr.Javaproject5sna.model.MedicalRecord;

public class Person {

	public Person() {

	}

	@NotBlank
	public String firstName;
	@NotBlank
	public String lastName;
	@NotBlank
	public String phone;
	@NotBlank
	public String zip;
	@NotBlank
	public String address;
	@NotBlank
	public String city;
	@NotBlank
	public String email;

	public MedicalRecord medicalRecord;

	public Person(String firstName, String lastName, String phone, String zip, String address, String city,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.zip = zip;
		this.address = address;
		this.city = city;
		this.email = email;
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

	public String getName() {
		return firstName.concat(" ") + lastName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// associate MedicalRecord with Person
	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	public MedicalRecord getMedicalRecord() {
		return this.medicalRecord;
	}

	public String getBirthDate() {
		return this.medicalRecord.getBirthDate();
	}

	public Set<String> getMedication() {
		return this.getMedicalRecord().getMedications();
	}

	public Set<String> getAllergies() {
		return this.getMedicalRecord().getAllergies();
	}

	public int getAge() {
		return this.medicalRecord.getAge();
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", zip=" + zip
				+ ", address=" + address + ", city=" + city + ", email=" + email + "]";
	}

}
