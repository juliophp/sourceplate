package com.sourceplate.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sourceplate.model.Role;
import com.sourceplate.model.User;
import com.sourceplate.repo.UserRepository;






@Service
public class CustomUserDetailsService implements UserDetailsService {

	 @Autowired
	 private UserRepository userRepository;
	 
	 
	 @Override
	    public CustomUserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
			 User user = userRepository.findByMobilenumber(phone);
			 
		    if (user == null)
		       throw new UsernameNotFoundException("Bad username and password credentials");

		    CustomUserDetails customUser = new CustomUserDetails();
		    customUser.setUser(user);
		    
			List<Role> roles = user.getRoles();
	        //logger.debug("role of the user" + roles);

	        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	        for(Role role: roles){
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
		    
	        
	        customUser.setAuthorities(authorities);

	        return customUser;


		    

//		    User(user.getId().toString(), user.getPassword(), getAuthorities(user));
//	         return new CustomUserdetails(user.getEmail(), user.getPassword(), getAuthorities(user));
	    }
	 
	 	@Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        return bCryptPasswordEncoder;
	    }
	 
//	 	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
//	        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
//	        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//	        return authorities;
//	    }
	 
	 
	 
	 

}
