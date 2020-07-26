package com.ocr.Javaproject5sna.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.Javaproject5sna.dto.PersonDetailsDTO;
import com.ocr.Javaproject5sna.dto.PersonDetailsFromStationNumberDTO;
import com.ocr.Javaproject5sna.dto.PersonInEachAddressDTO;
import com.ocr.Javaproject5sna.dto.PersonInfoPlusAddressFromEachStationDTO;
import com.ocr.Javaproject5sna.dto.EveryHouseHoldInfoDTO;
import com.ocr.Javaproject5sna.dto.NumberOfPersonsDTO;
import com.ocr.Javaproject5sna.model.FireStation;
import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.FireStationRepository;
import com.ocr.Javaproject5sna.repository.PersonRepository;

@Service
public class FireStationService implements IFireStationService {

	Logger logger = LoggerFactory.getLogger(FireStationService.class);

	FireStationRepository fireStationRepository;

	PersonRepository personRepository;

	@Autowired
	public FireStationService(FireStationRepository fireStationRepository, PersonRepository personRepository) {
		this.fireStationRepository = fireStationRepository;
		this.personRepository = personRepository;
	}

	public FireStation createFSAddressmapping(String stationNumber, String address) {

		FireStation fireStation = fireStationRepository.findStation(stationNumber);
		if (fireStation != null) {

			fireStation.addAddress(address);
			fireStationRepository.createStation(fireStation);

		} else {
			fireStation = fireStationRepository.createStation(fireStation);
			fireStation.addAddress(address);
			fireStationRepository.updateStation(fireStation);
		}
		return fireStation;
	}

	public void updateAddressFSNumber(String stationNumber, String address) {

		List<FireStation> fireStations = fireStationRepository.findAll();
		for (FireStation fireStation : fireStations) {
			if (fireStation.getAddresses().contains(address)) {

				fireStation.removeAddress(address);
				fireStationRepository.updateStation(fireStation);
			}
		}
		for (FireStation fireStation : fireStations) {
			if (fireStation.getStationNumber().equals(stationNumber)) {

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

		for (FireStation fireStation : fireStationRepository.findAll()) {
			if (fireStation.getAddresses().contains(address)) {

				fireStation.removeAddress(address);
				fireStationRepository.updateStation(fireStation);
			}
		}
	}

//	public List<FireStation> findAll() {
//
//		return fireStationRepository.findAll();
//	}
	
//	public FireStation findStation(String stationNumber) {
//		return fireStationRepository.findStation(stationNumber);
//	}

	// Url end points

	/*
	 * returns full name, address, phone number and also a number of children and
	 * adults from each station. below is the url address.
	 * http://localhost:8080/firestation?stationNumber=<station_number>
	 */
	public PersonDetailsFromStationNumberDTO getPersonDetailsFromFireStationNumber(String stationNumber) {

		PersonDetailsFromStationNumberDTO personDetailsFromStationNumber = new PersonDetailsFromStationNumberDTO();
		ArrayList<NumberOfPersonsDTO> numberOfKidsPlusAdults = new ArrayList<>();
		ArrayList<PersonDetailsDTO> personsDetails = new ArrayList<>();

		for (FireStation fireStation : fireStationRepository.findAll()) {
			if (fireStation.getStationNumber().equals(stationNumber)) {

				for (Person person : fireStation.getPersonList()) {
					PersonDetailsDTO personDetails = new PersonDetailsDTO();
					personDetails.setFirstName(person.getFirstName());
					personDetails.setLastName(person.getLastName());
					personDetails.setAddress(person.getAddress());
					personDetails.setPhoneNumber(person.getPhone());

					personsDetails.add(personDetails);
				}
				NumberOfPersonsDTO numberOfPersons = new NumberOfPersonsDTO();
				numberOfPersons.setChildren(fireStation.getNumberOfChildren());
				numberOfPersons.setAdults(fireStation.getNumberOfAdults());

				numberOfKidsPlusAdults.add(numberOfPersons);
			}
		}
		personDetailsFromStationNumber.setPersonsDetails(personsDetails);
		personDetailsFromStationNumber.setNumberOfKidsPlusAdult(numberOfKidsPlusAdults);

		return personDetailsFromStationNumber;
	}

	/*
	 * returns the phone Numbers of persons within a fireStation below is the url
	 * address 
	 * http://localhost:8080/phoneAlert?firestation=<firestation_number>
	 */
	public List<String> getPersonPhoneNumberFromFireStationNumber(String stationNumber) {

		List<String> phoneNumberOfEachPerson = new ArrayList<>();

		for (FireStation fireStation : fireStationRepository.findAll()) {
			if (fireStation.getStationNumber().contentEquals(stationNumber)) {
				for (Person person : fireStation.getPersonList()) {
					phoneNumberOfEachPerson.add(person.getPhone());
				}
			}
		}
		return phoneNumberOfEachPerson;
	}

	/*
	 * returns persons details and addresses assigned to the relevant fireStation
	 * number. below is the url address
	 * http://localhost:8080/fire?address=%3Caddress
	 */

	public PersonInfoPlusAddressFromEachStationDTO getAddressFromEachStation(String address) {

		String stationNumber = null;
		PersonInfoPlusAddressFromEachStationDTO personDetailsFromEachStationAddress = new PersonInfoPlusAddressFromEachStationDTO();
		ArrayList<PersonInEachAddressDTO> personsDetails = new ArrayList<>();

		for (FireStation fireStation : fireStationRepository.findAll()) {
			if (fireStation.getAddresses().contains(address)) {
				stationNumber = fireStation.getStationNumber();
			}

			for (Person person : fireStation.getPersonList()) {
				if (person.getAddress().equals(address)) {

					PersonInEachAddressDTO personDetails = new PersonInEachAddressDTO();

					personDetails.setFirstName(person.getFirstName());
					personDetails.setLastName(person.getLastName());
					personDetails.setPhoneNumber(person.getPhone());
					personDetails.setAddress(person.getAddress());
					personDetails.setAge(person.getAge());
					personDetails.setMedications(person.getMedication());
					personDetails.setAllergies(person.getAllergies());

					personsDetails.add(personDetails);
				}
			}

			personDetailsFromEachStationAddress.setStationNumber(stationNumber);
			personDetailsFromEachStationAddress.setPersonsInAddress(personsDetails);
		}
		return personDetailsFromEachStationAddress;
	}

	/*
	 * return houseHolds with persons in each fireStation jurisdiction below is the
	 * url address.
	 *  http://localhost:8080/flood/stations?stations=%3Ca
	 */
	public List<EveryHouseHoldInfoDTO> getPersonByHouseHoldsInEachStationNumber(String stationNumber) {

		List<String> allAddressesInEachStation = new ArrayList<>();
		List<EveryHouseHoldInfoDTO> personsWithAddressByEachStationNumber = new ArrayList<>();

		for (FireStation fireStation : fireStationRepository.findAll()) {
			if (fireStation.getStationNumber().equals(stationNumber)) {
				allAddressesInEachStation.addAll(fireStation.getAddresses());
			}
		}
		for (String address : allAddressesInEachStation) {

			EveryHouseHoldInfoDTO eachHouseHoldWithPersons = new EveryHouseHoldInfoDTO();
			eachHouseHoldWithPersons.addAddress(address);

			ArrayList<PersonInEachAddressDTO> everybodyInfo = new ArrayList<>();
			for (Person person : personRepository.findAll()) {
				if (person.getAddress().equals(address)) {

					PersonInEachAddressDTO personInfo = new PersonInEachAddressDTO();

					personInfo.setFirstName(person.getFirstName());
					personInfo.setLastName(person.getLastName());
					personInfo.setAge(person.getAge());
					personInfo.setAddress(person.getAddress());
					personInfo.setPhoneNumber(person.getPhone());
					personInfo.setMedications(person.getMedication());
					personInfo.setAllergies(person.getAllergies());
					everybodyInfo.add(personInfo);
				}
			}
			eachHouseHoldWithPersons.setEverybodyInfo(everybodyInfo);

			personsWithAddressByEachStationNumber.add(eachHouseHoldWithPersons);
		}

		return personsWithAddressByEachStationNumber;

	}
}
