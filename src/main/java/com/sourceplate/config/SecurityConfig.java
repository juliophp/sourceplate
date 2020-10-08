package com.sourceplate.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Autowired
	    private UserDetailsService customUserDetailsService;
	 
	 
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	         .userDetailsService(customUserDetailsService)
	         .passwordEncoder(passwordEncoder());
	    }
	 
	 
	   

       @Override
       protected void configure(HttpSecurity http) throws Exception {
               http.
	                headers()
	               .frameOptions().sameOrigin()
	               .and()
                 .authorizeRequests()
                  .antMatchers("/forms").permitAll()
                  .antMatchers("/bower_components/**", "css/**", "/register").permitAll()
                  .antMatchers("/api/v1/**").permitAll()
                     .antMatchers("/admin/**").hasRole("ADMIN")
                     .anyRequest().authenticated()
                     .and()
                 .formLogin()
                 	. loginPage("/login")
                     .defaultSuccessUrl("/contacts/create")
                     .failureUrl("/login?error")
                     .permitAll()
                     .and()
                 .logout()
                  .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                  .logoutSuccessUrl("/login");               			
       }

       
       
       @Override
	   public void configure(WebSecurity web){
           web.ignoring()
           		.antMatchers("/bower_components/**")
               .antMatchers("/css/**")
               .antMatchers("/img/**")
               .antMatchers("/api/v1/claim")
               .antMatchers("/resources/static/**");      
       }
       
       
}
