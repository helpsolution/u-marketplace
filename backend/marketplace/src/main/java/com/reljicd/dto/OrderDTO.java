package com.reljicd.dto;

import com.reljicd.model.OrderOfSystem;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 200, message = "Поле не должно быть длиннее 200 символов")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String address;

    private OrderOfSystem.PayingType payingType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "*Пожалуйста, заполните поле")
    private Date dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "*Пожалуйста, заполните поле")
    private Date dateTo;
}
