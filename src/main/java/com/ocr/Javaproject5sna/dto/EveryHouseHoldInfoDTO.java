package com.ocr.Javaproject5sna.dto;

import java.util.ArrayList;

public class EveryHouseHoldInfoDTO {

	private String address;
	private ArrayList<PersonInEachAddressDTO> everybodyInfo;

	public EveryHouseHoldInfoDTO() {

	}

	public EveryHouseHoldInfoDTO(String address, ArrayList<PersonInEachAddressDTO> everybodyInfo ) {
        this.address = address;
        this.everybodyInfo = everybodyInfo;
		
	}

	public ArrayList<PersonInEachAddressDTO> getEverybodyInfo() {
		return everybodyInfo;
	}
	
	public void setEverybodyInfo(ArrayList<PersonInEachAddressDTO> everybodyInfo) {
		this.everybodyInfo = everybodyInfo;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}
