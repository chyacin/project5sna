package com.ocr.Javaproject5sna.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.ocr.Javaproject5sna.model.FireStation;
import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.repository.FireStationRepository;
import com.ocr.Javaproject5sna.repository.MedicalRecordRepository;
import com.ocr.Javaproject5sna.repository.PersonRepository;


@Component
public class JsonParseData {

	Logger log = LoggerFactory.getLogger(JsonParseData.class);
	
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	@Autowired
	private FireStationRepository fireStationRepository;
	
	//Using a listener, this method will be called when our application starts
	@EventListener
	public void onApplicationReadyEvent(ApplicationReadyEvent event) {
		
		try {
			//get json file, and convert it to byte array
			String filePath = "src/main/resources/data.json";
	    	byte[] byteArray = Files.readAllBytes(new File(filePath).toPath());
	    	
	    	//using JsonIterator, we are reading complete byte array and converting it into iterator object
			JsonIterator jsoniter = JsonIterator.parse(byteArray);
			
			// Any is a lazy container in Jsoniter that can hold different values
			Any any = jsoniter.readAny();

			//using any container, we can iterate over every person from given data
			processPersonData(any);
							
			//using any container, we can iterate over every firestation record from given data
			processFireStationData(any);
							
			//using any container, we can iterate over every medical record from given data
			processMedicalRecordData(any);
			
			//Associate MedicalRecord to Person
			associatePersonToMedicalRecord();
			
			//Associate Firestation to Person
			associatePersonToFireStation();
					
			System.out.println();

			personRepository.printAll();
			
			fireStationRepository.printAll();
			
            medicalRecordRepository.printAll();
			
		}	
		catch(IOException e) {
			//log error
		}
	}


	private void processMedicalRecordData(Any any) {
		Any medicalAny = any.get("medicalrecords");
		
		
		medicalAny.forEach(anyMedicalRecord -> {
			
			MedicalRecord medicalRecord = new MedicalRecord(
					anyMedicalRecord.get("firstName").toString(),
					anyMedicalRecord.get("lastName").toString(),
					anyMedicalRecord.get("birthdate").toString());
			
			Any medications = anyMedicalRecord.get("medications");
			if(medications.size() > 0)
					medications.forEach(a -> medicalRecord.addMedication(a.toString()));
					
			Any allergies = anyMedicalRecord.get("allergies");
			if(allergies.size() > 0)
					allergies.forEach(a -> medicalRecord.addAllergies(a.toString()));
			
			medicalRecordRepository.findAll().add(medicalRecord);});
			
	}

	private void processPersonData(Any any) {
		
		Any anyPerson = any.get("persons");
		
		anyPerson.forEach(p -> personRepository.createPerson(new Person(p.get("firstName").toString(),
				(p.get("lastName").toString()),
				(p.get("phone").toString()),
				(p.get("zip").toString()),
				(p.get("address").toString()),
				(p.get("city").toString()),
				(p.get("email").toString()))));
	
		
	}

	private void processFireStationData(Any any) {
		
	Map<String, FireStation> fireStationMap = new HashMap<>();
	
	Any fireStationAny = any.get("firestations");
    
         fireStationAny.forEach(anyStation -> {
         fireStationMap.compute(anyStation.get("station").toString(),
         (k, v) -> v == null ? new FireStation(anyStation.get("station").toString())
         .addAddress(anyStation.get("address").toString()) : v.addAddress(anyStation.get("address").toString()));});
    
    fireStationRepository.setFireStation(fireStationMap.values().stream().collect(Collectors.toList()));
    	
	}
	
	private void associatePersonToMedicalRecord() {
		for(Person person:personRepository.findAll()) {
			for(MedicalRecord medicalRecord:medicalRecordRepository.findAll()) {
				if(person.getName().equals(medicalRecord.getName())) {
					person.setMedicalRecord(medicalRecord);
				}
			}
		}
	}
	
	private void associatePersonToFireStation() {
		for(Person person: personRepository.findAll()) {
			for(FireStation fireStation: fireStationRepository.findAll()) {
				
				for(String address: fireStation.getAddresses()) {
					if(person.getAddress().equals(address)){
						fireStation.addPerson(person);	
						
				   }
				}
			}
		}
	}
}
