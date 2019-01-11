package com.reljicd.model;

import lombok.Data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "seller_id")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private User user;

    @Column(name = "product_name", nullable = false, unique = true)
    @Length(min = 3, message = "*Название должно содержать от 3 символов")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private BigDecimal price;

    @Column(name = "product_color", nullable = false)
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String color;

    @Column(name = "product_size", nullable = false)
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String size;

    @Column(name = "product_count", nullable = false)
    @Min(value = 0, message = "*Количество должно быть неотрицательным")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
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
}
