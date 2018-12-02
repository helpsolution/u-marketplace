package com.mephi.marketplace.model;

import java.time.LocalDateTime;

public class Basket {
    private Long id;
    private LocalDateTime createdAt;
    private Customer customer;
    private boolean isActive;

    public Basket() {
    }

    public Basket(Long id, LocalDateTime createdAt, Customer customer, boolean active) {
        this.id = id;
        this.createdAt = createdAt;
        this.customer = customer;
        this.isActive = active;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void activate() {
        this.isActive = true;
    }
    public void deactivate() {
        this.isActive = false;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean isActive() {
        return isActive;
    }
}
