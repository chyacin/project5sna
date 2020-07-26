package com.ocr.Javaproject5sna.serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ocr.Javaproject5sna.dto.ChildAlertDTO;
import com.ocr.Javaproject5sna.dto.PersonNamePlusAgeDTO;
import com.ocr.Javaproject5sna.dto.PersonMedicalInfoDTO;
import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.PersonRepository;
import com.ocr.Javaproject5sna.service.PersonService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

	@InjectMocks
	PersonService personService;

	@Mock
	PersonRepository personRepository;

	@Test
	public void createPerson_returnCreatedPerson() {

		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Boyd");
		person.setAddress("175 Culver St");
		person.setCity("Culver");
		person.setZip("75009");
		person.setPhone("888-987-7878");
		person.setEmail("jaboyd@email.com");
		when(personRepository.createPerson(person)).thenReturn(person);

		// Act
		Person createPerson = personService.createPerson(person);

		// Assert
		assertEquals(createPerson.getFirstName(), "John");
		assertEquals(createPerson.getLastName(), "Boyd");
		assertEquals(createPerson.getAddress(), "175 Culver St");
		assertEquals(createPerson.getCity(), "Culver");
		assertEquals(createPerson.getZip(), "75009");
		assertEquals(createPerson.getPhone(), "888-987-7878");
		assertEquals(createPerson.getEmail(), "jaboyd@email.com");

	}

	@Test
	public void updatePerson_returnUpdatedPerson() {

		Person person1 = new Person();
		person1.setFirstName("John");
		person1.setLastName("Boyd");
		person1.setAddress("175 Culver St");
		person1.setCity("Culver");
		person1.setZip("97451");
		person1.setPhone("841-874-7466");
		person1.setEmail("encore317@email.com");

		when(personRepository.findPerson("John", "Boyd")).thenReturn(person1);

		// Act
		personService.updatePerson(person1);

		// Assert
		Mockito.verify(personRepository, times(1));

	}

	@Test
	public void deletePerson() {

		personService.deletePerson("John", "Boyd");

		Mockito.verify(personRepository, times(1));
	}

	@Test
	public void getPersonInfo_returnPersonInfo() {

		// Arrange
		Set<String> medications = new HashSet<String>();
		medications.add("aznol:350mg, hydrapermazol:100mg");
		Set<String> allergies = new HashSet<>();
		allergies.add("nilliacilan");
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setBirthDate("01/07/1998");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		String firstName = "John";
		String lastName = "Boyd";
		Person person = new Person(firstName, lastName, "888-545-5656", "5400", "1509 Culver St", "Culver",
				"jaboyd@email.com");
		person.setMedicalRecord(medicalRecord);
		Person person1 = new Person("Soh", "Dod", "1509 Culver", "gaboyd@email", "888-345-345", "Sulver", "5400");

		List<Person> persons = new ArrayList<>();
		persons.add(person);
		persons.add(person1);

		Mockito.when(personRepository.findAll()).thenReturn(persons);

		// Act
		List<PersonMedicalInfoDTO> result = personService.getPersonInfo(firstName, lastName);

		// Assert
		assertNotNull(result);
		System.out.println(result);
		assertEquals(1, result.size());
		// extracting the json using a key
		assertEquals(firstName, result.get(0).getFirstName());
		assertEquals(lastName, result.get(0).getLastName());

	}

	@Test
	public void getPersonsEmail_returnPersonsEmail() {

		// Arrange
		String city = "Culver";
		String email = "jaboyd@email.com";
		Person person = new Person("John", "Boyd", "888-545-5656", "5400", "1509 Culver St", city, email);
		Person person1 = new Person("John", "Boyd", "1509 Culver", "gaboyd@email.com", "888-345-345", "Sulver", "5400");
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		persons.add(person1);
		Mockito.when(personRepository.findAll()).thenReturn(persons);

		// Act
		List<String> result = personService.getPersonsEmailAddress("Culver");

		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(email, result.get(0));

	}

	@Test
	public void jsonObject_getChildrenFromEachAddress_returnChildrenFromEachAddress() {

		// Arrange
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setBirthDate("03/06/1984");
		MedicalRecord medicalRecord1 = new MedicalRecord();
		medicalRecord1.setBirthDate("09/06/2017");

		String address = "1509 Culver St";

		Person person = new Person("John", "Boyd", "888-545-5656", "5400", address, "Culver", "jaboyd@email.com");
		person.setMedicalRecord(medicalRecord);
		Person person1 = new Person("Roger", "Boyd", "888-545-5656", "5400", address, "Culver", "jaboyd@email.com");
		person1.setMedicalRecord(medicalRecord1);
		Person person2 = new Person("Sonny", "Boy", "888-545-5656", "5400", "17 Culver St", "Culver",
				"jaboyd@email.com");

		List<Person> persons = new ArrayList<>();
		persons.add(person);
		persons.add(person1);
		persons.add(person2);
		Mockito.when(personRepository.findAll()).thenReturn(persons);

		// Act
		ChildAlertDTO result = personService.getChildrenFromEachAddress(address);
		List<PersonNamePlusAgeDTO> children = result.getChildren();
		List<PersonNamePlusAgeDTO> adults = result.getAdults();

		// Assert
		assertNotNull(result);
		assertEquals(1, children.size());
		assertEquals(1, adults.size());
		assertEquals("John", adults.get(0).getFirstName());
		assertEquals("Boyd", adults.get(0).getLastName());
		assertEquals("Roger", children.get(0).getFirstName());
		assertEquals("Boyd", children.get(0).getLastName());

	}

}
