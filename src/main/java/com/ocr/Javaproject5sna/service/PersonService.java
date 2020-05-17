package com.ocr.Javaproject5sna.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.PersonRepository;
 
@Service
public class PersonService {
	
	PersonRepository personRepository;
	
	Person person;
	
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person createPerson(Person person) {
			
		return personRepository.createPerson(person);
	}
	
	public List<Person> getAllPerson(){
		
		 return personRepository.findAll();
	}
	
	
	
	//Url endPoints methods
	
	//return personInfo which includes name, address, age, email and medical information.
	
	public List<JSONObject> getPersonInfo(String firstName, String lastName) {
			
	    List<JSONObject> allPersonsInfo = new ArrayList<>();
			
		for(Person person: personRepository.findAll()) {
			if(person.getFirstName().equals(firstName)
			   && person.getLastName().equals(lastName)) {
				
				JSONObject personInfo = new JSONObject();
				
				personInfo.put("firstName", person.getFirstName());
				personInfo.put("lastName", person.getLastName());
				personInfo.put("address", person.getAddress());
				personInfo.put("age", person.getAge());
				personInfo.put("email", person.getEmail());
				personInfo.put("medicationWithDosage", person.getMedication());
				personInfo.put("allergies", person.getAllergies());
				
				allPersonsInfo.add(personInfo);
			}
		}
		return allPersonsInfo;
	}
	
	//return community email
	
	public List<String> getPersonsEmailAddress(String city) {
		
		List<String> personsEmailAddress = new ArrayList<>();
		
		for(Person person: personRepository.findAll()) {
			if(person.getCity().equals(city)) {
				personsEmailAddress.add(person.getEmail());
			}
		}
		return personsEmailAddress;
	}
	
	// get a list of children under 18 at each address and other people at the same address

	public JSONObject getChildrenFromEachAddress(String address) {
		
		List<Person> personsInAddress = new ArrayList<>();
		List<Person> adults = new ArrayList<>();
		List<JSONObject> everyChildDetails = new ArrayList<>();
		
		for(Person person: personRepository.findAll()) {
			if(person.getAddress().equals(address)) {
				personsInAddress.add(person);
			}
		}
		for(Person person: personsInAddress) {
			if(person.getAge() <= 18) {
			   JSONObject childDetails = new JSONObject();
			   childDetails.put("firstName", person.getFirstName());
			   childDetails.put("lastName", person.getLastName());
			   childDetails.put("age", person.getAge());
			   
			   everyChildDetails.add(childDetails);
			}
			else {
				adults.add(person);
			}
		}
		if(everyChildDetails.isEmpty()) {
			return null;
		}
		else {
			JSONObject familyWithChildrenPlusAdults = new JSONObject();
			familyWithChildrenPlusAdults.put("everyChildDetails", everyChildDetails);
			familyWithChildrenPlusAdults.put("adults", adults);
			
			return familyWithChildrenPlusAdults;
		}
	}
}
