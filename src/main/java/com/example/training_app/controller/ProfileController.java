package com.example.training_app.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        
        // ログインユーザー情報取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        
        if (user == null) {
        	return "redirect:/top";
        }
        
        ProfileForm profileForm = modelMapper.map(user, ProfileForm.class);
        
        model.addAttribute("profileForm", profileForm);
        
        model.addAttribute("userId", user.getId());
        
        logger.info("フォームデータ: {}", profileForm);
        
        return "profile/index";
    }
    
}
