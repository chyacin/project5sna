package com.ocr.Javaproject5sna.serviceTest;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.repository.MedicalRecordRepository;
import com.ocr.Javaproject5sna.service.MedicalRecordService;

@RunWith(MockitoJUnitRunner.class)
public class MedicalRecordServiceTest {

	@InjectMocks
	MedicalRecordService medicalRecordService;

	@Mock
	MedicalRecordRepository medicalRecordRepository;

	@Test
	public void createMedicalRecord_returnCreatedMedicalRecord() {

		// Arange
		Set<String> medications = new HashSet<String>();
		medications.add("pharmacol:750");

		Set<String> allergies = new HashSet<String>();
		allergies.add("crab");

		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName("Jason");
		medicalRecord.setLastName("Kidd");
		medicalRecord.setBirthDate("03/07/98");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);
		// when(medicalRecordRepository.createMedicalRecord(medicalRecord)).thenReturn(medicalRecord);

		// Act
		MedicalRecord createdMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecord);

		// Assert
		assertEquals("Jason", createdMedicalRecord.getFirstName());
		assertEquals("Kidd", createdMedicalRecord.getLastName());
		assertEquals("03/07/98", createdMedicalRecord.getBirthDate());
		assertEquals(medications, createdMedicalRecord.getMedications());
		assertEquals(allergies, createdMedicalRecord.getAllergies());

	}

	@Test
	public void updateMedicalRecord_returnUpatedMedicalRecord() {

		// Arrange
		Set<String> medications = new HashSet<String>();
		medications.add("pharmacol:750");

		Set<String> allergies = new HashSet<String>();
		allergies.add("crab");

		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName("John");
		medicalRecord.setLastName("Boyd");
		medicalRecord.setBirthDate("03/07/98");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);
		
		Mockito.when(medicalRecordRepository.findMedicalRecord("John", "Boyd")).thenReturn(medicalRecord);

		// Act
		medicalRecordService.updateMedicalRecord(medicalRecord);

		Mockito.verify(medicalRecordRepository, times(1));
	}

	@Test
	public void deletePersonMedicalRecord_successfullyDeletedPersonMedicalRecord() {

		// Act
		medicalRecordService.deleteMedicalRecord("John", "Boyd");

		// Assert
		Mockito.verify(medicalRecordRepository, times(1));
	}

}
