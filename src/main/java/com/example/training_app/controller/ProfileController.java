package com.example.training_app.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.training_app.form.ProfileForm;
import com.example.training_app.model.User;
import com.example.training_app.service.ApplicationService;
import com.example.training_app.service.UserService;

@Controller
public class ProfileController {
    
    @Autowired
    private ApplicationService applicationService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    
    @GetMapping("/profile/{id}")
    public String getProfile(Model model, @PathVariable("id") Long id) {
        model.addAttribute("genderMap", applicationService.getGenderMap());
        
        User user = userService.getUserOne(id);
        
        if (user == null) {
            return "redirect:/login";
        }
        
        ProfileForm profileForm = modelMapper.map(user, ProfileForm.class);
        
        model.addAttribute("profileForm", profileForm);
        
        logger.info("フォームデータ: {}", profileForm);
        
        return "profile/index";
    }
    
}
