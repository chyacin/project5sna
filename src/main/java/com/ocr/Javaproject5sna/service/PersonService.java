package com.ocr.Javaproject5sna.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.PersonRepository;
 
@Service
public class PersonService implements IPersonService{
	
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
	
	/*return personInfo which includes full name, address, age, email and medical information.
	 * below is the url address
	 * http://localhost:8080/personInfo?firstName=%3CfirstName%3E&lastName=%3ClastName
	 */
	@SuppressWarnings("unchecked")
	public List<JSONObject> getPersonInfo(String firstName, String lastName) {
			
	    List<JSONObject> personMedicalInfo = new ArrayList<>(); 
			
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
				
				personMedicalInfo.add(personInfo);
			}
		}
		return personMedicalInfo;
	}
	
	/*returns email address of everyone in the city
	 * below is the url address
	 * http://localhost:8080/communityEmail?city=%3Ccity
	 */
	public List<String> getPersonsEmailAddress(String city) {
		
		List<String> personEmail = new ArrayList<>();
		
		for(Person person: personRepository.findAll()) {
			if(person.getCity().equals(city)) {
				personEmail.add(person.getEmail());
				
			}
		}
		return personEmail;
	}
	
	/* returns a list of children under 18 at each address and the adults at the same address
	 * if there is no child at the address, it returns empty.
	 * below is the url address
     http://localhost:8080/childAlert?address=%3Caddress 
     */
	@SuppressWarnings("unchecked")
	public JSONObject getChildrenFromEachAddress(String address) {
		
		List<Person> personsInAddress = new ArrayList<>();
		List<JSONObject> adults = new ArrayList<>();
		List<JSONObject> children = new ArrayList<>();	
		personsInAddress = personRepository.findAll().stream().filter(e -> e.getAddress().equals(address)).collect(Collectors.toList());
			
		for(Person person: personsInAddress) {
			if(person.getAge() <= 18) {
			   JSONObject childDetails = new JSONObject();
			   childDetails.put("firstName", person.getFirstName());
			   childDetails.put("lastName", person.getLastName());
			   childDetails.put("age", person.getAge());
			   childDetails.put("address", person.getAddress());	
			   
			   children.add(childDetails);   
			}
			else {	
				JSONObject adult = new JSONObject();
			     adult.put("firstName", person.getFirstName());
			     adult.put("lastName", person.getLastName());
			     adult.put("address", person.getAddress());

			   adults.add(adult);  
			}		
		} 
		if(children.isEmpty()) {
			return new JSONObject();
		}
		else {
			JSONObject adultsPlusChildren = new JSONObject();
			
			adultsPlusChildren.put("children", children);
			if(adults != null) {
				adultsPlusChildren.put("adults", adults);
					
			}
			return adultsPlusChildren;
		}
	}
}
