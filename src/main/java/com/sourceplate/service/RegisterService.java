package com.sourceplate.service;

import java.util.Arrays;
import java.util.HashMap;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sourceplate.formclass.RegisterForm;
import com.sourceplate.model.Role;
import com.sourceplate.model.User;
import com.sourceplate.repo.RoleRepository;
import com.sourceplate.repo.UserRepository;

@Service
public class RegisterService {
	
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Role findOrCreateRole(String rolename) {
		Role role = null;
		if (this.checkRole(rolename)) {
			role = roleRepository.findByName(rolename);
		} else {
			Role newrole = new Role();
			newrole.setName(rolename);
			role = roleRepository.save(newrole);
		}
		return role;
	}

	public boolean checkRole(String rolename) {
		boolean rolecheck = roleRepository.existsRoleByName(rolename);
		return rolecheck;
	}
	
	
	public HashMap<String, String> creatUser(RegisterForm rf) throws HibernateException{
		HashMap<String, String> resp = new HashMap<String, String>();

		try{
			User user = new User();
			user.setFirstname(rf.getFirstname());
			user.setLastname(rf.getLastname());
			user.setMobilenumber(rf.getMobilenumber());
			user.setPassword(bCryptPasswordEncoder.encode(rf.getPassword()));
			user.setEmail(rf.getEmail());
			user.setRoles(Arrays.asList(this.findOrCreateRole("ROLE_USER")));
			userRepository.save(user);
			resp.put("respcode", "00");
		}catch(Exception ex){
			resp.put("respcode", "09");
			resp.put("respmessage", ex.getMessage());
		}
		return resp;
	}
	

}
