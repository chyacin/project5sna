package com.ocr.Javaproject5sna.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.ocr.Javaproject5sna.model.FireStation;

public interface IFireStationService {

	public FireStation createFSAddressmapping(String stationNumber, String address);
	public List<FireStation> getAllFireStation();
	public void updateAddressFSNumber(String stationNumber, String address);
	public void deleteFSAddressMapping(String stationNumber, String address);
	public JSONObject getPersonDetailsFromFireStationNumber(String stationNumber);
	public List<String> getPersonPhoneNumberFromWithInEachFireStation(String stationNumber);
	public JSONObject getAddressFromEachStation(String address);
	public List<JSONObject> getPersonByHouseHoldsInEachStationNumber(String stationNumber);
}
