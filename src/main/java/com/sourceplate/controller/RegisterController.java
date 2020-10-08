package com.sourceplate.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sourceplate.formclass.RegisterForm;
import com.sourceplate.service.RegisterService;


@Controller
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	
	
	@RequestMapping("/login")
	public String showLogin() {
		
		return "LoginPage";
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		
		model.addAttribute("register", new RegisterForm());
		return "RegisterPage";
	}
	
	@GetMapping("/")
	public String showHomePage(Model model) {
		
		return "redirect:/contacts/create";
	}
	
	
	@PostMapping("/register")
	public String postRegister(@Valid @ModelAttribute RegisterForm rf, BindingResult result, RedirectAttributes redirect, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("warn", result.getFieldError().getDefaultMessage());
		}else {
			   HashMap<String, String> resp = this.registerService.creatUser(rf);
			   if(resp.get("respcode").equals("00")) {
				   redirect.addFlashAttribute("success", "You are registered, Please Log In");
				   return "redirect:/login";
			   }else {
				   model.addAttribute("warn", resp.get("respmessage"));	   
			   }
		   }
	model.addAttribute("register", new RegisterForm());
	return "RegisterPage";
	}

}
