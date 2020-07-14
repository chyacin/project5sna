package com.ocr.Javaproject5sna.service;

import java.util.List;


import com.ocr.Javaproject5sna.dto.ChildAlertDTO;
import com.ocr.Javaproject5sna.dto.PersonMedicalInfoDTO;
import com.ocr.Javaproject5sna.model.Person;

public interface IPersonService {

	public Person createPerson(Person person);

	public void updatePerson(Person person);

	public void deletePerson(String firstName, String lastName);
	
	public List<Person> findAll();
	
	public Person findPerson(String firstName, String lastName);

	public List<PersonMedicalInfoDTO> getPersonInfo(String firstName, String lastName);

	public List<String> getPersonsEmailAddress(String city);

	public ChildAlertDTO getChildrenFromEachAddress(String address);
}
