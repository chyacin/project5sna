package com.ocr.Javaproject5sna.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ocr.Javaproject5sna.model.FireStation;

@Repository
public class FireStationRepository {

	private List<FireStation> fireStationList;

    @Autowired
    public FireStationRepository() {
        this.fireStationList = new ArrayList<>();
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

    //add a new firestation
    public FireStation createStation(FireStation fireStation) {
        fireStationList.add(fireStation);
        return fireStation;
    }

    //update an existing firestation
    public void updateStation(FireStation fireStation) {
        FireStation updateStation = findStation(fireStation.getStationNumber());
        updateStation.setAddresses(fireStation.getAddresses());
    }

    //delete an existing firestation
    public void deleteStation(String address) {
        FireStation deleteStation = findStation(address);
        fireStationList.remove(deleteStation);
    }
}
