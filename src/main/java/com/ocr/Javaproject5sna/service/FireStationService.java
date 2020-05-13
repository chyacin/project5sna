package com.ocr.Javaproject5sna.service;


import java.util.List;

import com.ocr.Javaproject5sna.model.FireStation;
import com.ocr.Javaproject5sna.repository.FireStationRepository;

public class FireStationService {
	
	FireStationRepository fireStationRepository;

	FireStation fireStation;
	
	public void createFSAddressmapping(String stationNumber, String address) {
		
		fireStationRepository.createStation(fireStation);		
	}
	
	public void getStationFromStationAddress(String address) {
		
		 fireStationRepository.findStation(address);
	}
	
	public List<FireStation> getAllFireStation(){
		
		return fireStationRepository.findAll();
	}
	
	public void updateAddressFSNumber(String stationNumber, String address) {
		
		fireStationRepository.updateStation(fireStation);
	}
	
	public void deleteFSAddressMapping(String stationNumber, String address) {
		
		fireStationRepository.deleteStation(address);
	}

}
