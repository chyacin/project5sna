package com.ocr.Javaproject5sna.dto;

import java.util.ArrayList;
import java.util.List;

public class PersonDetailsFromStationNumberDTO {

	private ArrayList<NumberOfPersonsDTO> numberOfKidsPlusAdults;
	private ArrayList<PersonDetailsDTO> personsDetails;

	public PersonDetailsFromStationNumberDTO() {

	}

	public PersonDetailsFromStationNumberDTO(ArrayList<NumberOfPersonsDTO> numberOfKidsPlusAdults,
			ArrayList<PersonDetailsDTO> personsDetails) {
         this.numberOfKidsPlusAdults = numberOfKidsPlusAdults;
         this.personsDetails = personsDetails;
	}
	
	public ArrayList<NumberOfPersonsDTO> getNumberOfKidsPlusAdult(){
		return numberOfKidsPlusAdults;
	}
	
	public void setNumberOfKidsPlusAdult(ArrayList<NumberOfPersonsDTO> numberOfKidsPlusAdults) {
		this.numberOfKidsPlusAdults = numberOfKidsPlusAdults;
	}
	
	public ArrayList<PersonDetailsDTO> getPersonsDetails(){
		return personsDetails;
	}
	
	public void setPersonsDetails(ArrayList<PersonDetailsDTO> personsDetails) {
		this.personsDetails = personsDetails;
	}
}
