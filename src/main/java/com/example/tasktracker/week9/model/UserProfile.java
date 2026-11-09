package com.example.tasktracker.week9.model;

import jakarta.persistence.*;

@Entity
@Table(name = "week9_user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String bio;

    @OneToOne(mappedBy = "profile")
    private User user;

    public UserProfile() {}

    public UserProfile(String fullName, String bio) {
        this.fullName = fullName;
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}