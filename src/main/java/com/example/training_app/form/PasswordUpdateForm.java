package com.example.training_app.form;

import jakarta.validation.constraints.NotBlank;

import com.example.training_app.custom.UpdatePasswordMatches;

import lombok.Data;

@Data
@UpdatePasswordMatches
public class PasswordUpdateForm {
    
    private Long id;
    
    @NotBlank(message = "現パスワードは入力必須です")
    private String currentPassword;
    
    @NotBlank(message = "新パスワードは入力必須です")
    private String newPassword;
    
    @NotBlank(message = "新パスワード（確認）は入力必須です")
    private String newPasswordConfirm;
}
