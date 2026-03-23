package com.alumninetwork.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String organization;
    private String category; // hackathon, internship, job, news
    private String domain;
    private String location;
    private String deadline; // For hackathons/internships
    private String stipendOrSalary;
    
    // Media support
    private String mediaUrl;
    private String mediaType; // image, video
    
    // Interactions
    private int likesCount = 0;
    private int sharesCount = 0;
    
    @jakarta.persistence.ElementCollection
    private java.util.Set<Long> likedByUserIds = new java.util.HashSet<>();
    
    private LocalDateTime createdAt = LocalDateTime.now();
    
    // Author info
    private Long authorId;
    private String authorName;
}
