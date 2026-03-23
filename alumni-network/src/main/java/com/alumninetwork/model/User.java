package com.alumninetwork.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String password;
    private String role; // "student" or "alumni"
    private String company;
    private String jobTitle;
    private Integer graduationYear;
    private String department;
    private String about;
    private String skills; // Stored as comma-separated or JSON string for simplicity
    private String location;
    private String headline;

    private String experience; // JSON or formatted text
    private String education; // JSON or formatted text
    private String projects; // JSON or formatted text
    private String profilePicture; // URL to the profile picture
}