package com.example.training_app.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.training_app.model.User;

@Mapper
public interface UserMapper {
	
	public int registerUser(User user);

}
