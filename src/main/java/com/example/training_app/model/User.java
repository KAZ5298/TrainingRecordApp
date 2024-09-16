package com.example.training_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String familyName;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private int age;
    
    @Column(nullable = false)
    private double weight;
    
    @Column(nullable = false)
    private double height;
    
    @Column(nullable = false)
    private int gender;
    
    private String profileImage;
}
