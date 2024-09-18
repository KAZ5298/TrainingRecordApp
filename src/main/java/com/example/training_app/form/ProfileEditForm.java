package com.example.training_app.form;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.training_app.custom.PasswordMatches;

import lombok.Data;

@Data
@PasswordMatches
public class ProfileEditForm {
    
    @NotBlank(message = "ユーザー名は入力必須です")
    private String name;
    
    @NotBlank(message = "メールアドレスは入力必須です")
    @Email(message = "正しいメールアドレスを入力してください")
    private String email;
    
    @NotBlank(message = "パスワードは入力必須です")
    private String password;
    
    @NotBlank(message = "パスワード確認は入力必須です")
    private String passwordConfirm;
    
    @NotBlank(message = "姓は入力必須です")
    private String familyName;
    
    @NotBlank(message = "名は入力必須です")
    private String firstName;
    
    @NotNull(message = "生年月日は入力必須です")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    
    @NotNull(message = "年齢は入力必須です")
    @Min(value = 0, message = "年齢は正しく入力してください")
    private Integer age;
    
    @NotNull(message = "体重は入力必須です")
    @Min(value = 0, message = "体重は正しく入力してください")
    private Double weight;
    
    @NotNull(message = "身長は入力必須です")
    @Min(value = 0, message = "身長は正しく入力してください")
    private Double height;
    
    @NotNull(message = "性別は選択必須です")
    private Integer gender;
    
    private String profileImage;
}
