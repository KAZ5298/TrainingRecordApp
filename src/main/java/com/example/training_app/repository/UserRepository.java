package com.example.training_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.training_app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByName(String name);
}