package com.ocr.Javaproject5sna.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.repository.MedicalRecordRepository;


public class Person {
	
MedicalRecordRepository medicalRecordRepository;	
 
 public Person() {
	 
 }
		public String firstName;
		public String lastName;
		public String phone;
		public String zip;
		public String address;
		public String city;
		public String email;

		public Person(String firstName, String lastName, String phone, String zip, String address, String city, String email) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.phone = phone;
			this.zip = zip;
			this.address = address;
			this.city = city;
			this.email = email;
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

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    public String getZip() {
	        return zip;
	    }

	    public void setZip(String zip) {
	        this.zip = zip;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    @Override
	    public String toString() {
	        return "Person{" +
	                "firstName='" + firstName + '\'' +
	                ", lastName='" + lastName + '\'' +
	                ", address='" + address + '\'' +
	                ", city='" + city + '\'' +
	                ", zip='" + zip + '\'' +
	                ", phone='" + phone + '\'' +
	                ", email='" + email + '\'' +
	                '}';
	    }	
	
	 // calling someone's medical record by using the PersonClass
		
		public MedicalRecord getMedicalRecord() {
			
		   List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
			
		   for(MedicalRecord medicalRecord: medicalRecords) {
			   if(medicalRecord.getFirstName().equals(firstName)
				  && medicalRecord.getLastName().equals(lastName))
				   
				   return medicalRecord;
			   
		   }
		   return null;
		}
		
		public String getBirthDate() {
			return this.getMedicalRecord().getBirthDate();
		}
		
		public Set<String> getMedication() {
			return this.getMedicalRecord().getMedications();
		}
		
		public Set<String> getAllergies() {
			return this.getMedicalRecord().getAllergies();		
		}
		
		// creating a method to get current age
		
		public int getAge() {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
			
			LocalDate today = LocalDate.now();
			
			LocalDate birthDate = LocalDate.parse(this.getBirthDate(), formatter);
			
			Period age = Period.between(birthDate, today);
			
			return age.getYears();
			
		}
		
}

