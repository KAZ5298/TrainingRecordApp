package com.example.training_app.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.training_app.form.UserRegisterForm;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserRegisterForm> {

    private static final Logger logger = LoggerFactory.getLogger(PasswordMatchesValidator.class);
    
    @Override
    public boolean isValid(UserRegisterForm form, ConstraintValidatorContext context) {
        
        logger.debug("パスワード: {}", form.getPassword());
        logger.debug("パスワード確認: {}", form.getPasswordConfirm());
        
        boolean isValid = form.getPassword().equals(form.getPasswordConfirm());
        if (!isValid) {
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                   .addConstraintViolation()
                   .disableDefaultConstraintViolation();
        }
        
        return isValid;
    }

}
