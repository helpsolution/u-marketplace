package com.mephi.marketplace.model;

import java.time.LocalDateTime;

public class Product {
    private Long id;
    private LocalDateTime createdAt;
    private Seller seller;
    private String name;
    private String mainCategory;
    private Float price;
    private String description;
    private String color;
    private String size;
    private Long count;

    public Product() {
    }

    public Product(Long id, LocalDateTime createdAt, Seller seller, String name, String mainCategory, Float price, String description, String color, String size, Long count) {
        this.id = id;
        this.createdAt = createdAt;
        this.seller = seller;
        this.name = name;
        this.mainCategory = mainCategory;
        this.price = price;
        this.description = description;
        this.color = color;
        this.size = size;
        this.count = count;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Seller getSeller() {
        return seller;
    }

    public String getName() {
        return name;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public Float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public Long getCount() {
        return count;
    }
}
