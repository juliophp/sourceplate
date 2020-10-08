package com.sourceplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.sourceplate.formclass.ContactForm;
import com.sourceplate.model.Contact;
import com.sourceplate.model.Tag;
import com.sourceplate.model.User;
import com.sourceplate.service.ContactService;
import com.sourceplate.service.TagService;
import com.sourceplate.service.UserService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private TagService tagService;
	
	@Autowired
	private ContactService contactService;
	
	
	@GetMapping("/create")
	public String showCreateContact(Authentication auth, Model model) {
		
		User user = this.userService.getActiveUser(auth);
		model.addAttribute("contact", new ContactForm());
		model.addAttribute("customer", user);
		List<Tag> tags = this.tagService.getAllTags();
		model.addAttribute("tags", tags);

		return "CreateContact";
	}
	
	@GetMapping("/")
	public String getCreateContact(Authentication auth, Model model) {
		
		User user = this.userService.getActiveUser(auth);
		model.addAttribute("customer", user);
		List<Contact> contacts = this.contactService.getAllContacts();
		model.addAttribute("contacts", contacts);

		return "ContactList";
	}
	
	@PostMapping("/create")
	public String postCreateContact(@Valid @ModelAttribute ContactForm cf, Authentication auth,  BindingResult result, 
			RedirectAttributes redirect, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("warn", result.getFieldError().getDefaultMessage());
		}else {
			this.contactService.createContact(cf);
		}
		
		return "redirect:/contacts/";
	}
}
