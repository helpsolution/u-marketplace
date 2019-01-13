package com.reljicd.dto;

import lombok.Data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Entity
@Data
public class ProductDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String categoryId;


    @Length(min = 3, message = "*Название должно содержать от 3 символов")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String name;


    private String description;

    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String price;


    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String color;


    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String size;


    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDto product = (ProductDto) o;

        return id.equals(product.id);
    }

    public ProductDto( String categoryId, String name, String description, String price, String color, String size, String quantity) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }

    public ProductDto() {

    }
}
