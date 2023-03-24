package com.cms.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cms.dto.Contact;
import com.cms.repository.ContactRepository;

@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {
	@Mock
	ContactRepository contactRepo;

	ContactServiceImpl contactService;

	@BeforeEach
	public void setup() {
		this.contactService = new ContactServiceImpl(contactRepo);
	}

	@AfterEach
	public void tearDown() {
		contactRepo.deleteAll();
	}

	@Test
	public void createContactTest() {
		Contact actualContact = Contact.builder().id(1).name("John").address("Kochi").email("john@xyz.com")
				.phone("9876543120").build();

		when(contactRepo.save(ArgumentMatchers.eq(actualContact))).thenReturn(actualContact);

		boolean success = contactService.createContact(actualContact);
		Assertions.assertEquals(true, success);

	}

	@Test
	public void deleteContactTest() {
		Contact actualContact = Contact.builder().id(1).name("John").address("Kochi").email("john@xyz.com")
				.phone("9876543120").build();

		when(contactRepo.findById(ArgumentMatchers.eq(actualContact.getId()))).thenReturn(Optional.of(actualContact));

		contactService.deleteContact(actualContact.getId());

		verify(contactRepo, times(1)).deleteById(actualContact.getId());

	}

	@Test
	public void getAllUsersTest() {
		Contact actualContact1 = Contact.builder().id(1).name("John").address("Kochi").email("john@xyz.com")
				.phone("9876543121").build();
		Contact actualContact2 = Contact.builder().id(1).name("Jane").address("Kochi").email("jane@xyz.com")
				.phone("9876543122").build();
		Contact actualContact3 = Contact.builder().id(1).name("Jack").address("Kochi").email("jack@xyz.com")
				.phone("9876543123").build();

		when(contactRepo.findAll()).thenReturn(List.of(actualContact1, actualContact2, actualContact3));
		contactService.getAllContacts();
		verify(contactRepo,times(1)).findAll();
	}

}
