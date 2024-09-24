package com.example.training_app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.training_app.model.LoginUserDetails;

@Controller
public class TopController {
    
    @GetMapping("/top")
    public String getTop(Model model) {
        // ログインユーザー情報を取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // 認証情報が存在し、ユーザー情報が正しく取得できる場合
        if (authentication != null && authentication.getPrincipal() instanceof LoginUserDetails) {
            LoginUserDetails user = (LoginUserDetails) authentication.getPrincipal();
            model.addAttribute("userId", user.getUserId()); // userIdをモデルに追加
            model.addAttribute("profileImage", user.getprofileImage());
        } else {
            // 認証されていない場合はログイン画面にリダイレクト
            return "redirect:/login";
        }
        
        return "top/index";
    }
}
