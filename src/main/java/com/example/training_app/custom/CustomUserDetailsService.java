package com.example.training_app.custom;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.training_app.model.LoginUserDetails;
import com.example.training_app.model.User;
import com.example.training_app.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username)  // データベースからユーザーを検索
				.orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));
		
		return new LoginUserDetails(user);
	}
}
