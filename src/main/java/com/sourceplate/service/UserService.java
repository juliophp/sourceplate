package com.sourceplate.service;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sourceplate.model.User;




@Service
public class UserService {
		
	public User getActiveUser(Authentication auth) {
		Object principal = auth.getPrincipal();
		return ((CustomUserDetails)principal).getUser();
	}
	

}
