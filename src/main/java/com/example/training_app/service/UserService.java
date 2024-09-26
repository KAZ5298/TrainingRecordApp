package com.example.training_app.service;

import com.example.training_app.model.User;

public interface UserService {
    
    public User getLoginUser(String username);
    
    public void registerUser(User user);
    
    public User getUserOne(Long id);
    
    public void updateUser(User user);
    
    public void updatePassword(User user);

	
    
}
