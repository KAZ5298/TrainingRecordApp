package com.example.training_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.training_app.model.User;
import com.example.training_app.repository.UserMapper;
import com.example.training_app.repository.UserRepository;
import com.example.training_app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		mapper.registerUser(user);
	}
	
	@Override
	public User getLoginUser(String username) {
		return userRepository.findByName(username)
			.orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));
	}
}
