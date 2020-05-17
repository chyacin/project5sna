package com.ocr.Javaproject5sna.serviceTest;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.PersonRepository;
import com.ocr.Javaproject5sna.service.PersonService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
	
	@Mock
	Person person;
	
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
	public void jsonObject_getPersonInfo_returnPersonInfo()  {
		
		//Arrange
		 Set<String> medications = new HashSet<String>();
		  medications.add("aznol:350mg, hydrapermazol:100mg");
	
	     Set<String> allergies = new HashSet<>();
		  allergies.add("nilliacilan");
			
		 JSONObject personInfo = new JSONObject();	 
		 personInfo.put("John", person.getFirstName());
		 personInfo.put("Boyd", person.getLastName());
		 personInfo.put("1509 Culver St", person.getAddress());
		 personInfo.put("age", person.getAge());
		 personInfo.put("jaboyd@email.com", person.getEmail());
		 personInfo.put("medicationsWithDosage", person.getMedication());
		 personInfo.put("allergies", person.getAllergies());
		
		//Act
		 List<JSONObject> newPersonInfo = personService.getPersonInfo("John", "Boyd");
		 
		 //Assert
		 assertEquals(personInfo, newPersonInfo);
		
		 	
	}
		
	

}
