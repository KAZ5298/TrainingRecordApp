package com.example.training_app.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = UpdatePasswordMatchesValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdatePasswordMatches {
    String message() default "{PasswordMatches.profileForm}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
