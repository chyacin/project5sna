package com.ocr.Javaproject5sna.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MedicalRecord {
	
	private String firstName;
	private String lastName;
	private String birthDate;
	private Set<String> medications = new HashSet<>();
	private Set<String> allergies = new HashSet<>();
	

	public MedicalRecord(String firstName, String lastName, String birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public MedicalRecord addMedication(String medication) {
		medications.add(medication);
		return this;
	}

	public MedicalRecord addAllergies(String allergy) {
		allergies.add(allergy);
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getName() {
		return firstName.concat(" ") + lastName;
	}

	public String getbirthDate() {
		return birthDate;
	}

	public Set<String> getMedications() {
		return medications.stream().collect(Collectors.toSet());
	}

	public Set<String> getAllergies() {
		return allergies.stream().collect(Collectors.toSet());
	}

	@Override
	public String toString() {
		return this.getName().concat(": ") + String.join(", medications :", medications) + String.join(", Allergies : ", allergies);
	}

}
