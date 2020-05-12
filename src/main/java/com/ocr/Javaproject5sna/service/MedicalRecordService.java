package com.ocr.Javaproject5sna.service;

import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.repository.MedicalRecordRepository;

public class MedicalRecordService {
	
	MedicalRecordRepository medicalRecordRepository;
	
	MedicalRecord medicalRecord;

	public void createMedicalRecord(MedicalRecord medicalRecord) {
		
		medicalRecordRepository.createMedicalRecord(medicalRecord);
	}
	
	public void getMedicalRecordByName(String firstName, String lastName) {
		
		medicalRecordRepository.findMedicalRecord(firstName, lastName);
	}
	
	public void updateMedicalRecord(MedicalRecord medicalRecord) {
		
		medicalRecordRepository.updateMedicalRecord(medicalRecord);
	}
	
	public void deleteMedicalRecord(String firstName, String lastName) {
		
		medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
	}

}
