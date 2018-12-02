package com.mephi.marketplace.model;

import java.time.LocalDateTime;

public class OrderOfSystem {
    private Long id;
    private LocalDateTime createdAt;
    private Basket basket;
    private String deliveryAddress;
    private LocalDateTime deliveryTime;
    private PayingType payingType;
    private LocalDateTime payingTime;

    public OrderOfSystem() {
    }

    public OrderOfSystem(Long id, LocalDateTime createdAt, Basket basket, String deliveryAddress, LocalDateTime deliveryTime, PayingType payingType, LocalDateTime payingTime) {
        this.id = id;
        this.createdAt = createdAt;
        this.basket = basket;
        this.deliveryAddress = deliveryAddress;
        this.deliveryTime = deliveryTime;
        this.payingType = payingType;
        this.payingTime = payingTime;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setPayingType(PayingType payingType) {
        this.payingType = payingType;
    }

    public void setPayingTime(LocalDateTime payingTime) {
        this.payingTime = payingTime;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Basket getBasket() {
        return basket;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public PayingType getPayingType() {
        return payingType;
    }

    public LocalDateTime getPayingTime() {
        return payingTime;
    }
}
