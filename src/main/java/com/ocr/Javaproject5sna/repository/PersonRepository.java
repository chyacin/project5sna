package com.ocr.Javaproject5sna.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ocr.Javaproject5sna.model.Person;

@Repository
public class PersonRepository {

	Logger log = LoggerFactory.getLogger(PersonRepository.class);

	private List<Person> personList;

	@Autowired	
	public PersonRepository() {
		this.personList = new ArrayList<>();
	}

	//get list of all persons
	public List<Person> findAll() {
		return personList;
	}

	//print list of all persons
	public void printAll() {
		for (Person person : personList) {
			log.info(person.toString());
		//	System.out.println(person.toString());
		}
	}

	//get a person by name
	public Person findPerson(String firstName, String lastName) {
		for (Person person : personList) {
			if (person.getFirstName().equals(firstName) 
			    && person.getLastName().equals(lastName)) {
				return person;
			}
		}
		return null;
	}

	//add a new person
	public Person createPerson(Person person) {
		personList.add(person);
		return person;
	}

	//update an existing person details
	public void updatePerson(Person person) {
		Person updatePerson = findPerson(person.getFirstName(), person.getLastName());
		updatePerson.setAddress(person.getAddress());
		updatePerson.setCity(person.getCity());
		updatePerson.setZip(person.getZip());
		updatePerson.setPhone(person.getPhone());
		updatePerson.setEmail(person.getEmail());
	}

	//delete an existing person
	public void deletePerson(String firstName, String lastName) {
		Person deletePerson = findPerson(firstName, lastName);
		personList.remove(deletePerson);
	}
	
	 public int getNumberOfAdults() {
			
			int numberOfAdults = 0;
			for(Person person: personList) {
				if(person.getAge() > 18) {
					numberOfAdults++;
				}
			}
			return numberOfAdults;
		}
		
		public int getNumberOfChildren() {
			
			int numberOfChildren = 0;
			for(Person person: personList) {
				if(person.getAge() <= 18) {
					numberOfChildren++;
				}
			}
			return numberOfChildren;
		}

		
		
}
