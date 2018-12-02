package com.mephi.marketplace.model;

import java.time.LocalDateTime;

public class Analyst extends UserOfSystem {
    private String fullName;

    public Analyst() {
    }

    public Analyst(Long id, LocalDateTime createdAt, String username, String password, String phone, String email, String fullName) {
        super(id, createdAt, username, password, phone, email);
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
