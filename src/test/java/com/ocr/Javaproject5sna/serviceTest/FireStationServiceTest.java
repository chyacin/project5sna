package com.ocr.Javaproject5sna.serviceTest;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ocr.Javaproject5sna.model.FireStation;
import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.FireStationRepository;
import com.ocr.Javaproject5sna.service.FireStationService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@RunWith(MockitoJUnitRunner.class)
public class FireStationServiceTest {

    @InjectMocks
    FireStationService fireStationService;
	
	@Mock
	FireStationRepository fireStationRepository;
	
	
	@Test
	public void createFSAddressMapping_returnFSAddressMapping() {
		
		//Arrange
		FireStation fireStation = new FireStation();
		String stationNumber = "2";
		String address = "188 Culver St";
		fireStation.addAddress(address);
		fireStation.setStationNumber(stationNumber);
		Mockito.when(fireStationRepository.findStation(stationNumber)).thenReturn(fireStation);
		
		//Act
		FireStation result = fireStationService.createFSAddressmapping("2", "188 Culver St");
		 
		//Assert
		assertNotNull(result);	
		assertTrue(result.equals(fireStation));
		assertEquals(address, new ArrayList(result.getAddresses()).get(0));
		assertEquals(stationNumber, result.getStationNumber());
		//System.out.println(fireStation);
		
	}
	
	@Test
	public void updateFSAddressMapping_returnFSAddressMapping() {
		
		//Arrange
		FireStation fireStation = new FireStation();
		String stationNumber = "2";
		String address = "188 Culver St";
		fireStation.addAddress(address);
		fireStation.setStationNumber(stationNumber);
		List<FireStation> fireStations = new ArrayList<>();
	    fireStations.add(fireStation);
		Mockito.when(fireStationRepository.createStation(Mockito.any(FireStation.class))).thenReturn(fireStation);
	    Mockito.when(fireStationRepository.findAll()).thenReturn(fireStations);
		
		//Act
		fireStationService.updateAddressFSNumber("3", "175 Culver St");
		
		//Assert 
		Mockito.verify(fireStationRepository).addFireStation(fireStation);
		
	}
		
	
	
	@Test
	public void deleteFSAddressMapping_successfullyDeletedFSAddressMapping() {	
		
		//Act
		fireStationService.deleteFSAddressMapping("2", "175 Culver St");
		
		//Assert
		Mockito.verify(fireStationRepository, times(1));
	}
	
	
	// url end points testing
	
	@Test
	public void getPersonDetailsFromFireStationNumber() {
		
	  //Arrange
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setBirthDate("03/06/1984");
		MedicalRecord medicalRecord1 = new MedicalRecord();
		medicalRecord1.setBirthDate("09/06/2017");
		
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		person.setPhone("841-874-6512");
		person.setMedicalRecord(medicalRecord);
		
		Person person1 = new Person();
		person1.setFirstName("Roger");
		person1.setLastName("Boyd");
		person1.setAddress("1509 Culver St");
		person1.setPhone("841-874-6512");
		person1.setMedicalRecord(medicalRecord1);

				
		String stationNumber = "3";
		
		FireStation fireStation = new FireStation(stationNumber);
		fireStation.addPerson(person);
		fireStation.addPerson(person1);	
		
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(fireStation);
		Mockito.when(fireStationRepository.findAll()).thenReturn(fireStations);
		
		//Act
		JSONObject result = fireStationService.getPersonDetailsFromFireStationNumber(stationNumber);
		List<JSONObject> numberOfKidsPlusAdults = (List<JSONObject>) result.get("numberOfKidsPlusAdults");
		List<JSONObject> personsDetails = (List<JSONObject>) result.get("personsDetails");
		
		//Assert
		assertNotNull(result);	
		assertEquals(1, numberOfKidsPlusAdults.size());
		assertEquals(2, personsDetails.size());
		assertEquals(2,result.size());			
		
	}
	@Test
	public void getPersonPhoneNumberFromWithInEachFireStation() {
		
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		person.setPhone("841-874-6512");
		
		Person person1 = new Person();
		person1.setFirstName("Jacob");
		person1.setLastName("Boyd");
		person1.setAddress("1509 Culver St");
		person1.setPhone("841-874-6513");
		
		String stationNumber = "3";
		FireStation fireStation = new FireStation(stationNumber);
		fireStation.addPerson(person);
		fireStation.addPerson(person1);
		
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(fireStation);
		
		Mockito.when(fireStationRepository.findAll()).thenReturn(fireStations);
		
		//Act
		List<String> result = fireStationService.getPersonPhoneNumberFromWithInEachFireStation("3");
		
		//Assert
		assertNotNull(result);
		assertEquals(2, result.size());
     	assertEquals("841-874-6512", result.get(0));
     	assertEquals("841-874-6513", result.get(1));
        //System.out.println(result.get(0));
        //System.out.println(result.get(1));
	} 
	
	@Test
	public void getAddressFromEachStation() {
		
		//Arrange
		Set<String> medications = new HashSet<String>();
		medications.add("aznol:350mg, hydrapermazol:100mg");
	    Set<String> allergies = new HashSet<>();
		allergies.add("nilliacilan");
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setBirthDate("03/06/1984");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);
		
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		person.setPhone("841-874-6512");
		person.setMedicalRecord(medicalRecord);
		
		String stationNumber ="3";
		String address = "1509 Culver St";
		
		FireStation fireStation = new FireStation(stationNumber, address);
		fireStation.addPerson(person);
		
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(fireStation);
		Mockito.when(fireStationRepository.findAll()).thenReturn(fireStations);

		//Act
		JSONObject result = fireStationService.getAddressFromEachStation(address);
		List<JSONObject> personsInAddress = (List<JSONObject>) result.get("personsInAddress");
		String fireStationNumber = (String) result.get("fireStationNumber");
		
		//Assert
	   assertNotNull(result);	
       assertEquals(1, personsInAddress.size());
       assertEquals(1, stationNumber.length());
       assertEquals(2, result.size());
       assertEquals(address, personsInAddress.get(0).get("address"));
      // assertEquals(address, fireStationNumber);

     //  System.out.println(personsInAddress);
	}
	
	@Test
	public void getPersonByHouseHoldsInEachStationNumber() {
		
		//Arrange
		Set<String> medications = new HashSet<String>();
		medications.add("aznol:350mg, hydrapermazol:100mg");
	    Set<String> allergies = new HashSet<>();
		allergies.add("nilliacilan");
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setBirthDate("03/06/1984");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);
				
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Boyd");
		person.setAddress("1509 Culver St");
		person.setPhone("841-874-6512");
		person.setMedicalRecord(medicalRecord);
		
		
		String stationNumber ="3";
		FireStation fireStation = new FireStation(stationNumber);
		fireStation.addPerson(person);
		
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(fireStation);
		Mockito.when(fireStationRepository.findAll()).thenReturn(fireStations);
		
		//Act
		List<JSONObject> result = fireStationService.getPersonByHouseHoldsInEachStationNumber(stationNumber);
		
		
		//Assert
		assertNotNull(result); 
		//System.out.println(result);
		
	}
}
