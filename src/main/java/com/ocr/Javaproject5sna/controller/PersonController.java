package com.ocr.Javaproject5sna.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.service.PersonService;

public class PersonController {

	Logger logger =LoggerFactory.getLogger(PersonController.class);
	
	PersonService personService;
	
	Person person;
	
	@PostMapping("/person")
	public void createPerson(@RequestBody @Valid Person person, BindingResult bindingResult) {
		
		if(!bindingResult.hasErrors()) {
			personService.createPerson(person);
		}
	}
	
	@PutMapping("/person")
	public void updatePerson(@RequestBody Person person) {
		
		personService.updatePerson(person);
	}
	
	@GetMapping("/person") 
	public List<Person> getAllPerson() {
		
		logger.info("GET /person called");
		
		return personService.getAllPerson();
	}
	
	@DeleteMapping("/person") 
	public void deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
		
		personService.deletePerson(firstName, lastName);
	}	
		
	
}
