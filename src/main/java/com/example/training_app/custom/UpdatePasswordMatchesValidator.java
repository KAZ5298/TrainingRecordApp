package com.example.training_app.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.example.training_app.form.ProfileForm;

public class UpdatePasswordMatchesValidator implements ConstraintValidator<UpdatePasswordMatches, ProfileForm> {
    
    @Override
    public boolean isValid(ProfileForm form, ConstraintValidatorContext context) {
        
        if (form.getNewPassword() != null && !form.getNewPassword().isEmpty()) {
            boolean isValid = form.getNewPassword().equals(form.getNewPasswordConfirm());
            if (!isValid) {
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            }
            return isValid;
        }
        return true;
    }
}
