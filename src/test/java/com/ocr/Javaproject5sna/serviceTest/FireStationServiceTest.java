package com.ocr.Javaproject5sna.serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.ocr.Javaproject5sna.model.FireStation;
import com.ocr.Javaproject5sna.repository.FireStationRepository;
import com.ocr.Javaproject5sna.service.FireStationService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class FireStationServiceTest {

    @InjectMocks
    FireStationService fireStationService;
	
	
	@Mock
	FireStationRepository fireStationRepository;
	
	
	@Test
	public void createFSAddressMapping_returnFSAddressMapping() {
		
		//Arrange
		FireStation fireStation = new FireStation();
		fireStation.setAddress("188 Culver St");
		fireStation.setStationNumber("2");
		
		//Act
		FireStation createdFireStation = fireStationService.createFSAddressmapping("2", "188 Culver St");
		 
		 //Assert
		assertTrue(createdFireStation.equals(fireStation));
	}
	
	@Test
	public void updateFSAddressMapping_returnFSAddressMapping() {
		
		//Arrange
		FireStation newFireStation = new FireStation();
		newFireStation.setAddress("175 Culver St");
		newFireStation.setStationNumber("3");
		
		//Act
	    
		fireStationService.updateAddressFSNumber("2", "175 Culver St");
	
		//Assert
		
	}
		
	
	
	@Test
	public void deleteFSAddressMapping_successfullyDeletedFSAddressMapping() {
		
		//Act
		fireStationService.deleteFSAddressMapping("2", "175 Culver St");
		
		//Assert
		Mockito.verify(fireStationService, times(1));
	}
}
