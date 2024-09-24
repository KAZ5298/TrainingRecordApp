package com.example.training_app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;

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
    
    private static final String UPLOADED_FOLDER = "src/main/resources/static/images/profiles/";
    
    @GetMapping("/register")
    public String getRegister(@ModelAttribute UserRegisterForm userRegisterForm, Model model) {
        model.addAttribute("genderMap", applicationService.getGenderMap());
        return "register/index";
    }
    
    @PostMapping("/register")
    public String postRegister(@ModelAttribute @Valid UserRegisterForm userRegisterForm,
            BindingResult bindingResult, Model model) {
        
        // パスワード一致チェック
        if (!userRegisterForm.getPassword().equals(userRegisterForm.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "error.passwordConfirm", "パスワードが一致しません");
        }
        
        // エラー時
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            
            model.addAttribute("errorMessages", errorMessages);
            
            model.addAttribute("genderMap", applicationService.getGenderMap());
            
            return "register/index";
        }
        
        User user = modelMapper.map(userRegisterForm, User.class);
        
        // プロフィール画像保存処理
        MultipartFile profileImage = userRegisterForm.getProfileImage();
        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                // 実行ディレクトリを取得し、images/profilesに保存するパスを設定
                String baseDir = System.getProperty("user.dir");
                Path uploadPath = Paths.get(baseDir, UPLOADED_FOLDER);
                
                // ディレクトリが存在しない場合は作成
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                
                // ファイル名を一意にする（user_{name}_{timestamp}.png）
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String extension = profileImage.getOriginalFilename().substring(profileImage.getOriginalFilename().lastIndexOf("."));
                String fileName = "user_" + user.getName() + "_" + timestamp + extension;
                
                // ファイルを保存
                Path filePath = uploadPath.resolve(fileName);
                profileImage.transferTo(filePath.toFile());
                
                // 保存したファイルのパスをユーザーオブジェクトにセット
                user.setProfileImage("/images/profiles/" + fileName);
                
                userService.registerUser(user);
                
            } catch (IOException e) {
                logger.error("プロフィール画像の保存に失敗しました", e);
                return "register/index";
            }
        } else {
            logger.info("プロフィール画像は選択されていません");
        }
        
        logger.info("フォームデータ: {}", userRegisterForm);
        
        return "redirect:/login/index";
    }
    
}
