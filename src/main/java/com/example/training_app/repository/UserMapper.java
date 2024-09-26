package com.example.training_app.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.training_app.model.User;

@Mapper
public interface UserMapper {
    
    public int registerUser(User user);
    
    public User getUserOne(Long id);
    
    public void updateUser(User user);
    
    public void updatePassword(User user);
    
}
