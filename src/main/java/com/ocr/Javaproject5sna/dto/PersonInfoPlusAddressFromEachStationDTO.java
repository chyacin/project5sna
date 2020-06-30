package com.ocr.Javaproject5sna.dto;

import java.util.ArrayList;

public class PersonInfoPlusAddressFromEachStationDTO {

	private String stationNumber;
	private ArrayList<PersonInEachAddressDTO> personsInAddress;

	public PersonInfoPlusAddressFromEachStationDTO() {

	}

	public PersonInfoPlusAddressFromEachStationDTO(ArrayList<PersonInEachAddressDTO> personsInAddress,
			String stationNumber) {
		this.personsInAddress = personsInAddress;
		this.stationNumber = stationNumber;

	}
	public ArrayList<PersonInEachAddressDTO> getPersonsInAddress() {
		return personsInAddress;
	}
	
	public void setPersonsInAddress(ArrayList<PersonInEachAddressDTO> personsInAddress) {
		this.personsInAddress = personsInAddress;
	}
	
	public String getStationNumber() {
		return stationNumber;
	}
	
	public void setStationNumber(String stationNumber) {
		this.stationNumber = stationNumber;
	}
}
