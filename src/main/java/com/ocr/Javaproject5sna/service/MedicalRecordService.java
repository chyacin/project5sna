package com.ocr.Javaproject5sna.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService implements IMedicalRecordService {

	Logger logger = LoggerFactory.getLogger(MedicalRecordService.class);

	MedicalRecordRepository medicalRecordRepository;

	@Autowired
	public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
		this.medicalRecordRepository = medicalRecordRepository;
	}

	// add a new medical record
	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordRepository.findAll().add(medicalRecord);
		return medicalRecord;
	}

	// update an existing medical record.
	public void updateMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord updateMedical = medicalRecordRepository.findMedicalRecord(medicalRecord.getFirstName(),
				medicalRecord.getLastName());
		updateMedical.setBirthDate(medicalRecord.getBirthDate());
		updateMedical.setMedications(medicalRecord.getMedications());
		updateMedical.setAllergies(medicalRecord.getAllergies());
	}

	public void deleteMedicalRecord(String firstName, String lastName) {
		MedicalRecord deleteMedical = medicalRecordRepository.findMedicalRecord(firstName, lastName);

		medicalRecordRepository.findAll().remove(deleteMedical);
	}

	   public MedicalRecord findMedicalRecord(String firstName, String lastName) {
	        for (MedicalRecord medicalRecord : medicalRecordRepository.findAll()) {
	            if (medicalRecord.getFirstName().equals(firstName)
	                && medicalRecord.getLastName().equals(lastName)) {
	                return medicalRecord;
	            }
	        }
	        return null;
	    }

}
