package com.ocr.Javaproject5sna.controllerTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ocr.Javaproject5sna.controller.PersonController;
import com.ocr.Javaproject5sna.service.PersonService;

public class PersonControllerTest {

	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webContext;
	
	@MockBean
	private PersonService personService;
	
	@Before
	public void setupMockmvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	
   //@Test
	
}
