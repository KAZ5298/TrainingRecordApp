package com.example.training_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {
	
	@GetMapping("/top")
	public String getLogin() {
		return "top/index";
	}

	public TopController() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

}
