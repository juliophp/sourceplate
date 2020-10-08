package com.sourceplate.formclass;

import javax.validation.constraints.Size;

public class TagForm {
	@Size(max = 40, min = 3, message = "Tag name entered is invalid. It must be between {2} and {20} characters.")
    private String name;
	
	private int catid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCatid() {
		return catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	
}
