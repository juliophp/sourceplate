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
import com.sourceplate.model.Tag;
import com.sourceplate.model.User;
import com.sourceplate.service.CategoryService;
import com.sourceplate.service.UserService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/")
	public String showCreateCategory(Authentication auth, Model model) {
		
		User user = this.userService.getActiveUser(auth);
		
		model.addAttribute("customer", user);		
		List<Category> categories = this.categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("category", new CategoryForm());
		return "CreateCategory";
	}
	
	@PostMapping("/create")
	public String postCreateCategory(@Valid @ModelAttribute CategoryForm cf, BindingResult result, RedirectAttributes redirect,
			Model model, Authentication auth) {
		
		if (result.hasErrors()) {
			model.addAttribute("warn", result.getFieldError().getDefaultMessage());
		}else {
			this.categoryService.findOrCreateCategory(cf.getName());
		}
		
		List<Category> categories = this.categoryService.getAllCategories();
		User user = this.userService.getActiveUser(auth);
		
		model.addAttribute("categories", categories);
		model.addAttribute("customer", user);
		model.addAttribute("category", new CategoryForm());
		return "CreateCategory";
	}
	
	@GetMapping("/{name}/tags")
	public String getSingleCategoryTags(@PathVariable String name, Authentication auth, Model model) {
		Category category = this.categoryService.findOrCreateCategory(name);
		List<Category> categories = this.categoryService.getAllCategories();
		List<Tag> tags = this.categoryService.getAllTagsFromCategory(category.getName());
		
		User user = this.userService.getActiveUser(auth);
		
		model.addAttribute("tags", tags);
		model.addAttribute("customer", user);
		model.addAttribute("categories", categories);
		model.addAttribute("tag", new TagForm());

		return "CreateTag";
	}
	
	

}
