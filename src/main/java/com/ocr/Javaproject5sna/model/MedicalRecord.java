package com.ocr.Javaproject5sna.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MedicalRecord {
	
     public MedicalRecord() {
		
	 }
	
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
	
	public MedicalRecord(String firstName, String lastName, String birthDate, Set<String> medication, Set<String> allergies) {
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
    
    
}
