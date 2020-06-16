package com.ocr.Javaproject5sna.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ocr.Javaproject5sna.controller.MedicalRecordController;
import com.ocr.Javaproject5sna.model.MedicalRecord;
import com.ocr.Javaproject5sna.service.MedicalRecordService;


@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest(controllers = {MedicalRecordController.class, MedicalRecordService.class})
public class MedicalRecordControllerTest {

	@Autowired
	private MockMvc mockMvc;
		
	@Autowired
	private WebApplicationContext webContext;
	
	@MockBean 
	private MedicalRecordController medicalRecordController;
	
	@Before
	public void setupMockmvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();	
	}
	
	@Test
	public void testCreateMedicalRecord() throws Exception{
		
		mockMvc.perform(post("/medicalRecord")
				.param("firstName", "James")
				.param("lastName", "Boyd")
				.param("birthDate", "01/07/1998")
				.param("medications", "aznol:350mg, hydrapermazol:100mg")
				.param("allergies", "nilliacilan"))
		        .andExpect(view().name("medicalRecord"))
		        .andExpect(model().hasNoErrors())
		        .andExpect(status().isOk());
		
	}
	
	@Test
	public void testCreateMedicalRecordMissingName() throws Exception {
		
		mockMvc.perform(post("/medicalRecord")
				.param("lastName", "Boyd")
				.param("birthDate", "01/07/1998")
				.param("medications", "aznol:350mg, hydrapermazol:100mg")
				.param("allergies", "nilliacilan"))
		        .andExpect(view().name("medicalRecord"))
		        .andExpect(model().attributeHasFieldErrors("medicalRecord", "firstName"))
		        .andExpect(status().isOk())
		        .andExpect(model().errorCount(1));		
	}	
}
