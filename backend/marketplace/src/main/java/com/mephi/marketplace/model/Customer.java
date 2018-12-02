package com.mephi.marketplace.model;

import java.time.LocalDateTime;

public class Customer extends UserOfSystem{

    private String fullName;
    private String address;

    public Customer() {
    }

    public Customer(Long id, LocalDateTime createdAt, String username, String password, String phone, String email, String fullName, String address) {
        super(id, createdAt, username, password, phone, email);
        this.fullName = fullName;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
