package com.ocr.Javaproject5sna.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

public class MedicalRecord {

	public MedicalRecord() {

	}

	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	private String birthDate;
	private Set<String> medications = new HashSet<>();
	private Set<String> allergies = new HashSet<>();

	public MedicalRecord(String firstName, String lastName, String birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public MedicalRecord(String firstName, String lastName, String birthDate, Set<String> medication,
			Set<String> allergies) {
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return firstName.concat(" ") + lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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

	public int getAge() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");

		LocalDate today = LocalDate.now();

		LocalDate birthDate = LocalDate.parse(this.getBirthDate(), formatter);

		Period age = Period.between(birthDate, today);

		return age.getYears();

	}

	@Override
	public String toString() {
		return "MedicalRecord [firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", medications=" + medications + ", allergies=" + allergies + ", getAge()=" + getAge() + "]";
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		MedicalRecord other = (MedicalRecord) obj;
//		if (allergies == null) {
//			if (other.allergies != null)
//				return false;
//		} else if (!allergies.equals(other.allergies))
//			return false;
//		if (birthDate == null) {
//			if (other.birthDate != null)
//				return false;
//		} else if (!birthDate.equals(other.birthDate))
//			return false;
//		if (firstName == null) {
//			if (other.firstName != null)
//				return false;
//		} else if (!firstName.equals(other.firstName))
//			return false;
//		if (lastName == null) {
//			if (other.lastName != null)
//				return false;
//		} else if (!lastName.equals(other.lastName))
//			return false;
//		if (medications == null) {
//			if (other.medications != null)
//				return false;
//		} else if (!medications.equals(other.medications))
//			return false;
//		return true;
//	}

}
