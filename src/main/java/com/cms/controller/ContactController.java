package com.cms.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.dto.Contact;
import com.cms.dto.Response;
import com.cms.service.ContactService;


@RestController
@RequestMapping("/cms")
public class ContactController {
	@Autowired
	ContactService contactService;
	
	@PostMapping("/addContact")
	public ResponseEntity<Response> createContact(@RequestBody Contact contact)
	{

		if(contactService.createContact(contact))
			return new ResponseEntity<Response>(Response.builder()
					.message("Phone number created")
					.isSuccess(true).build(), HttpStatus.CREATED);
		return new ResponseEntity<Response>(Response.builder()
				.message("Failed PhoneNumber already exists")
				.isSuccess(false).build(), HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getAllContacts")
	public ResponseEntity<List<Contact>> getAllContacts()
	{
		List<Contact> contacts =contactService.getAllContacts();
		if(contacts.isEmpty())
			return new ResponseEntity<List<Contact>>(List.of(null), HttpStatus.OK);
		return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteContact/{id}")
	public ResponseEntity<Response> deleteContact(@PathVariable int id)
	{
		if(contactService.deleteContact(id))
			return new ResponseEntity<Response>(Response.builder()
					.message("Deleted Contact")
					.isSuccess(true).build(), HttpStatus.OK);
			
		return new ResponseEntity<Response>(Response.builder()
				.message("Failed. ContactID doesnot exists")
				.isSuccess(false).build(), HttpStatus.BAD_REQUEST);
	}

}
