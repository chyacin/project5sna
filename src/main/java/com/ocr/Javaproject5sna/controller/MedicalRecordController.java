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
import org.springframework.web.servlet.ModelAndView;

import com.ocr.Javaproject5sna.dto.MedicalRecordDTO;
import com.ocr.Javaproject5sna.model.MedicalRecord;
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
	public MedicalRecordDTO createMedicalRecord(@RequestBody MedicalRecord medicalRecord, BindingResult result) {

		if (!result.hasErrors()) {
			medicalRecordService.createMedicalRecord(medicalRecord);
			MedicalRecordDTO dto = new MedicalRecordDTO();
			dto.setCreated(true);

			return dto;

		} else {

			MedicalRecordDTO dto = new MedicalRecordDTO();
			dto.setCreated(false);
			dto.setErrors(result.hasFieldErrors());

			return dto;
		}
	}

	@RequestMapping(value = "/medicalRecord", method = RequestMethod.PUT)
	public boolean updateMedicalRecord(@RequestBody MedicalRecord medicalRecord, BindingResult result) {

		if (!result.hasErrors()) {
			medicalRecordService.updateMedicalRecord(medicalRecord);
			
			return true;
		}else {
          return false;
		}
	}

	@DeleteMapping("/medicalRecord")
	public void deleteMedicalRecord(@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName) {

		medicalRecordService.deleteMedicalRecord(firstName, lastName);
	}

}
