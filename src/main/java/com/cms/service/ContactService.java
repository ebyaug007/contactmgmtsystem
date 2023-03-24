package com.cms.service;

import java.util.List;
import java.util.Optional;

import com.cms.dto.Contact;

public interface ContactService {
	public boolean createContact(Contact contact);
	public List<Contact> getAllContacts();
	public boolean deleteContact(int contactId);
}
