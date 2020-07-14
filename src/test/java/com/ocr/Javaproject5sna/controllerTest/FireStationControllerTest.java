package com.ocr.Javaproject5sna.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

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
import com.ocr.Javaproject5sna.dto.EveryHouseHoldInfoDTO;
import com.ocr.Javaproject5sna.dto.NumberOfPersonsDTO;
import com.ocr.Javaproject5sna.dto.PersonDetailsDTO;
import com.ocr.Javaproject5sna.dto.PersonDetailsFromStationNumberDTO;
import com.ocr.Javaproject5sna.dto.PersonInEachAddressDTO;
import com.ocr.Javaproject5sna.dto.PersonInfoPlusAddressFromEachStationDTO;
import com.ocr.Javaproject5sna.dto.ResponseDTO;
import com.ocr.Javaproject5sna.model.FireStation;
import com.ocr.Javaproject5sna.modelClass.StationNum_AddressModelClass;
import com.ocr.Javaproject5sna.service.FireStationService;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class FireStationControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webContext;

	@MockBean
	private FireStationService fireStationService;

	@Autowired
	ObjectMapper objectMapper;

	@Before
	public void setupMockmvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}

	@Test
	public void testCreatefirestationAddressMapping() throws Exception {

		FireStation fireStation = new FireStation();
		String stationNumber = "2";
		String address = "15 Soft St";
		Set<String> addresses = new HashSet<>();
		addresses.add(address);
		fireStation.addAddress(address);
		fireStation.setStationNumber(stationNumber);
		Mockito.when(fireStationService.createFSAddressmapping(stationNumber, address)).thenReturn(fireStation);

		String json = objectMapper.writeValueAsString(fireStation);

//		String json = ("{\"station\":\"3\",\"address\":\"15 Soft St\"}");

		MvcResult mvcResult = mockMvc
				.perform(post("/firestation").param("stationNumber", stationNumber)
						.param("address", addresses.toString())
						.flashAttr("fireStationCreated", new StationNum_AddressModelClass())
						.contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.isSuccessful());
		Assert.assertFalse(result.getErrors());

	}

	@Test
	public void testCreatefirestationAddressMappingMissingStationNumber() throws Exception {

		FireStation fireStation = new FireStation();
		String stationNumber = "";
		String address = "15 Soft St";
		Set<String> addresses = new HashSet<>();
		addresses.add(address);
		fireStation.addAddress(address);
		fireStation.setStationNumber(stationNumber);
		Mockito.when(fireStationService.createFSAddressmapping(stationNumber, address)).thenReturn(fireStation);

		String json = objectMapper.writeValueAsString(fireStation);

//		String json = ("{\"station\":\"3\",\"address\":\"15 Soft St\"}");

		MvcResult mvcResult = mockMvc
				.perform(post("/firestation").param("stationNumber", stationNumber)
						.param("address", addresses.toString())
						.flashAttr("fireStationCreated", new StationNum_AddressModelClass())
						.contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.getErrors());
	}

	@Test
	public void testUpdateFirestationAddressMapping() throws Exception {

		FireStation fireStation = new FireStation();
		String stationNumber = "2";
		String address = "188 Culver St";
		Set<String> addresses = new HashSet<>();
		addresses.add(address);
		fireStation.addAddress(address);
		fireStation.setStationNumber(stationNumber);
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(fireStation);
		Mockito.when(fireStationService.createFSAddressmapping(stationNumber, address)).thenReturn(fireStation);
		Mockito.when(fireStationService.findAll()).thenReturn(fireStations);

		String json = objectMapper.writeValueAsString(fireStations);

		MvcResult mvcResult = mockMvc
				.perform(put("/firestation").param("stationNumber", stationNumber)
						.param("address", addresses.toString())
						.flashAttr("fireStationUpdated", new StationNum_AddressModelClass())
						.contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.isSuccessful());
		Assert.assertFalse(result.getErrors());

	}

	@Test
	public void testDeleteFirestationAddressMapping() throws Exception {
		
		FireStation fireStation = new FireStation();
		String stationNumber = "2";
		String address = "188 Culver St";
		Set<String> addresses = new HashSet<>();
		addresses.add(address);
		fireStation.addAddress(address);
		fireStation.setStationNumber(stationNumber);
		List<FireStation> fireStations = new ArrayList<>();
	    fireStations.add(fireStation);
	    
		Mockito.when(fireStationService.findAll()).thenReturn(fireStations);
		doNothing().when(fireStationService).deleteFSAddressMapping(stationNumber, address);
		

		String json = objectMapper.writeValueAsString(fireStations);

		MvcResult mvcResult = mockMvc
				.perform(delete("/firestation").param("stationNumber", stationNumber)
						.param("address", addresses.toString()).contentType("application/json").content(json)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

		ResponseDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponseDTO.class);

		Assert.assertTrue(result.isSuccessful());
		Assert.assertFalse(result.getErrors());

	}
	
	@Test
	public void testPersonDetailsFromFireStationNumber() throws Exception {
		
		String stationNumber = "3";
		
		NumberOfPersonsDTO numberOfPerson = new NumberOfPersonsDTO();
		ArrayList<NumberOfPersonsDTO> numberOfKidsPlusAdults = new ArrayList<>();
		ArrayList<PersonDetailsDTO> personsDetails = new ArrayList<>();
		PersonDetailsDTO person1 = new PersonDetailsDTO("John", "Boyd", "1509 Culver St", "8686-988-9887");
		PersonDetailsDTO person2 = new PersonDetailsDTO("Roger", "Boyd", "1509 Culver St", "8686-988-9887");
		
		personsDetails.add(person1);
		personsDetails.add(person2);
		numberOfPerson.setAdults(1);
		numberOfPerson.setChildren(1);
		
		numberOfKidsPlusAdults.add(numberOfPerson);
		
		PersonDetailsFromStationNumberDTO dto = new PersonDetailsFromStationNumberDTO(numberOfKidsPlusAdults, personsDetails);

		Mockito.when(fireStationService.getPersonDetailsFromFireStationNumber(stationNumber)).thenReturn(dto);
		
		String json = objectMapper.writeValueAsString(dto);
		
		MvcResult mvcResult = mockMvc
				.perform(get("/firestation").param("stationNumber",stationNumber).contentType(MediaType.APPLICATION_JSON)
						.content(json).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		
		PersonDetailsFromStationNumberDTO result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
				PersonDetailsFromStationNumberDTO.class);
		assertEquals(1, result.getNumberOfKidsPlusAdult().size());
		assertEquals(2, result.getPersonsDetails().size());
	}
	
	@Test
	public void testPersonPhoneNumberFromFireStationNumber() throws Exception {
		
		List<String> phoneNumberOfEachPerson = new ArrayList<>();
		String phoneNumber = "868-7878-7676";
		phoneNumberOfEachPerson.add(phoneNumber);
		
		String stationNumber = "3";
		
		Mockito.when(fireStationService.getPersonPhoneNumberFromFireStationNumber(stationNumber)).thenReturn(phoneNumberOfEachPerson);
		
		MvcResult mvcResult = mockMvc.perform(get("/phoneAlert").param("stationNumber", stationNumber)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isOk()).andReturn();
		
		List<String> result = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
				new TypeReference<List<String>>() {
				});
		
		assertEquals(1, result.size());
		assertEquals(phoneNumber, result.get(0));
	}
	
	@Test
	public void testPersonByAddress() throws Exception{
		
		String address = "1509 Culver St";
		String stationNumber = "3";
		Set<String> medications = new HashSet<String>();
		medications.add("aznol:350mg, hydrapermazol:100mg");
		Set<String> allergies = new HashSet<>();
		allergies.add("nilliacilan");
		
		PersonInEachAddressDTO person = new PersonInEachAddressDTO("John", "Boyd", "1509 Culver St", "8686-988-9887" ,36,
				medications, allergies);
		
		ArrayList<PersonInEachAddressDTO> personsDetails = new ArrayList<>();
        personsDetails.add(person);
		
		PersonInfoPlusAddressFromEachStationDTO dto = new PersonInfoPlusAddressFromEachStationDTO(personsDetails, stationNumber);

		Mockito.when(fireStationService.getAddressFromEachStation(address)).thenReturn(dto);
		
		MvcResult mvcResult = mockMvc.perform(get("/fire").param("address", address)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType("application/json")).andExpect(status().isOk())
				.andReturn();
		
		PersonInfoPlusAddressFromEachStationDTO result = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
				PersonInfoPlusAddressFromEachStationDTO.class);
		
		assertEquals(1, result.getPersonsInAddress().size());
		assertEquals("3", result.getStationNumber().toString());
	}
	
	
//	@Test
//	public void testPersonsByFireStationNumber() throws Exception{
//		
//		String stationNumber = "3";
//		String address = "1509 Culver St";
//		Set<String> medications = new HashSet<String>();
//		medications.add("aznol:350mg, hydrapermazol:100mg");
//		Set<String> allergies = new HashSet<>();
//		allergies.add("nilliacilan");
//		ArrayList<PersonInEachAddressDTO> everybodyInfo = new ArrayList<>();
//		List<String> addressesInFireStation = new ArrayList<>();
//		List<EveryHouseHoldInfoDTO> addressesWithPersons = new ArrayList<>();
//		PersonInEachAddressDTO personInfo = new PersonInEachAddressDTO("John", "Boyd", "1509 Culver St", "8686-988-9887" ,36,
//				medications, allergies);
//		
//		everybodyInfo.add(personInfo);
//		
//				
//		EveryHouseHoldInfoDTO dto = new EveryHouseHoldInfoDTO(address, everybodyInfo);
//		
//		MvcResult mvcResult = mockMvc.perform(get("/stations").param("stationNumber", stationNumber)
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//				.andExpect(content().contentType("application/json")).andExpect(status().isOk())
//				.andReturn();
//		List<EveryHouseHoldInfoDTO> result = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
//				new TypeReference<List<EveryHouseHoldInfoDTO>>() {
//		});
//		
//		
//	}
}
