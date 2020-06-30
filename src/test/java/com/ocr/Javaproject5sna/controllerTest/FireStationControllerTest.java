package com.ocr.Javaproject5sna.controllerTest;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ocr.Javaproject5sna.service.FireStationService;

public class FireStationControllerTest {


	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webContext;
	
	@MockBean
	private FireStationService fireStationService;
	
	@Before
	public void setupMockmvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
}
