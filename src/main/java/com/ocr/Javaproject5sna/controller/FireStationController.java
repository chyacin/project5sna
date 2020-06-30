package com.ocr.Javaproject5sna.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ocr.Javaproject5sna.dto.EveryHouseHoldInfoDTO;
import com.ocr.Javaproject5sna.dto.PersonDetailsFromStationNumberDTO;
import com.ocr.Javaproject5sna.dto.PersonInfoPlusAddressFromEachStationDTO;
import com.ocr.Javaproject5sna.model.FireStation;
import com.ocr.Javaproject5sna.service.FireStationService;

@RestController
public class FireStationController {
	
	

	Logger logger = LoggerFactory.getLogger(FireStationController.class);
	
	
	FireStationService fireStationService;
	
	
	@Autowired
    public FireStationController(FireStationService fireStationService) {
		   this.fireStationService = fireStationService;
	}
	
	//fireStation endPoints
	@PostMapping("/firestation")
	public void createFSAddressMapping(@RequestParam String stationNumber, @RequestParam String address) {
		
		fireStationService.createFSAddressmapping(stationNumber, address);
	
	}
	
	@PutMapping("/firestation")
	public void updateAddressFSAddressMapping(@RequestBody String address, @RequestBody String stationNumber ) {
		
		fireStationService.updateAddressFSNumber(stationNumber, address);
	}
	
	@GetMapping("/firestations")
	public List<FireStation> getFireStation(){
		
		logger.info("GET /firestation called");
		
		return fireStationService.getAllFireStation();
	}
	
	@DeleteMapping("/firestation")
	public void deleteFSAddressMapping(@RequestParam String address, @RequestParam String stationNumber) {
		
		fireStationService.deleteFSAddressMapping(stationNumber, address);
	}
	
	//firestation Url
	
	@GetMapping("/firestation")
	public PersonDetailsFromStationNumberDTO getPersonByStationNumber(@RequestParam(value = "stationNumber") String stationNumber) {
		
		return fireStationService.getPersonDetailsFromFireStationNumber(stationNumber);
	}
	
	@GetMapping("/phoneAlert")
	public List<String> getPersonByPhoneAlert(@RequestParam String firestation) {

		return fireStationService.getPersonPhoneNumberFromFireStationNumber(firestation);
    }
	
	@GetMapping("/fire")
	public PersonInfoPlusAddressFromEachStationDTO getPersonByAddress(@RequestParam(value = "address") String address) {
		
		return fireStationService.getAddressFromEachStation(address);
	}
	
	@GetMapping("/flood/stations")
	public List<EveryHouseHoldInfoDTO> getPersonsByFireStationNumber(@RequestParam(value = "stations") String stations) {
		
		return fireStationService.getPersonByHouseHoldsInEachStationNumber(stations);
	}
}	
	
