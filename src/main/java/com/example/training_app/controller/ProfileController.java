package com.example.training_app.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.training_app.form.PasswordUpdateForm;
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
        
        PasswordUpdateForm passwordUpdateForm = new PasswordUpdateForm();
        passwordUpdateForm.setId(user.getId());
        model.addAttribute("passwordUpdateForm", passwordUpdateForm);
        
        // ヘッダーのプロフィール画像用
        model.addAttribute("profileImage", profileForm.getProfileImage());
        
        logger.info("フォームデータ: {}", profileForm);
        
        return "profile/index";
    }
    
    @PostMapping("/profile/{id}")
    public String updateProfile(@ModelAttribute @Valid ProfileForm profileForm,
    		BindingResult bindingResult, @PathVariable("id") Long id, Model model) {
    	
    	// エラー時
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            
            model.addAttribute("errorMessages", errorMessages);
            
            model.addAttribute("genderMap", applicationService.getGenderMap());
            
            return "profile/index";
        }
        
        User user = userService.getUserOne(id);
        
        if (user != null) {
        	user.setName(profileForm.getName());
        	user.setEmail(profileForm.getEmail());
        	user.setFamilyName(profileForm.getFamilyName());
        	user.setFirstName(profileForm.getFirstName());
        	user.setAge(profileForm.getAge());
        	user.setWeight(profileForm.getWeight());
        	user.setHeight(profileForm.getHeight());
        	user.setGender(profileForm.getGender());
        	
        	userService.updateUser(user);
        }
        
        return "redirect:/top";
    }
    
}
