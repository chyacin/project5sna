package com.ocr.Javaproject5sna.repository;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ocr.Javaproject5sna.model.MedicalRecord;

@Repository
public class MedicalRecordRepository {

	private List<MedicalRecord> medicalRecordList;

    @Autowired
    public MedicalRecordRepository() {
        this.medicalRecordList = new ArrayList<>();
    }

    // get list of all medical records
    public List<MedicalRecord> findAll() {
        return medicalRecordList;
    }

    // print list of all medical records
    public void printAll() {
    	for (MedicalRecord medical : medicalRecordList) {
    		System.out.println(medical.toString());
    	}
    }

    //find medical record by person's name
    public MedicalRecord findMedicalRecord(String firstName, String lastName) {
        for (MedicalRecord medical : medicalRecordList) {
            if (medical.getFirstName().equals(firstName)
                && medical.getLastName().equals(lastName)) {
                return medical;
            }
        }
        return null;
    }

    //add a new medical record
    public MedicalRecord createMedicalRecord(MedicalRecord medical) {
        medicalRecordList.add(medical);
        return medical;
    }

    //update an existing medical record
    public void updateMedicalRecord(MedicalRecord medical) {
        MedicalRecord updateMedical = findMedicalRecord(medical.getFirstName(), medical.getLastName());
        updateMedical.setBirthDate(medical.getBirthDate());
        updateMedical.setMedications(medical.getMedications());
        updateMedical.setAllergies(medical.getAllergies());
    }

    //delete an existing medical record
    public void deleteMedicalRecord(String firstName, String lastName) {
        MedicalRecord deleteMedical = findMedicalRecord(firstName, lastName);
        medicalRecordList.remove(deleteMedical);
    }
}
