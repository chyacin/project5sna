package com.ocr.Javaproject5sna.service;

import java.util.List;

import com.ocr.Javaproject5sna.model.MedicalRecord;

public interface IMedicalRecordService {
	
	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);
	public void updateMedicalRecord(MedicalRecord medicalRecord);
	public void deleteMedicalRecord(String firstName, String lastName);
}
