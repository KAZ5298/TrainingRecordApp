package com.example.training_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.training_app.model.User;
import com.example.training_app.repository.UserMapper;
import com.example.training_app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	
	@Override
	public void registerUser(User user) {
		mapper.registerUser(user);
	}

}
