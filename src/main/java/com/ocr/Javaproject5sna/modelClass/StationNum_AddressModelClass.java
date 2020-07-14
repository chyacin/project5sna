package com.ocr.Javaproject5sna.modelClass;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StationNum_AddressModelClass {

	public StationNum_AddressModelClass() {

	}

	//@NotNull
	//private String address;
	@NotNull
	private Set<String> addresses = new HashSet<>();
	@NotBlank
	private String stationNumber;

	public StationNum_AddressModelClass(String stationNumber, String address) {
		this.stationNumber = stationNumber;
		this.addresses.add(address);
	}

	public String getStationNumber() {
		return this.stationNumber;
	}

	public void setStationNumber(String stationNumber) {
		this.stationNumber = stationNumber;
	}

	public StationNum_AddressModelClass addAddress(String address) {
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
