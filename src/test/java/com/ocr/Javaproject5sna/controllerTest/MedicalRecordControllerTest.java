package com.ocr.Javaproject5sna.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.integration.test.util.TestUtils;

import org.json.simple.JSONObject;
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
import com.ocr.Javaproject5sna.dto.MedicalRecordDTO;
import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.service.MedicalRecordService;

import net.minidev.json.parser.JSONParser;

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

		String Json = objectMapper.writeValueAsString(medicalRecord);

		MvcResult result = mockMvc
				.perform(post("/medicalRecord").contentType(MediaType.APPLICATION_JSON).content(Json)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isOk()).andReturn();

	MedicalRecordDTO createMedicalRecord = new MedicalRecordDTO();
	
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

		String Json = objectMapper.writeValueAsString(medicalRecord);

		MvcResult result = mockMvc
				.perform(put("/medicalRecord").contentType(MediaType.APPLICATION_JSON_VALUE).content(Json)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isOk()).andReturn();

		result.getResponse().getContentAsString();
	}

}

//mockMvc.perform(post("/api/account")
//.contentType(MediaType.APPLICATION_JSON)
//.content("{ "accountType": "SAVINGS", "balance": 5000.0 }") 
//.accept(MediaType.APPLICATION_JSON))
//.andExpect(status().isCreated())
//.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//.andExpect(header().string("Location", "/api/account/12345"))
//.andExpect(jsonPath("$.accountId").value("12345")) 
//.andExpect(jsonPath("$.accountType").value("SAVINGS"))
//.andExpect(jsonPath("$.balance").value(5000)); 
//}

//.andExpect(jsonPath("$.firstName").value("Joe")) 
//.andExpect(jsonPath("$.lastName").value("Bing"))
//.andExpect(jsonPath("$.birthdate").value("09/09/1989"))
//.andExpect(jsonPath("$.medications").value("thradox:700mg"))
//.andExpect(jsonPath("$.allergies").value("peanut"))
