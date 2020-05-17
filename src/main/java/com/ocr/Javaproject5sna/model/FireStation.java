package com.ocr.Javaproject5sna.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.PersonRepository;

public class FireStation {
	
	PersonRepository personRepository;
	
	public FireStation() {
		
	}

	private Set<String> addresses = new HashSet<>();
	
	private String address;
	private String stationNumber;

	public FireStation(String stationNumber) {
		this.stationNumber = stationNumber;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public FireStation addAddress(String address) {
		addresses.add(address);
		return this;
	}

	public String getStationNumber() {
		return stationNumber;
	}
	
	public void setStationNumber(String stationNumber) {
		this.stationNumber = stationNumber;
	}

	public Set<String> getAddresses() {
		return addresses.stream().collect(Collectors.toSet());
	}
	
	public void setAddresses(Set<String> addresses) {
		this.addresses = addresses;
	}

	public void removeAddress(String address) {
    	addresses.remove(address);
    }
	
	@Override
	public String toString() {
		return stationNumber.concat(": ") + String.join(", ", addresses);
	}


	public List<Person> getPersonInEachStation(){
		
		List<Person> allPersons = personRepository.findAll();
		
		List<Person> personsInEachFireStation = new ArrayList<>();
		
		for(Person person: allPersons) {
			if(this.addresses.contains(person.address))
				personsInEachFireStation.add(person);
		}
		
		return personsInEachFireStation;
	}
    
    public int getNumberOfAdults() {
		
		List<Person> persons = personRepository.findAll();
		
		int numberOfAdults = 0;
		
		for(Person person: persons) {
			if(person.getAge() > 18) {
				numberOfAdults++;
			}
		}
		return numberOfAdults;
	}
	
	public int getNumberOfChildren() {
		
		List<Person> persons = personRepository.findAll();
		
		int numberOfChildren = 0;
		
		for(Person person: persons) {
			if(person.getAge() <= 18) {
				numberOfChildren++;
			}
		}
		return numberOfChildren;
	}
}
