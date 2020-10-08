package com.sourceplate.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sourceplate.model.Contact;



public interface ContactRepository extends JpaRepository<Contact, Integer> {

	Contact findByContactname(String string);
	boolean existsContactByContactname(String roleName);

}
