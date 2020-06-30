package com.ocr.Javaproject5sna.dto;

import java.util.ArrayList;

public class ChildAlertDTO {

	private ArrayList<PersonNamePlusAgeDTO> children;
	private ArrayList<PersonNamePlusAgeDTO> adults;

	public ChildAlertDTO() {
	}

	public ChildAlertDTO(ArrayList<PersonNamePlusAgeDTO> children, ArrayList<PersonNamePlusAgeDTO> adults) {
		this.children = children;
		this.adults = adults;
	}

	public ArrayList<PersonNamePlusAgeDTO> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<PersonNamePlusAgeDTO> children) {
		this.children = children;
	}

	public ArrayList<PersonNamePlusAgeDTO> getAdults() {
		return adults;
	}

	public void setAdults(ArrayList<PersonNamePlusAgeDTO> adults) {
		this.adults = adults;
	}
}
