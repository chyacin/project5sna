package com.ocr.Javaproject5sna.serviceTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
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
		
		//Arange
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
		when(medicalRecordRepository.createMedicalRecord(medicalRecord)).thenReturn(medicalRecord);
		
		//Act
		MedicalRecord createdMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecord);
		
		//Assert
		assertEquals("Jason", createdMedicalRecord.getFirstName());
		assertEquals("Kidd", createdMedicalRecord.getLastName());
		assertEquals("03/07/98", createdMedicalRecord.getBirthDate());
		assertEquals(medications, createdMedicalRecord.getMedications());
		assertEquals(allergies, createdMedicalRecord.getAllergies());
		
	}
	
	@Test
	public void getAllMedicalRecord_returnAllMedicalRecord() {
		
		List <MedicalRecord> medicalRecord = new ArrayList<>();
		
		//Arrange
		Set<String>medications1 = new HashSet<String>();
		medications1.add("pharmacol:500");
				
	    Set<String>allergies1 = new HashSet<String>();
		allergies1.add("fish");
		
		MedicalRecord medicalRecord1 = new MedicalRecord();
		medicalRecord1.setFirstName("John");
		medicalRecord1.setLastName("Sane");
		medicalRecord1.setBirthDate("03/03/98");
		medicalRecord1.setMedications(medications1);
		medicalRecord1.setAllergies(allergies1);
		
		
		//Arrange
		Set<String>medications2 = new HashSet<String>();
		medications2.add("pharmacol:1000");
				
	    Set<String>allergies2 = new HashSet<String>();
		allergies2.add("shellfish");
		
		MedicalRecord medicalRecord2 = new MedicalRecord();
		medicalRecord2.setFirstName("Joe");
		medicalRecord2.setLastName("Jones");
		medicalRecord2.setBirthDate("05/12/95");
		medicalRecord2.setMedications(medications2);
		medicalRecord2.setAllergies(allergies2);
		
		medicalRecord.add(medicalRecord1);
		medicalRecord.add(medicalRecord2);
		when(medicalRecordRepository.findAll()).thenReturn(medicalRecord);
		
		//Act
		List<MedicalRecord> findAllMedicalRecord = medicalRecordService.getAllMedicalRecord();
		
		assertEquals(2, findAllMedicalRecord.size());
		assertEquals(medications1, findAllMedicalRecord.get(0).getMedications());
		assertEquals(medications2, findAllMedicalRecord.get(1).getMedications());
		assertEquals("John", findAllMedicalRecord.get(0).getFirstName());
		assertEquals("Joe", findAllMedicalRecord.get(1).getFirstName());
	}

}
