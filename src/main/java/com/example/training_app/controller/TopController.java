package com.example.training_app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.training_app.model.User;

//@Controller
//public class TopController {
//    
//    @GetMapping("/top")
//    public String getTop(Model model) {
//    	// ログインユーザー情報取得
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();
//        
//        model.addAttribute("userId", user.getId());
//        
//        return "top/index";
//    }
//
//}
@Controller
public class TopController {

    @GetMapping("/top")
    public String getTop(Model model) {
        // ログインユーザー情報取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            model.addAttribute("userId", user.getId());
        } else {
            // 認証されていない場合の処理（例: リダイレクト）
            return "redirect:/login";
        }

        return "top/index";
    }
}
