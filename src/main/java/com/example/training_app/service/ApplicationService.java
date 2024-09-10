package com.example.training_app.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
	
	public Map<Integer, String> getGenderMap() {
		Map<Integer, String> genderMap = new HashMap<>();
		genderMap.put(1, "男性");
		genderMap.put(2, "女性");
		genderMap.put(3, "その他");
		genderMap.put(4, "不明");
		
		return genderMap;
	}

}
