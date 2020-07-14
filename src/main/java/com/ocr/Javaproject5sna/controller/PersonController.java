package com.ocr.Javaproject5sna.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ocr.Javaproject5sna.dto.ChildAlertDTO;
import com.ocr.Javaproject5sna.dto.PersonMedicalInfoDTO;
import com.ocr.Javaproject5sna.dto.ResponseDTO;
import com.ocr.Javaproject5sna.model.Person;
import com.ocr.Javaproject5sna.modelClass.NamesModelClass;
import com.ocr.Javaproject5sna.repository.PersonRepository;
import com.ocr.Javaproject5sna.service.PersonService;


@EnableWebMvc
@RestController
public class PersonController {

	Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonService personService;

	@Autowired
	PersonRepository personRepository;

	Person person;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public ResponseDTO createPerson(@Valid @RequestBody Person person, BindingResult result,
			HttpServletResponse response) {

		if (!result.hasErrors()) {
			personService.createPerson(person);
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

	@RequestMapping(value = "/person", method = RequestMethod.PUT)
	public ResponseDTO updatePerson(@Valid @RequestBody Person person, BindingResult result,
			HttpServletResponse response) {

		if (!result.hasErrors()) {
			personService.updatePerson(person);
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

	@RequestMapping(value = "/person", method = RequestMethod.DELETE)
	public ResponseDTO deletePerson(@Valid @ModelAttribute NamesModelClass names, BindingResult result,
			HttpServletResponse response) {

		if (!result.hasErrors()) {
			// personService.findPerson(names.getFirstName(), names.getLastName());
			personService.deletePerson(names.getFirstName(), names.getLastName());

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

	// Person Url

	@RequestMapping(value = "/childAlert", method = RequestMethod.GET)
	public ChildAlertDTO getChildrenAlert(@Valid @ModelAttribute("address") String address, BindingResult result,
			HttpServletResponse response) {

		ChildAlertDTO dto = null;
		if (!result.hasErrors()) {
			dto = personService.getChildrenFromEachAddress(address);

			response.setStatus(HttpServletResponse.SC_OK);

			return dto;
		} else {

			if (result.hasErrors()) {
			}
			dto = new ChildAlertDTO();

			ArrayList<String> errorList = new ArrayList<>();

			result.getAllErrors().forEach(error -> {
				errorList.add(error.toString());
			});

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return dto;
		}

	}

	@RequestMapping(value = "/personInfo", method = RequestMethod.GET)
	public List<PersonMedicalInfoDTO> getPersonByInfo(@Valid @ModelAttribute NamesModelClass names,
			BindingResult result, HttpServletResponse response) {

		List<PersonMedicalInfoDTO> dto = null;

		if (!result.hasErrors()) {
			dto = personService.getPersonInfo(names.getFirstName(), names.getLastName());

			response.setStatus(HttpServletResponse.SC_OK);

		} else if (result.hasErrors()) {
			ArrayList<String> errorList = new ArrayList<>();

			result.getAllErrors().forEach(error -> {
				errorList.add(error.toString());
			});

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return dto;
		}
		return dto;

	}

	@RequestMapping(value = "/communityEmail", method = RequestMethod.GET)
	public List<String> getCommunityEmail(@Valid @ModelAttribute("city") String city, BindingResult result,
			HttpServletResponse response) {

		List<String> endResult = null;
		if (!result.hasErrors()) {
			endResult = personService.getPersonsEmailAddress(city);

			response.setStatus(HttpServletResponse.SC_OK);
		}
		return endResult;

	}
}
