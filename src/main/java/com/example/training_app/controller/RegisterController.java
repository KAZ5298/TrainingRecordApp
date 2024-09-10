package com.example.training_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.training_app.service.ApplicationService;

@Controller
public class RegisterController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@GetMapping("/register")
	public String getRegister(Model model) {
		model.addAttribute("genderMap", applicationService.getGenderMap());
		return "register/index";
	}

}
