package com.ocr.Javaproject5sna.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.ocr.Javaproject5sna.model.Person;

public interface IPersonService {

	public Person createPerson(Person person);
	public List<Person> getAllPerson();
	public List<JSONObject> getPersonInfo(String firstName, String lastName);
	public List<String> getPersonsEmailAddress(String city);
	public JSONObject getChildrenFromEachAddress(String address);
}
