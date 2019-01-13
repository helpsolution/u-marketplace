package com.reljicd.model;

import lombok.Data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "seller_id")
//    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private Seller seller;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_category")
//    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private Category category;

    @Column(name = "product_name", nullable = false, unique = true)
    @Length(min = 3, message = "*Название должно содержать от 3 символов")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false,precision=10, scale=2)
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    @NotNull
    private BigDecimal price;

    @Column(name = "product_color", nullable = false)
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String color;

    @Column(name = "product_size", nullable = false)
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String size;

    @Column(name = "product_count", nullable = false)
    @Min(value = 0, message = "*Количество должно быть неотрицательным")
    @NotNull
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Product(Seller seller, Category category, String name, String description, BigDecimal price, String color, String size, Integer quantity) {
        this.seller = seller;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    public Product() {

    }
}
