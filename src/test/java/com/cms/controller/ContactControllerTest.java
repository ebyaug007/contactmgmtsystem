package com.cms.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.hamcrest.core.IsEqual;

import com.cms.dto.Contact;
import com.cms.repository.ContactRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jdk.jfr.ContentType;

@SpringBootTest
public class ContactControllerTest {
	private MockMvc mockMvc;
	@Autowired
	ContactRepository contactRepo;
	@Autowired
	WebApplicationContext wac;
	
	@BeforeEach
	public void setup()
	{
		this.mockMvc = webAppContextSetup(this.wac).build();
	}
	@AfterEach
	public void tearDown()
	{
		contactRepo.deleteAll();
	}
	static String jsonToString(Object obj)
	{
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}
	@Test
	public void createContactTest() throws Exception
	{
		Contact actualContact = Contact.builder().id(1).name("John").address("Kochi")
				.email("john@xyz.com")
				.phone("9876543120")
				.build();
		
		mockMvc.perform(post("/cms/addContact")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(actualContact)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.success",is(true)));
	}
	
	@Test
	public void deleteContactTest() throws Exception
	{
		
		Contact actualContact = Contact.builder().id(1).name("John").address("Kochi")
				.email("john@xyz.com")
				.phone("9876543120")
				.build();
		contactRepo.save(actualContact);
		
		mockMvc.perform(delete("/cms/deleteContact/"+ actualContact.getId())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.success",is(true)));
	}
	
	@Test
	public void getAllUsersTest() throws Exception
	{
		
		Contact actualContact1 = Contact.builder().id(1).name("John").address("Kochi")
				.email("john@xyz.com")
				.phone("9876543121")
				.build();
		Contact actualContact2 = Contact.builder().id(1).name("Jane").address("Kochi")
				.email("jane@xyz.com")
				.phone("9876543122")
				.build();
		Contact actualContact3 = Contact.builder().id(1).name("Jack").address("Kochi")
				.email("jack@xyz.com")
				.phone("9876543123")
				.build();
		
		contactRepo.save(actualContact1);
		contactRepo.save(actualContact2);
		contactRepo.save(actualContact3);
		
		mockMvc.perform(get("/cms/getAllContacts")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
}
