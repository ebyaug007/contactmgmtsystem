package com.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.dto.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
	List<Contact> findByPhone(String phone);
}
