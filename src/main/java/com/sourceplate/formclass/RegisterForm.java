package com.sourceplate.formclass;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class RegisterForm {
	@Size(max = 20, min = 3, message = "First name entered is invalid. It must be between {3} and {20} characters.")
	private String firstname;
	@Size(max = 13, min = 9, message = "Invalid email! Please enter a valid email")
	private String mobilenumber;
	@Email(message = "Invalid email! Please enter a valid email")
	private String email;
	@Size(max = 20, min = 3, message = "Last name entered is invalid. It must be between {3} and {20} characters.")
	private String lastname;
	@Size(max = 8, min = 4, message = "password must be between {4} and {8} characters")
	private String password;
	
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}