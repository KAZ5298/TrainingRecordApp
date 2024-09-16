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
import org.springframework.web.bind.annotation.PostMapping;

import com.example.training_app.form.UserRegisterForm;
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
    
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    
    @GetMapping("/register")
    public String getRegister(@ModelAttribute UserRegisterForm userRegisterForm, Model model) {
        model.addAttribute("genderMap", applicationService.getGenderMap());
        return "register/index";
    }
    
    @PostMapping("/register")
    public String postRegister(@ModelAttribute @Valid UserRegisterForm userRegisterForm,
            BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            
            model.addAttribute("errorMessages", errorMessages);
            
            if (!userRegisterForm.getPassword().equals(userRegisterForm.getPasswordConfirm())) {
                bindingResult.rejectValue("passwordConfirm", "error.passwordConfirm", "パスワードが一致しません");
            }
            
            model.addAttribute("genderMap", applicationService.getGenderMap());
            
            return "register/index";
        }
        
        User user = modelMapper.map(userRegisterForm, User.class);
        
        userService.registerUser(user);
        
        logger.info("フォームデータ: {}", userRegisterForm);
        
        return "redirect:/login/index";
    }
    
}
