package com.sourceplate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourceplate.formclass.ContactForm;
import com.sourceplate.model.Contact;
import com.sourceplate.model.Tag;
import com.sourceplate.repo.ContactRepository;
import com.sourceplate.repo.TagRepository;

@Service
public class ContactService {
	
	@Autowired
	TagRepository tagRepository;
	
	@Autowired
	ContactRepository contactRepository;
	
	public Contact createContact(ContactForm cf) {
		List<Tag> tags = new ArrayList<Tag>();
		for(int tid : cf.getTagids()) {
			tags.add(tagRepository.getOne(tid));
		}
		Contact contact = new Contact(cf.getContactname(), cf.getPhone(), cf.getEmail(), tags);
		return contactRepository.save(contact);
		
	}

	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

}
