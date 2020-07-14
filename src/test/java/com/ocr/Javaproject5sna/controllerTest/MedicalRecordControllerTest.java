package com.ocr.Javaproject5sna.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocr.Javaproject5sna.dto.ResponseDTO;
import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.service.MedicalRecordService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MedicalRecordControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webContext;

	@MockBean
	MedicalRecordService medicalRecordService;
	
	@Autowired
	ObjectMapper objectMapper;

	@Before
	public void setupMockmvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

//	String json = ("{\"firstName\": \"Joe\", \"lastName\": \"Bing\", \"birthdate\": \"09/09/1989\",\"medications\":[\"thradox:700mg\"], \"allergies\": [\"peanut\"]}");

	@Test
	public void testCreateMedicalRecord() throws Exception {

		Set<String> medications = new HashSet<String>();
		medications.add("thradox:700mg");

		Set<String> allergies = new HashSet<String>();
		allergies.add("peanut");

		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName("Joe");
		medicalRecord.setLastName("Bing");
		medicalRecord.setBirthDate("09/09/1989");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		when(medicalRecordService.createMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecord);

		String json = objectMapper.writeValueAsString(medicalRecord);

		MvcResult mvcResult = mockMvc
				.perform(post("/medicalRecord").contentType(MediaType.APPLICATION_JSON).content(json)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isCreated()).andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.isSuccessful());
		Assert.assertFalse(result.getErrors());
	}

	@Test
	public void testCreateMedicalRecordWithOutName() throws Exception {

		Set<String> medications = new HashSet<String>();
		medications.add("thradox:700mg");

		Set<String> allergies = new HashSet<String>();
		allergies.add("peanut");

		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName("");
		medicalRecord.setLastName("");
		medicalRecord.setBirthDate("09/09/1989");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		when(medicalRecordService.createMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecord);

		String json = objectMapper.writeValueAsString(medicalRecord);

		MvcResult mvcResult = mockMvc
				.perform(post("/medicalRecord").contentType(MediaType.APPLICATION_JSON).content(json)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isBadRequest()).andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.getErrors());

	}

	@Test
	public void testUpdateMedicalRecord() throws Exception {

		Set<String> medications = new HashSet<String>();
		medications.add("thradox:700mg");

		Set<String> allergies = new HashSet<String>();
		allergies.add("peanut");

		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName("Joe");
		medicalRecord.setLastName("Bing");
		medicalRecord.setBirthDate("09/09/1989");
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);

		when(medicalRecordService.findMedicalRecord("Joe", "Bing")).thenReturn(medicalRecord);

		String json = objectMapper.writeValueAsString(medicalRecord);

		MvcResult mvcResult = mockMvc
				.perform(put("/medicalRecord").contentType(MediaType.APPLICATION_JSON).content(json)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isOk()).andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.isSuccessful());
		Assert.assertFalse(result.getErrors());
	}
	

	@Test
	public void testDeleteMedicalRecord() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(delete("/medicalRecord")
				.param("firstName", "Joe")
				.param("lastName", "Bing")
				.param("birthdate", "09/09/1989")
				.param("medications", "thradox:700mg")
				.param("allergies","peanut")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(content().contentType("application/json")).andExpect(status().isOk()).andReturn();
		
		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);
		
		Assert.assertTrue(result.isSuccessful());
		Assert.assertFalse(result.getErrors());
		
	}
	
}
