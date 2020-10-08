package com.sourceplate.formclass;

import java.util.List;

import javax.validation.constraints.Size;

public class ContactForm {
	@Size(max = 40, min = 3, message = "First name entered is invalid. It must be between {3} and {20} characters.")
	private String contactname;
	@Size(max = 14, min = 3, message = "First name entered is invalid. It must be between {3} and {20} characters.")
	private String phone;
	private String email;
	private List<Integer> tagids;
	
	public String getContactname() {
		return contactname;
	}
	public void setContactname(String contactname) {
		this.contactname = contactname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Integer> getTagids() {
		return tagids;
	}
	public void setTagids(List<Integer> tagids) {
		this.tagids = tagids;
	}
	
	

	
	


}
