package com.example.training_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.training_app.service.ApplicationService;

@Controller
public class ProfileController {
    
    @Autowired
    private ApplicationService applicationService;
    
    @GetMapping("/profile/{id}")
    public String getProfile(Model model, @PathVariable("id") Long id) {
        model.addAttribute("genderMap", applicationService.getGenderMap());
        
//        User user = userService;
        return "profile/index";
    }
    
}
