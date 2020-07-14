package com.ocr.Javaproject5sna.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ocr.Javaproject5sna.dto.EveryHouseHoldInfoDTO;
import com.ocr.Javaproject5sna.dto.PersonDetailsFromStationNumberDTO;
import com.ocr.Javaproject5sna.dto.PersonInfoPlusAddressFromEachStationDTO;
import com.ocr.Javaproject5sna.dto.ResponseDTO;
import com.ocr.Javaproject5sna.model.FireStation;
import com.ocr.Javaproject5sna.modelClass.StationNum_AddressModelClass;
import com.ocr.Javaproject5sna.service.FireStationService;

@RestController
public class FireStationController {

	Logger logger = LoggerFactory.getLogger(FireStationController.class);

	FireStationService fireStationService;

	@Autowired
	public FireStationController(FireStationService fireStationService) {
		this.fireStationService = fireStationService;
	}

//	@RequestMapping(value = "/firestation", method = RequestMethod.POST,
//			produces = "application/json")
//	public ResponseEntity <String> createFSAddressMapping(@RequestParam("stationNumber") String stationNumber,
//			@RequestParam("address") String address, HttpHeaders httpHeaders, HttpServletResponse response) { 
//		
//		 httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		 
//	 return new ResponseEntity ("{\"station\":\"3\",\"address\":\"15 Soft St\"}", httpHeaders, response.SC_CREATED);

//	}

	@RequestMapping(value = "/firestation", method = RequestMethod.POST)
	public ResponseDTO createFSAddressMapping(
			@Valid @ModelAttribute("fireStationCreated") StationNum_AddressModelClass modelClass, BindingResult result,
			HttpServletResponse response) {

		if (!result.hasErrors()) {
			fireStationService.createFSAddressmapping(modelClass.getStationNumber(), modelClass.getAddress());
			ResponseDTO dto = new ResponseDTO();
			dto.setSuccessful(true);

			response.setStatus(HttpServletResponse.SC_CREATED);
			return dto;

		} else {

			ResponseDTO dto = new ResponseDTO();
			dto.setSuccessful(true);
			dto.setErrors(result.hasErrors());

			ArrayList<String> errorList = new ArrayList<>();

			result.getAllErrors().forEach(error -> {
				errorList.add(error.toString());
			});

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			return dto;
		}

	}

	@RequestMapping(value = "/firestation", method = RequestMethod.PUT)
	public ResponseDTO updateFSAddressMapping(
			@Valid @ModelAttribute("fireStationUpdated") StationNum_AddressModelClass modelClass, BindingResult result,
			HttpServletResponse response) {

		if (!result.hasErrors()) {
			fireStationService.updateAddressFSNumber(modelClass.getAddress(), modelClass.getStationNumber());
			ResponseDTO dto = new ResponseDTO();
			dto.setSuccessful(true);

			response.setStatus(HttpServletResponse.SC_OK);
			return dto;

		} else {

			ResponseDTO dto = new ResponseDTO();
			dto.setSuccessful(true);
			dto.setErrors(result.hasErrors());

			ArrayList<String> errorList = new ArrayList<>();

			result.getAllErrors().forEach(error -> {
				errorList.add(error.toString());
			});

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			return dto;
		}

	}

	@RequestMapping(value = "/firestation", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseDTO deleteFSAddressMapping(@Valid @ModelAttribute StationNum_AddressModelClass modelClass,
			BindingResult result, HttpServletResponse response) {

		if (!result.hasErrors()) {
			fireStationService.deleteFSAddressMapping(modelClass.getStationNumber(), modelClass.getAddress());
			ResponseDTO dto = new ResponseDTO();
			dto.setSuccessful(true);

			response.setStatus(HttpServletResponse.SC_OK);
			return dto;

		} else {

			ResponseDTO dto = new ResponseDTO();
			dto.setSuccessful(true);
			dto.setErrors(result.hasErrors());

			ArrayList<String> errorList = new ArrayList<>();

			result.getAllErrors().forEach(error -> {
				errorList.add(error.toString());
			});

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			return dto;
		}

	}

	// firestation Url

	@RequestMapping(value = "/firestation", method = RequestMethod.GET)
	public PersonDetailsFromStationNumberDTO getPersonByStationNumber(
			@Valid @ModelAttribute("stationNumber") String stationNumber, BindingResult result,
			HttpServletResponse response) {

		PersonDetailsFromStationNumberDTO dto = null;
		if (!result.hasErrors()) {
			dto = fireStationService.getPersonDetailsFromFireStationNumber(stationNumber);

			response.setStatus(HttpServletResponse.SC_OK);
		}
		return dto;
	}

	@RequestMapping(value = "/phoneAlert", method = RequestMethod.GET)
	public List<String> getPersonByPhoneAlert(@Valid @ModelAttribute("stationNumber") String stationNumber,
			BindingResult result, HttpServletResponse response) {
		List<String> endResult = null;

		if (!result.hasErrors()) {
			endResult = fireStationService.getPersonPhoneNumberFromFireStationNumber(stationNumber);

			response.setStatus(HttpServletResponse.SC_OK);
		}
		return endResult;
	}

	@RequestMapping(value = "/fire", method = RequestMethod.GET)
	public PersonInfoPlusAddressFromEachStationDTO getPersonByAddress(@Valid @ModelAttribute("address") String address,
			BindingResult result, HttpServletResponse response) {

		PersonInfoPlusAddressFromEachStationDTO dto = null;

		if (!result.hasErrors()) {

			dto = fireStationService.getAddressFromEachStation(address);
			response.setStatus(HttpServletResponse.SC_OK);
		}
		return dto;
	}

	@RequestMapping(value = "/flood/stations", method = RequestMethod.GET)
	public List<EveryHouseHoldInfoDTO> getPersonsByFireStationNumber(@Valid @ModelAttribute("stationNumber") String stationNumber,
			BindingResult result, HttpServletResponse response) {

		List<EveryHouseHoldInfoDTO> dto = null;

		if (!result.hasErrors()) {

			dto = fireStationService.getPersonByHouseHoldsInEachStationNumber(stationNumber);
			response.setStatus(HttpServletResponse.SC_OK);

		}
		return dto;
	}
}
