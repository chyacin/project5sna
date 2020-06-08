package com.ocr.Javaproject5sna.serviceTest;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.PersonRepository;
import com.ocr.Javaproject5sna.service.PersonService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
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
		
		//Act
		Person createPerson = personService.createPerson(person);
		
		//Assert
		assertEquals(createPerson.getFirstName(), "John");
		assertEquals(createPerson.getLastName(), "Boyd");
		assertEquals(createPerson.getAddress(), "175 Culver St");
		assertEquals(createPerson.getCity(), "Culver");
		assertEquals(createPerson.getZip(), "75009");
		assertEquals(createPerson.getPhone(), "888-987-7878");
		assertEquals(createPerson.getEmail(), "jaboyd@email.com");
		
	}
	
	@Test
	public void getAllPerson_returnAllPerson() {
		
		List<Person> person = new ArrayList<>();
		
		Person person1 = new Person();
		person1.setFirstName("John");
		person1.setLastName("Boyd");
		person1.setAddress("175 Culver St");
		person1.setCity("Culver");
		person1.setZip("97451");
		person1.setPhone("841-874-7466");
		person1.setEmail("encore317@email.com");
		
        Person person2 = new Person();
		person2.setFirstName("David");
		person2.setLastName("Bower");
		person2.setAddress("144 Culver St");
		person2.setCity("Culver");
		person2.setZip("97451");
		person2.setPhone("845-875-7444");
		person2.setEmail("encore@email.com");
		
		person.add(person1);
		person.add(person2);
		when(personRepository.findAll()).thenReturn(person);
		
		//Act		
		List<Person> findAllPerson = personService.getAllPerson();
		
		//Assert
		assertEquals(2, findAllPerson.size());
		
	}
		
	@Test
	public void jsonObject_getPersonInfo_returnPersonInfo() throws Exception {
		
		//List<JSONObject> allInfoOfPerson = new ArrayList<>();
		
		//Arrange
		 Set<String> medications = new HashSet<String>();
		 medications.add("aznol:350mg, hydrapermazol:100mg");
	
	     Set<String> allergies = new HashSet<>();
		 allergies.add("nilliacilan");
		 
		 JSONObject personInfo = new JSONObject(); 
		 personInfo.put("firstName", "John");
		// personInfo.put("John", person.setFirstName(firstName);getFirstName());	
		 personInfo.put("lastName", "Boyd");
		 personInfo.put("address", "1509 Culver St");
		 personInfo.put("age", "22");
		 personInfo.put("email", "jaboyd@email.com");
		 personInfo.put("medication", medications);
		 personInfo.put("allergies", allergies);
		 
	     //allInfoOfPerson.add(personInfo);
		Person person = new Person();
		 person.setFirstName("John");
		 person.setLastName("Boyd");
		 person.setAddress("1509 Culver St");
		 person.setEmail("jaboyd@email.com");
		 
		 MedicalRecord medicalRecord = new MedicalRecord();
		 medicalRecord.setBirthDate("19/07/1998");
		 medicalRecord.setMedications(medications);
		 medicalRecord.setAllergies(allergies);
		 
         person.setMedicalRecord(medicalRecord);
         personRepository.createPerson(person);
		
		//Act
		 List<JSONObject> newPersonInfo = personService.getPersonInfo("John", "Boyd");
		 
		//Assert
		 assertEquals(personInfo, newPersonInfo);
			 	
	}
		
	@Test
	public void getPersonsEmail_returnPersonsEmail() {
		
		//Arrange
		List<String> personsEmailInTheCity = new ArrayList<>();
		
		Person person = new Person();
		person.setCity("Culver");
		person.setEmail("jaboyd@email.com");
		personRepository.createPerson(person);
		
		personsEmailInTheCity.add("jaboyd@email.com");
		personsEmailInTheCity.add("Culver");
		
		
		//Act
		List<String> emailAddressInCity = personService.getPersonsEmailAddress("Culver");
		
		//Assert
		assertEquals(personsEmailInTheCity, emailAddressInCity);
		
	}

}
