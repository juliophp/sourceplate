package com.sourceplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.sourceplate.formclass.CategoryForm;
import com.sourceplate.formclass.RegisterForm;
import com.sourceplate.formclass.TagForm;
import com.sourceplate.model.Category;
import com.sourceplate.model.Contact;
import com.sourceplate.model.Tag;
import com.sourceplate.model.User;
import com.sourceplate.service.CategoryService;
import com.sourceplate.service.TagService;
import com.sourceplate.service.UserService;

@Controller
@RequestMapping("/tags")
public class TagController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private TagService tagService;
	
	
	@GetMapping("/")
	public String showCreateTag(Authentication auth, Model model) {
		
		User user = this.userService.getActiveUser(auth);
		
		model.addAttribute("customer", user);		
		List<Category> categories = this.categoryService.getAllCategories();
		List<Tag> tags = this.tagService.getAllTags();

		model.addAttribute("tags", tags);
		model.addAttribute("categories", categories);
		model.addAttribute("tag", new TagForm());
		return "CreateTag";
	}
	
	@PostMapping("/create")
	public String postCreateCategory(@Valid @ModelAttribute TagForm tf, BindingResult result, RedirectAttributes redirect,
			Model model, Authentication auth) {
		
		if (result.hasErrors()) {
			model.addAttribute("warn", result.getFieldError().getDefaultMessage());
		}else {
			this.tagService.createTag(tf.getCatid(), tf.getName());
		}
		
		List<Category> categories = this.categoryService.getAllCategories();
		List<Tag> tags = this.tagService.getAllTags();
		User user = this.userService.getActiveUser(auth);
		model.addAttribute("categories", categories);
		model.addAttribute("tags", tags);
		model.addAttribute("customer", user);
		model.addAttribute("tag", new TagForm());
		return "CreateTag";
	}
	
	@GetMapping("/{name}/contacts/")
	public String getSingleTagsContact(@PathVariable String name, Authentication auth, Model model) {
		List<Contact> contacts = this.tagService.getAllContacts(name);
		User user = this.userService.getActiveUser(auth);

		model.addAttribute("contacts", contacts);
		model.addAttribute("customer", user);

		return "ContactList";
	}
	
	

}
