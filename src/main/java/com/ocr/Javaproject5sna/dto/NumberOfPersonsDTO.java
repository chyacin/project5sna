package com.ocr.Javaproject5sna.dto;

public class NumberOfPersonsDTO {

	private int children;
	private int adults;

	public NumberOfPersonsDTO() {

	}

	public NumberOfPersonsDTO(int children, int adults) {
		this.children = children;
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}
}
