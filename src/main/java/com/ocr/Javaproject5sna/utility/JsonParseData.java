package com.ocr.Javaproject5sna.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
			Any anyPerson = any.get("persons");
			anyPerson.forEach(p -> personRepository.createPerson(new Person(p.get("firstName").toString(),
					(p.get("lastName").toString()),
					(p.get("phone").toString()),
					(p.get("zip").toString()),
					(p.get("address").toString()),
					(p.get("city").toString()),
					(p.get("email").toString()))));
			
			//using any container, we can iterate over every firestation record from given data
	    	Any fireStationAny = any.get("firestations");
			fireStationAny.forEach(anyStation -> { 
				FireStation fireStation = fireStationRepository.findStation(anyStation.get("station").toString());

				if (fireStation == null) {
					fireStation = new FireStation(anyStation.get("station").toString());
					fireStationRepository.createStation(fireStation);
				}
				fireStation.addAddress(anyStation.get("address").toString());
				fireStationRepository.updateStation(fireStation);
			});
			
			//using any container, we can iterate over every medical record from given data
			Any medicalAny = any.get("medicalrecords");
			
			
			medicalAny.forEach(anyMedicalRecord -> {
	    		
		    	MedicalRecord medicalRecord = medicalRecordRepository.createMedicalRecord(new MedicalRecord(
		    			anyMedicalRecord.get("firstName").toString(),
		    			anyMedicalRecord.get("lastName").toString(),
		    			anyMedicalRecord.get("birthdate").toString()));
		    	
		    	Any medications = anyMedicalRecord.get("medications");
		    	if(medications.size() > 0)
		    			medications.forEach(a -> medicalRecord.addMedication(a.toString()));
		    			
		    	Any allergies = anyMedicalRecord.get("allergies");
		    	if(allergies.size() > 0)
		    			allergies.forEach(a -> medicalRecord.addAllergies(a.toString()));
		    			
		    	medicalRecordRepository.updateMedicalRecord(medicalRecord);
		    });
			System.out.println();
			personRepository.printAll();
			medicalRecordRepository.printAll();
			fireStationRepository.printAll();
			
		}	
		catch(IOException e) {
			//log error
		}
	}
}
