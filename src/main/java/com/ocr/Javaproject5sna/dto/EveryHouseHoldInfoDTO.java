package com.ocr.Javaproject5sna.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EveryHouseHoldInfoDTO {

	private Set<String> addresses = new HashSet<>();
	private ArrayList<PersonInEachAddressDTO> everybodyInfo;

	public EveryHouseHoldInfoDTO() {

	}

	public EveryHouseHoldInfoDTO(String address, ArrayList<PersonInEachAddressDTO> everybodyInfo ) {
		this.addresses.add(address);
        this.everybodyInfo = everybodyInfo;

		
	}

	public ArrayList<PersonInEachAddressDTO> getEverybodyInfo() {
		return everybodyInfo;
	}
	
	public void setEverybodyInfo(ArrayList<PersonInEachAddressDTO> everybodyInfo) {
		this.everybodyInfo = everybodyInfo;
	}
	
	public EveryHouseHoldInfoDTO addAddress (String address) {
		addresses.add(address);
		return this;
	}
	
	public String getAddress() {
		return this.getAddresses().toString();
	}

	public Set<String> getAddresses() {
		return addresses.stream().collect(Collectors.toSet());
	}

	public void setAddresses(Set<String> addresses) {
		this.addresses = addresses;
	}

	
}
//	
//	public String getAddress() {
//		return address;
//	}
//	
//	public void setAddress(String address) {
//		this.address = address;
//	}
//}
