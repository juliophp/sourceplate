package com.sourceplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourceplate.model.Category;
import com.sourceplate.model.Tag;
import com.sourceplate.repo.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public Category findOrCreateCategory(String categoryname) {
			Category category = null;
			if (this.checkCategory(categoryname)) {
				category = categoryRepository.findByName(categoryname);
			} else {
				Category newcategory = new Category();
				newcategory.setName(categoryname);
				category = categoryRepository.save(newcategory);
			}
			return category;
		}
	public Category getCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}
	
	public boolean checkCategory(String categoryname) {
		boolean categorycheck = categoryRepository.existsCategoryByName(categoryname);
		return categorycheck;
	}
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	public List<Tag> getAllTagsFromCategory(String name){
		Category category = categoryRepository.findByName(name);
		return category.getTags();
	}
}

