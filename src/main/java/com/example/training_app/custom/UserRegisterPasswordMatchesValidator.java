package com.example.training_app.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.example.training_app.form.UserRegisterForm;

public class UserRegisterPasswordMatchesValidator implements ConstraintValidator<UserRegisterPasswordMatches, UserRegisterForm> {
    
    @Override
    public boolean isValid(UserRegisterForm form, ConstraintValidatorContext context) {
        
        boolean isValid = form.getPassword().equals(form.getPasswordConfirm());
        if (!isValid) {
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                   .addConstraintViolation()
                   .disableDefaultConstraintViolation();
        }
        
        return isValid;
    }

}
