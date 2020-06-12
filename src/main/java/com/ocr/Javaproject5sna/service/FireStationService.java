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
public class FireStationService implements IFireStationService {
	
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
	
	List<FireStation> fireStations = fireStationRepository.findAll();	
	  for(FireStation fireStation: fireStations) {
		if(fireStation.getAddresses().contains(address)) {
			
			fireStation.removeAddress(address);
			fireStationRepository.updateStation(fireStation);
		}
	  }
	  for(FireStation fireStation: fireStations) {
		  if(fireStation.getStationNumber().equals(stationNumber)){
			
			fireStation.addAddress(address);
			fireStationRepository.updateStation(fireStation);
			return;
		  }
	   }
		 FireStation fireStation = new FireStation(stationNumber, address);
		 fireStation = fireStationRepository.createStation(fireStation);
		 fireStationRepository.addFireStation(fireStation);
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
	
	/* returns full name, address, phone number and also a number of children and adults from each station.
	 * below is the url address.
	 * http://localhost:8080/firestation?stationNumber=<station_number>
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getPersonDetailsFromFireStationNumber(String stationNumber) {
		
		JSONObject personDetailsFromStationNumber = new JSONObject();
		List<JSONObject> numberOfKidsPlusAdults= new ArrayList<>();
		List<JSONObject> personsDetails = new ArrayList<>();
		
		   for(FireStation fireStation: fireStationRepository.findAll()) {
			  if(fireStation.getStationNumber().equals(stationNumber)) {
		
				for(Person person : fireStation.getPersonList()) {
					JSONObject personDetails = new JSONObject();
					personDetails.put("firstName",person.getFirstName()); 
					personDetails.put("lastName", person.getLastName());
					personDetails.put("address", person.getAddress());
					personDetails.put("phoneNumber", person.getPhone());
						
						personsDetails.add(personDetails);
				}	  
				JSONObject numberOfPersons = new JSONObject();		
			    numberOfPersons.put("numberOfChildren", fireStation.getNumberOfChildren());
			    numberOfPersons.put("numberOfAdults", fireStation.getNumberOfAdults());
			    
			    numberOfKidsPlusAdults.add(numberOfPersons);    
	         }
           }
		   personDetailsFromStationNumber.put("personsDetails", personsDetails);
		   personDetailsFromStationNumber.put("numberOfKidsPlusAdults", numberOfKidsPlusAdults);
		    
	       return personDetailsFromStationNumber;  
    } 

	     			
	
	/* returns the phone Numbers of persons within a fireStation
	 * below is the url address
	 * http://localhost:8080/phoneAlert?firestation=<firestation_number>
	 */
	public List<String> getPersonPhoneNumberFromWithInEachFireStation(String stationNumber) {
	
		List<String> phoneNumberOfEachPerson = new ArrayList<>();
	
		for(FireStation fireStation: fireStationRepository.findAll()) {
			if(fireStation.getStationNumber().equals(stationNumber)) {
	
	      for(Person person: fireStation.getPersonList()) {
		        phoneNumberOfEachPerson.add(person.getPhone());
	         }
		  }	    
		}
		return phoneNumberOfEachPerson;
	}	
	
	/*returns persons details and addresses assigned to the relevant fireStation number.
	 * below is the url address
	 * http://localhost:8080/fire?address=%3Caddress
	 */
	 @SuppressWarnings("unchecked")
	public JSONObject getAddressFromEachStation(String address) {
		
		String stationNumber = null;
		JSONObject detailsFromStationAddress = new JSONObject();
		List<JSONObject> personsInAddress = new ArrayList<>();
		
		for(FireStation fireStation: fireStationRepository.findAll()) {
			if(fireStation.getAddresses().contains(address)) {
				stationNumber = fireStation.getStationNumber();
			}
		
		for(Person person: fireStation.getPersonList()) {
			if(person.getAddress().equals(address)) {
				
				JSONObject personInEachAddress = new JSONObject();
				
				personInEachAddress.put("firstName", person.getFirstName());
				personInEachAddress.put("lastName", person.getLastName());
				personInEachAddress.put("phoneNumber", person.getPhone());
				personInEachAddress.put("address", person.getAddress());
				personInEachAddress.put("age", person.getAge());
				personInEachAddress.put("medicationsWithDosage", person.getMedication());
				personInEachAddress.put("allergies", person.getAllergies());
				
				personsInAddress.add(personInEachAddress);
			}
		}
		
		    detailsFromStationAddress.put("fireStationNumber", stationNumber);
		    detailsFromStationAddress.put("personsInAddress", personsInAddress);
		}
		return detailsFromStationAddress;
	}
	
	/*return houseHolds with persons in each fireStation jurisdiction
	 * below is the url address.
	 * http://localhost:8080/flood/stations?stations=%3Ca
	 */
	@SuppressWarnings("unchecked")
	public List<JSONObject> getPersonByHouseHoldsInEachStationNumber(String stationNumber) {
		
		List<String> addressesInFireStation = new ArrayList<>();
		List<JSONObject> addressesWithPersons = new ArrayList<>();
	    
		for(FireStation fireStation: fireStationRepository.findAll()) {
			  if(fireStation.getStationNumber().equals(stationNumber)) {
			addressesInFireStation.addAll(fireStation.getAddresses());	
		  }
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
	
