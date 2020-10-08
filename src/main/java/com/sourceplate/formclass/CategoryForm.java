package com.sourceplate.formclass;

import javax.validation.constraints.Size;

public class CategoryForm {
	@Size(max = 20, min = 3, message = "Category name entered is invalid. It must be between {2} and {20} characters.")
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
