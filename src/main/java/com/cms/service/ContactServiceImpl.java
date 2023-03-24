package com.cms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.dto.Contact;
import com.cms.exception.InvalidPhoneNumber;
import com.cms.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactRepository contactRepo;
	
	public ContactServiceImpl(ContactRepository contactRepo)
	{
		this.contactRepo = contactRepo;
	}
	
	@Override
	public boolean createContact(Contact contact) {
		System.out.println(contact);
		if(!contactRepo.findByPhone(contact.getPhone()).isEmpty())
			return false;
		/*
		 * if(contact.getPhoneNumber().!=10) throw new
		 * InvalidPhoneNumber("Phone Number should be 10 digits");
		 */
		contactRepo.save(contact);
		return true;
	}

	@Override
	public List<Contact> getAllContacts() {
		// TODO Auto-generated method stub
		return contactRepo.findAll();
	}

	@Override
	public boolean deleteContact(int contactId) {
		// TODO Auto-generated method stub
		if(contactRepo.findById(contactId).isEmpty())
			return false;
		contactRepo.deleteById(contactId);
		return true;
	}

}
