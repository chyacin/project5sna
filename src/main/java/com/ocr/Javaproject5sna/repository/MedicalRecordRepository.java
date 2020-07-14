package com.ocr.Javaproject5sna.repository;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ocr.Javaproject5sna.model.MedicalRecord;

@Repository
public class MedicalRecordRepository {

	private static List<MedicalRecord> medicalRecordList = new ArrayList<>();

	
	
	public void setMedicalRecord(List<MedicalRecord> medicalRecordList) {
		MedicalRecordRepository.medicalRecordList = medicalRecordList;
	}

    // get list of all medical records
    public List<MedicalRecord> findAll() {
        return medicalRecordList;
    }

    // print list of all medical records
    public void printAll() {
    	for (MedicalRecord medicalRecord : medicalRecordList) {
    		System.out.println(medicalRecord.toString());
    	}
    }

    //find medical record by person's name
    public MedicalRecord findMedicalRecord(String firstName, String lastName) {
        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getFirstName().equals(firstName)
                && medicalRecord.getLastName().equals(lastName)) {
                return medicalRecord;
            }
        }
        return null;
    }

    //add a new medical record
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordList.add(medicalRecord);
        return medicalRecord;
    }

    //update an existing medical record.
    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        MedicalRecord updateMedical = findMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName());
        updateMedical.setBirthDate(medicalRecord.getBirthDate());
        updateMedical.setMedications(medicalRecord.getMedications());
        updateMedical.setAllergies(medicalRecord.getAllergies());
    }

    //delete an existing medical record
    public void deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecord deleteMedical = findMedicalRecord(firstName, lastName);
        medicalRecordList.remove(deleteMedical);
    }
    
    
}
