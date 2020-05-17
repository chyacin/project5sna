package com.ocr.Javaproject5sna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
	
	MedicalRecordRepository medicalRecordRepository;
	
	
	
    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
    	this.medicalRecordRepository = medicalRecordRepository;
    }

	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
		
	 return  medicalRecordRepository.createMedicalRecord(medicalRecord);
		  	  
	}
	
	public List<MedicalRecord> getAllMedicalRecord(){
		
		return medicalRecordRepository.findAll();
	}
	
	

}
