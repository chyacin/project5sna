package com.ocr.Javaproject5sna.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ocr.Javaproject5sna.model.FireStation;
import com.ocr.Javaproject5sna.repository.FireStationRepository;
import com.ocr.Javaproject5sna.service.FireStationService;


public class FireStationController {

	Logger logger = LoggerFactory.getLogger(FireStationController.class);
	
	@Autowired
	FireStationService fireStationService;
	
	FireStation fireStation;
	
	//fireStation endPoints
	@PostMapping("/firestation")
	public void createFSAddressMapping(@RequestParam String stationNumber, @RequestParam String address) {
		
		fireStationService.createFSAddressmapping(stationNumber, address);
	
	}
	
	@PutMapping("/firestation")
	public void updateAddressFSAddressMapping(@RequestBody String address, @RequestBody String stationNumber ) {
		
		fireStationService.updateAddressFSNumber(stationNumber, address);
	}
	
	@GetMapping("/firestation")
	public List<FireStation> getFireStation(){
		
		logger.info("GET /firestation called");
		
		return fireStationService.getAllFireStation();
	}
	
	@DeleteMapping("/firestation")
	public void deleteFSAddressMapping(@RequestParam String address, @RequestParam String stationNumber) {
		
		fireStationService.deleteFSAddressMapping(stationNumber, address);
	}
}
