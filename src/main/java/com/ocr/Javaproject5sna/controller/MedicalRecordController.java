package com.ocr.Javaproject5sna.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.repository.MedicalRecordRepository;
import com.ocr.Javaproject5sna.service.MedicalRecordService;

@RestController
public class MedicalRecordController {
	
	Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);
	
	@Autowired
	MedicalRecordService medicalRecordService;
	
	@Autowired
	MedicalRecordRepository medicalRecordRepository;
	
	MedicalRecord medicalRecord;
	
	@Autowired
	public MedicalRecordController(MedicalRecordService medicalRecordService) {
		this.medicalRecordService = medicalRecordService;
	}
	
	@PostMapping("/medicalRecord")
	public void createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		
		medicalRecordService.createMedicalRecord(medicalRecord);
	}
	
	@PutMapping("/medicalRecord")
	public void updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		
		medicalRecordRepository.updateMedicalRecord(medicalRecord);	
	}
	
	@GetMapping("/medicalRecords")
	public List<MedicalRecord> getMedicalRecord(){
		
		logger.info("GET /medicalRecord called");
		
		return medicalRecordService.getAllMedicalRecord();
	}
	
	@DeleteMapping("/medicalRecord")
	public void deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName) {
		
		medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
	}

}
