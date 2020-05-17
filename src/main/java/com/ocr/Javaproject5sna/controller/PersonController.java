package com.ocr.Javaproject5sna.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.PersonRepository;
import com.ocr.Javaproject5sna.service.PersonService;

@RestController
public class PersonController {

	Logger logger =LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	PersonService personService;
	
	@Autowired
    PersonRepository personRepository;
	
	Person person;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@PostMapping("/person")
	public void createPerson(@RequestBody @Valid Person person, BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors()) {
			personService.createPerson(person);
		}
	}
	
	@PutMapping("/person")
	public void updatePerson(@RequestBody Person person) {
		
		personRepository.updatePerson(person);
	}
	
	@GetMapping("/persons") 
	public List<Person> getAllPerson() {
		
		logger.info("GET /person called");
		
		return personService.getAllPerson();
	}
	
	@DeleteMapping("/person") 
	public void deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
		
		personRepository.deletePerson(firstName, lastName);
	}	
		
	//Person Url
	
	@GetMapping("/childAlert")
	public JSONObject getChildrenAlert(@RequestParam String address) {
		
		return personService.getChildrenFromEachAddress(address);
	}
	
	@GetMapping("/personInfo")
	public List<JSONObject> getPersonByInfo(@RequestParam String firstName, @RequestParam String lastName) {
		
		return personService.getPersonInfo(firstName, lastName);
	}
	
	@GetMapping("/communityEmail")
	public List<String> getCommunityEmail(@RequestParam String city) {
		
		return personService.getPersonsEmailAddress(city);
	}
	
	
	
}
