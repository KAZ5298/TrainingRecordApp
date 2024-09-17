package com.example.training_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.training_app.service.ApplicationService;

@Controller
public class ProfileController {
    
    @Autowired
    private ApplicationService applicationService;
    
    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("genderMap", applicationService.getGenderMap());
        return "profile/index";
    }
    
}
