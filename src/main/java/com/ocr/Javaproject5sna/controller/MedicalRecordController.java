package com.ocr.Javaproject5sna.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.service.MedicalRecordService;

public class MedicalRecordController {
	
	Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);
	
	MedicalRecordService medicalRecordService;
	
	MedicalRecord medicalRecord;
	
	@PostMapping("/medicalRecord")
	public void createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		
		medicalRecordService.createMedicalRecord(medicalRecord);
	}
	
	@PutMapping("/medicalRecord")
	public void updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		
		medicalRecordService.updateMedicalRecord(medicalRecord);	
	}
	
	@GetMapping("/medicalRecord")
	public List<MedicalRecord> getMedicalRecord(){
		
		logger.info("GET /medicalRecord called");
		
		return medicalRecordService.getAllMedicalRecord();
	}
	
	@DeleteMapping("/medicalRecord")
	public void deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
		
		medicalRecordService.deleteMedicalRecord(firstName, lastName);
	}

}
