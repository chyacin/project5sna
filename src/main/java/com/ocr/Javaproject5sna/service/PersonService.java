package com.ocr.Javaproject5sna.service;

import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.PersonRepository;

public class PersonService {
	
	PersonRepository personRepository;
	
	Person person;
	
	public void createPerson(Person person) {
		
		personRepository.createPerson(person);
	}

	public void getPersonByName(String firstName, String lastName) {
		
		personRepository.findPerson(firstName, lastName);
	}
	
	public void updatePerson(Person person) {
		
		personRepository.updatePerson(person);
	}
	
	public void deletePerson(String firstName, String lastName) {
		
		personRepository.deletePerson(firstName, lastName);
	}
}
