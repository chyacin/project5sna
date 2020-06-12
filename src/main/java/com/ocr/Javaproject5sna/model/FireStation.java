package com.ocr.Javaproject5sna.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.ocr.Javaproject5sna.model.Person;


public class FireStation {
	

	public FireStation() {
		
	}
	
    public FireStation(String stationNumber, String address) {
		this.stationNumber = stationNumber;
		this.addresses.add(address);
    	
	}
	private Set<String> addresses = new HashSet<>();	
	private String stationNumber;
	
	

	public List<Person> personList = new ArrayList<>();
	
	
	public FireStation(String stationNumber) {
		this.stationNumber = stationNumber;
		this.personList = new ArrayList<>();
	}


	public FireStation addAddress(String address) {
		addresses.add(address);
		return this;
	}

	public String getStationNumber() {
		return this.stationNumber;
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
	
	//associate FireStation class to Person class
	public void addPerson(Person person) {
		this.personList.add(person);
	}
	
	public List<Person> getPersonList() {
		return this.personList;
	}
	
	
	public int getNumberOfAdults() {
			
		int numberOfAdults = 0;
		for(Person person: personList) {
			if(person.getAge()>18) {
			   numberOfAdults++;
			}
		}
		return numberOfAdults;
	}
	
	public int getNumberOfChildren() {
		
		int numberOfChildren = 0;
		for(Person person: personList) {
			if(person.getAge()<= 18) {
			   numberOfChildren++;
		    }
	    }
		return numberOfChildren;
	}


	@Override
	public String toString() {
		return "FireStation [addresses=" + addresses + ", stationNumber=" + stationNumber + "]";
	}


	


	


	
}
