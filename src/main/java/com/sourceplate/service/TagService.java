package com.sourceplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourceplate.model.Category;
import com.sourceplate.model.Contact;
import com.sourceplate.model.Tag;
import com.sourceplate.repo.CategoryRepository;
import com.sourceplate.repo.TagRepository;


@Service
public class TagService {
	
	@Autowired
	TagRepository tagRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public Tag findOrCreateTag(String tagname) {
		Tag tag = null;
		if (this.checkTag(tagname)) {
			tag = tagRepository.findByName(tagname);
		} else {
			Tag newtag = new Tag();
			newtag.setName(tagname);
			tag = newtag;
		}
		return tag;
	}
	
	public Tag createTag(int catid, String tagname) {
		Category cat = categoryRepository.getOne(catid);
		Tag tag = this.findOrCreateTag(tagname);
		tag.setCategory(cat);
		return tagRepository.save(tag);
	}

	public boolean checkTag(String tagname) {
		boolean tagcheck = tagRepository.existsTagByName(tagname);
		return tagcheck;
	}
	
	public List<Tag> getAllTags(){
		return tagRepository.findAll();
	}

	public List<Contact> getAllContacts(String name) {
		return tagRepository.findByName(name).getContacts();
	}
	
	
}