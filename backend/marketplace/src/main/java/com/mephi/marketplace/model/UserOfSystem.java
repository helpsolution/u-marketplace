package com.mephi.marketplace.model;

import java.time.LocalDateTime;

public abstract class UserOfSystem {
    private Long id;
    private LocalDateTime createdAt;
    private String username;
    private String password;
    private String phone;
    private String email;

    public UserOfSystem() {
    }

    public UserOfSystem(Long id, LocalDateTime createdAt, String username, String password, String phone, String email) {
        this.id = id;
        this.createdAt = createdAt;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}




