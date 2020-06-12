package com.ocr.Javaproject5sna.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ocr.Javaproject5sna.model.FireStation;

@Repository
public class FireStationRepository {

	private  List<FireStation> fireStationList = new ArrayList<>();
 	
	public void setFireStation(List<FireStation> fireStationList) {
		this.fireStationList = fireStationList;
	}
    
    //get list of all firestations
    public List<FireStation> findAll() {
        return fireStationList;
    }

   //print list of all firestations
    public void printAll() {
    	for (FireStation fireStation : fireStationList) {
    		System.out.println(fireStation.toString());
    	}
    }

    //find firestation by station number
    public FireStation findStation(String stationNumber) {
        for (FireStation fireStation : fireStationList) {
            if (fireStation.getStationNumber().equals(stationNumber)) {
                return fireStation;
                
            }
        }
        return null;
    }
    
    

//    //add a new firestation
    public FireStation createStation(FireStation fireStation) {
        if(!alreadyExist(fireStation)) {       	
         fireStationList.add(fireStation);  
         return fireStation;
        }
        else {
        FireStation existingFireStation = findStation(fireStation.getStationNumber());
        existingFireStation.getAddresses().addAll(fireStation.getAddresses());
        updateStation(existingFireStation);
        }
    	return null;
    }
    
    public void addFireStation(FireStation fireStation) {
    	fireStationList.add(fireStation);
    }


    //update an existing firestation
    public void updateStation(FireStation fireStation) {
    	for (FireStation station : fireStationList) {
            if (fireStation.getStationNumber().equals(station.getStationNumber())) {
                
            	fireStationList.remove(station);
                fireStationList.add(fireStation);
                break;
            }
            
        }
        
    }

    //delete an existing firestation
    public void deleteStation(String stationNumber) {
        FireStation deleteStation = findStation(stationNumber);
        fireStationList.remove(deleteStation);
    }
    
    private boolean alreadyExist(FireStation fireStation) {
    	return fireStationList.stream().filter(e -> e.getStationNumber().equalsIgnoreCase(fireStation.getStationNumber())).count() >= 1;
    }

}
