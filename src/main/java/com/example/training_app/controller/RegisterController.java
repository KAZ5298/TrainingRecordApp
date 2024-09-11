package com.example.training_app.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.training_app.model.User;
import com.example.training_app.service.ApplicationService;
import com.example.training_app.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String getRegister(Model model) {
		model.addAttribute("genderMap", applicationService.getGenderMap());
		return "register/index";
	}
	
	@PostMapping("/register")
	public String postRegister(Model model) {
		
		User user = modelMapper.map(model, User.class);
		
		userService.registerUser(user);
		
		return "login/index";
	}

}
