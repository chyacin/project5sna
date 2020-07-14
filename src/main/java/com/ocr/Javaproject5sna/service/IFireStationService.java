package com.ocr.Javaproject5sna.service;

import java.util.List;


import com.ocr.Javaproject5sna.dto.EveryHouseHoldInfoDTO;
import com.ocr.Javaproject5sna.dto.PersonDetailsFromStationNumberDTO;
import com.ocr.Javaproject5sna.dto.PersonInfoPlusAddressFromEachStationDTO;
import com.ocr.Javaproject5sna.model.FireStation;

public interface IFireStationService {

	public FireStation createFSAddressmapping(String stationNumber, String address);
	public void updateAddressFSNumber(String stationNumber, String address);
	public void deleteFSAddressMapping(String stationNumber, String address);
	public PersonDetailsFromStationNumberDTO getPersonDetailsFromFireStationNumber(String stationNumber);
	public List<String> getPersonPhoneNumberFromFireStationNumber(String stationNumber);
	public PersonInfoPlusAddressFromEachStationDTO getAddressFromEachStation(String address);
	public List<EveryHouseHoldInfoDTO> getPersonByHouseHoldsInEachStationNumber(String stationNumber);
}
