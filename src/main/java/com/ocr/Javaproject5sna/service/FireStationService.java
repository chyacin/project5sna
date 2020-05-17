package com.ocr.Javaproject5sna.service;



import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.Javaproject5sna.model.FireStation;
import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.FireStationRepository;
import com.ocr.Javaproject5sna.repository.PersonRepository;

@Service
public class FireStationService {
	
	
	FireStationRepository fireStationRepository;

	
	
	
	PersonRepository personRepository;
	
	@Autowired
	public FireStationService(FireStationRepository fireStationRepository, PersonRepository personRepository){
		this.fireStationRepository = fireStationRepository;
		this.personRepository = personRepository;
	}
	
	public FireStation createFSAddressmapping(String stationNumber, String address) {
		
		    FireStation fireStation = fireStationRepository.findStation(stationNumber);
			 if(fireStation!=null) {
				 
				 fireStation.addAddress(address);
				 fireStationRepository.createStation(fireStation);
			 
			 } else {
				 
				 fireStation = fireStationRepository.createStation(fireStation);
				 
				 fireStation.addAddress(address);
				 
				 fireStationRepository.updateStation(fireStation);
			   }
			 return fireStation;
		 }
	
    public List<FireStation> getAllFireStation(){
		
		return fireStationRepository.findAll();
	}
	
	
	public void updateAddressFSNumber(String stationNumber, String address) {
		
	  for(FireStation fireStation: fireStationRepository.findAll())	
		if(fireStation.getAddresses().contains(address)) {
			
			fireStation.removeAddress(address);
			fireStationRepository.updateStation(fireStation);
		}
		
	    FireStation fireStation = fireStationRepository.findStation(stationNumber);
		if(fireStation!=null) {
			
			fireStation.addAddress(address);
			return;
		}
		
		 fireStation = new FireStation(stationNumber);
		 fireStation = fireStationRepository.createStation(fireStation);
         fireStation.addAddress(address);
		 fireStationRepository.findAll().add(fireStation);
	}
	    
	
	   
	public void deleteFSAddressMapping(String stationNumber, String address) {
		
		for(FireStation fireStation: fireStationRepository.findAll()) {
		    if(fireStation.getAddresses().contains(address)) {
			
			   fireStation.removeAddress(address);
			   fireStationRepository.updateStation(fireStation);
		    }
		    
	    }
	}
	
	//Url end points
	
	public JSONObject getPersonDetailsFromFireStationNumber(String stationNumber) {
		
		JSONObject fireStationDetails = new JSONObject();
		
		 FireStation fireStation = fireStationRepository.findStation(stationNumber);
			if(fireStation!=null)  {
			
			fireStationDetails.put("persons", fireStation.getPersonInEachStation());
			fireStationDetails.put("numberOfAdults", fireStation.getNumberOfAdults());
			fireStationDetails.put("numberOfChildren", fireStation.getNumberOfChildren());
		}
	    return fireStationDetails;
	} 
	
	public List<String> getPersonPhoneNumberFromWithInEachFireStation(String stationNumber) {
		
		List<Person> personInEachFireStation = new ArrayList<>();
		List<String> phoneNumberOfEachPerson = new ArrayList<>();
		
		 FireStation fireStation = fireStationRepository.findStation(stationNumber);
			if(fireStation!=null)  {
			personInEachFireStation = fireStation.getPersonInEachStation();
		}
	
	    for(Person person: personInEachFireStation) {
		    phoneNumberOfEachPerson.add(person.getPhone());
	    }
	       return phoneNumberOfEachPerson;
	}	
	
	//addresses assigned to the relevant fireStation number.
	
	public JSONObject getAddressFromEachStation(String address) {
		
		String stationNumber = null;
		JSONObject detailsFromStationAddress = new JSONObject();
		
		for(FireStation fireStation: fireStationRepository.findAll()) {
			if(fireStation.getAddresses().contains(address)) {
				stationNumber = fireStation.getStationNumber();
			}
		}
		
		List<JSONObject> personsInAddress = new ArrayList<>();
		
		for(Person person: personRepository.findAll()) {
			if(person.getAddress().equals(address)) {
				
				JSONObject personInEachAddress = new JSONObject();
				
				personInEachAddress.put("firstName", person.getFirstName());
				personInEachAddress.put("lastName", person.getLastName());
				personInEachAddress.put("phoneNumber", person.getPhone());
				personInEachAddress.put("age", person.getAge());
				personInEachAddress.put("medicationsWithDosage", person.getMedication());
				personInEachAddress.put("allergies", person.getAllergies());
				
				personsInAddress.add(personInEachAddress);
			}
		}
		
		detailsFromStationAddress.put("stationNumber", stationNumber);
		detailsFromStationAddress.put("personsInAddress", personsInAddress);
		
		return detailsFromStationAddress;
	}
	
	//return houseHolds with persons in each fireStation jurisdiction
	
	public List<JSONObject> getPersonByHouseHoldsInEachStationNumber(String stationNumber) {
		
		List<String> addressesInFireStation = new ArrayList<>();
		List<JSONObject> addressesWithPersons = new ArrayList<>();
		
		 FireStation fireStation = fireStationRepository.findStation(stationNumber);
			if(fireStation!=null)  {
			addressesInFireStation.addAll(fireStation.getAddresses());	
		  }
	
	    for(String address: addressesInFireStation) {
	    	
	    	JSONObject eachHouseHoldWithPersons = new JSONObject();
	    	eachHouseHoldWithPersons.put("householdAddress", address);
	    	
	    List<JSONObject> everybodyInfoInHousehold = new ArrayList<>();
	    
	    for(Person person: personRepository.findAll()) {
	    	if(person.getAddress().equals(address)) {
	    	
	    	   JSONObject personsInfoInHousehold = new JSONObject();
	    	
	    	   personsInfoInHousehold.put("firstName", person.getFirstName());
    		   personsInfoInHousehold.put("lastName", person.getLastName());
    		   personsInfoInHousehold.put("age", person.getAge());
    		   personsInfoInHousehold.put("phoneNumber", person.getPhone());
    		   personsInfoInHousehold.put("medicationsWithDosage", person.getMedication());
    		   personsInfoInHousehold.put("allergies", person.getAllergies());
    		
    		   everybodyInfoInHousehold.add(personsInfoInHousehold);
	        }
	    }
	    
	    eachHouseHoldWithPersons.put("everybodyInfoInHousehold", everybodyInfoInHousehold);
	    
	    addressesWithPersons.add(eachHouseHoldWithPersons);
	    
	 }
	    return addressesWithPersons;
  }
}
	
