package com.ocr.Javaproject5sna.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.ocr.Javaproject5sna.dto.ResponseDTO;
import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.modelClass.NamesModelClass;
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

	@RequestMapping(value = "/medicalRecord", method = RequestMethod.POST)
	public ResponseDTO createMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord, BindingResult result,
			HttpServletResponse response) {

		if (!result.hasErrors()) {
			medicalRecordService.createMedicalRecord(medicalRecord);
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

	@RequestMapping(value = "/medicalRecord", method = RequestMethod.PUT)
	public ResponseDTO updateMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord, BindingResult result,
			HttpServletResponse response) {

		if (!result.hasErrors()) {
			medicalRecordService.updateMedicalRecord(medicalRecord);
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

			response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);

			return dto;
		}

	}

	@RequestMapping(value = "/medicalRecord", method = RequestMethod.DELETE)
	public ResponseDTO deleteMedicalRecord(@Valid @ModelAttribute NamesModelClass names, BindingResult result,
			 HttpServletResponse response) {

		if (!result.hasErrors()) {
		//	medicalRecordService.findMedicalRecord(names.getFirstName(), names.getLastName());
			medicalRecordService.deleteMedicalRecord(names.getFirstName(), names.getLastName());

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
}
