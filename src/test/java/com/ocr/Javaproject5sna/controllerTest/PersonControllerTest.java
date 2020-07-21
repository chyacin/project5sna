package com.ocr.Javaproject5sna.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocr.Javaproject5sna.dto.ChildAlertDTO;
import com.ocr.Javaproject5sna.dto.PersonMedicalInfoDTO;
import com.ocr.Javaproject5sna.dto.PersonNamePlusAgeDTO;
import com.ocr.Javaproject5sna.dto.ResponseDTO;
import com.ocr.Javaproject5sna.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PersonControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webContext;

	@MockBean
	PersonService personService;

	@Autowired
	ObjectMapper objectMapper;

	@Before
	public void setupMockmvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	// { "firstName":"John", "lastName":"Boyd", "address":"1509 Culver St",
	// "city":"Culver", "zip":"97451", "phone":"841-874-6512",
	// "email":"jaboyd@email.com" },

	@Test
	public void testCreatePerson() throws Exception {

		String json = (" {\"firstName\":\"Joe\",\"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }");

		MvcResult mvcResult = mockMvc
				.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(json)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isCreated()).andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.isSuccessful());
		Assert.assertFalse(result.getErrors());

	}

	@Test
	public void testCreatePersonMissingAddress() throws Exception {

		String json = (" {\"firstName\":\"Joe\",\"lastName\":\"Boyd\", \"address\":\"\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }");

		MvcResult mvcResult = mockMvc
				.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(json)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isBadRequest()).andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.getErrors());

	}

	@Test
	public void testUpdatePerson() throws Exception {

		String json = (" {\"firstName\":\"Joe\",\"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }");

		MvcResult mvcResult = mockMvc
				.perform(put("/person").contentType(MediaType.APPLICATION_JSON).content(json)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isOk()).andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.isSuccessful());
		Assert.assertFalse(result.getErrors());

	}

	@Test
	public void testUpdatePersonMissingNames() throws Exception {

		String json = (" {\"firstName\":\"\",\"lastName\":\"\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }");

		MvcResult mvcResult = mockMvc
				.perform(put("/person").contentType(MediaType.APPLICATION_JSON).content(json)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isBadRequest()).andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.getErrors());

	}

	@Test
	public void testDeletePerson() throws Exception {

//		String json = (" {\"firstName\":\"John\",\"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }");

		MvcResult mvcResult = mockMvc
				.perform(delete("/person").param("firstName", "Joe").param("lastName", "Bing")
						.param("address", "1509 Culver St").param("city", "Culver").param("zip", "97451")
						.param("phone", "841-874-6512").param("email", "jaboyd@email.com")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isOk()).andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.isSuccessful());
		Assert.assertFalse(result.getErrors());

	}

	@Test
	public void testDeletePersonMissingFirstName() throws Exception {

//		String json = (" {\"firstName\":\"John\",\"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }");

		MvcResult mvcResult = mockMvc
				.perform(delete("/person").param("firstName", "").param("lastName", "Bing")
						.param("address", "1509 Culver St").param("city", "Culver").param("zip", "97451")
						.param("phone", "841-874-6512").param("email", "jaboyd@email.com")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isBadRequest()).andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.getErrors());

	}

	@Test
	public void testChildAlert() throws Exception {

		String address = "1509 Culver St";

		ArrayList<PersonNamePlusAgeDTO> children = new ArrayList<>();
		PersonNamePlusAgeDTO personNamePlusAgeDTO = new PersonNamePlusAgeDTO("Roger", "Boyd", 3);
		children.add(personNamePlusAgeDTO);

		ArrayList<PersonNamePlusAgeDTO> adults = new ArrayList<>();
		PersonNamePlusAgeDTO personNamePlusAgeDTO2 = new PersonNamePlusAgeDTO("John", "Boyd", 36);
		adults.add(personNamePlusAgeDTO2);

		ChildAlertDTO childAlertDTO = new ChildAlertDTO(children, adults);

		Mockito.when(personService.getChildrenFromEachAddress(address)).thenReturn(childAlertDTO);

		String json = objectMapper.writeValueAsString(childAlertDTO);

		MvcResult mvcResult = mockMvc
				.perform(get("/childAlert").param("address", address).contentType(MediaType.APPLICATION_JSON)
						.content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		ChildAlertDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
				ChildAlertDTO.class);

		assertEquals(1, result.getAdults().size());
		assertEquals(1, result.getChildren().size());
	}

	@Test
	public void personInfo() throws Exception {

		Set<String> medications = new HashSet<String>();
		medications.add("aznol:350mg, hydrapermazol:100mg");
		Set<String> allergies = new HashSet<>();
		allergies.add("nilliacilan");
		String firstName = "Jonanathan";
		String lastName = "Marrack";

		ArrayList<PersonMedicalInfoDTO> personInfo = new ArrayList<>();

		PersonMedicalInfoDTO person = new PersonMedicalInfoDTO(firstName, lastName, "29 15th St", "jaboyd@email.com",
				36, medications, allergies);
		personInfo.add(person);

		Mockito.when(personService.getPersonInfo(firstName, lastName)).thenReturn(personInfo);

		String json = objectMapper.writeValueAsString(personInfo);

		MvcResult mvcResult = mockMvc
				.perform(get("/personInfo").param("firstName", firstName).param("lastName", lastName)
						.contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		List<PersonMedicalInfoDTO> result = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
				new TypeReference<List<PersonMedicalInfoDTO>>() {
				});

		assertEquals(1, result.size());
		assertEquals(firstName, result.get(0).getFirstName());
		assertEquals(lastName, result.get(0).getLastName());

	}

	@Test
	public void testCommunityEmail() throws Exception {

		List<String> personEmail = new ArrayList<>();
		String email = "culver@yahoo.com";
		personEmail.add(email);

		String city = "Culver";
		
		Mockito.when(personService.getPersonsEmailAddress(city)).thenReturn(personEmail);

		MvcResult mvcResult = mockMvc.perform(get("/communityEmail").param("city", city)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isOk())
				.andReturn();

		List<String> result = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
				new TypeReference<List<String>>() {
				});

		assertEquals(1, result.size());
		assertEquals(email, result.get(0));
	}
}
