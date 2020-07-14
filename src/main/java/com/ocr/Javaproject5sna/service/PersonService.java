package com.ocr.Javaproject5sna.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.Javaproject5sna.dto.ChildAlertDTO;
import com.ocr.Javaproject5sna.dto.PersonNamePlusAgeDTO;
import com.ocr.Javaproject5sna.dto.PersonMedicalInfoDTO;
import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.PersonRepository;

@Service
public class PersonService implements IPersonService {

	Logger logger = LoggerFactory.getLogger(PersonService.class);

	PersonRepository personRepository;

	Person person;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person createPerson(Person person) {

		personRepository.findAll().add(person);
		return person;
	}

	// update an existing person details
	public void updatePerson(Person person) {
		Person updatePerson = personRepository.findPerson(person.getFirstName(), person.getLastName());
		updatePerson.setAddress(person.getAddress());
		updatePerson.setCity(person.getCity());
		updatePerson.setZip(person.getZip());
		updatePerson.setPhone(person.getPhone());
		updatePerson.setEmail(person.getEmail());
	}

	// delete an existing person
	public void deletePerson(String firstName, String lastName) {
		Person deletePerson = personRepository.findPerson(firstName, lastName);
		personRepository.findAll().remove(deletePerson);
	}

	// get a person by name
	public Person findPerson(String firstName, String lastName) {
		for (Person person : personRepository.findAll()) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
				return person;
			}
		}
		return null;
	}
	
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	// Url endPoints methods

	/*
	 * return personInfo which includes full name, address, age, email and medical
	 * information. Below is the url address
	 * http://localhost:8080/personInfo?firstName=%3CfirstName%3E&lastName=%
	 * 3ClastName
	 */

	public List<PersonMedicalInfoDTO> getPersonInfo(String firstName, String lastName) {

		ArrayList<PersonMedicalInfoDTO> personMedicalInfo = new ArrayList<>();

		for (Person person : personRepository.findAll()) {
			if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {

				PersonMedicalInfoDTO personInfo = new PersonMedicalInfoDTO();

				personInfo.setFirstName(person.getFirstName());
				personInfo.setLastName(person.getLastName());
				personInfo.setAddress(person.getAddress());
				personInfo.setAge(person.getAge());
				personInfo.setEmail(person.getEmail());
				personInfo.setMedications(person.getMedication());
				personInfo.setAllergies(person.getAllergies());

				personMedicalInfo.add(personInfo);
			}
		}
		return personMedicalInfo;
	}

	/*
	 * returns email address of everyone in the city below is the url address
	 * http://localhost:8080/communityEmail?city=%3Ccity
	 */
	public List<String> getPersonsEmailAddress(String city) {

		
		List<String> personEmail = new ArrayList<>();

		for (Person person : findAll()) {
			if (person.getCity().equals(city)) {
				personEmail.add(person.getEmail());

			}
		}
		return personEmail;
	}

	/*
	 * returns a list of children under 18 at each address and the adults at the
	 * same address if there is no child at the address, it returns empty. Below is
	 * the url address http://localhost:8080/childAlert?address=%3Caddress
	 */

	public ChildAlertDTO getChildrenFromEachAddress(String address) {

		List<Person> personsInAddress = new ArrayList<>();
		ChildAlertDTO result = new ChildAlertDTO();
		ArrayList<PersonNamePlusAgeDTO> adults = new ArrayList<PersonNamePlusAgeDTO>();
		ArrayList<PersonNamePlusAgeDTO> children = new ArrayList<PersonNamePlusAgeDTO>();
		
		for(Person person: findAll()) {
			if(person.getAddress().equals(address)) {
				personsInAddress.add(person);
			}
		}

		for (Person person : personsInAddress) {
			if (person.getAge() <= 18) {
				PersonNamePlusAgeDTO child = new PersonNamePlusAgeDTO();
				child.setFirstName(person.getFirstName());
				child.setLastName(person.getLastName());
				child.setAge(person.getAge());

				children.add(child);
			} else {
				PersonNamePlusAgeDTO adult = new PersonNamePlusAgeDTO();
				adult.setFirstName(person.getFirstName());
				adult.setLastName(person.getLastName());
				adult.setAge(person.getAge());

				adults.add(adult);
			}
		}
		if (children.isEmpty()) {
			return null;
		} else {
			result.setAdults(adults);
			result.setChildren(children);
		}
		return result;

	}
	
//	personsInAddress = personRepository.findAll().stream().filter(e -> e.getAddress().equals(address))
//			.collect(Collectors.toList());
}
