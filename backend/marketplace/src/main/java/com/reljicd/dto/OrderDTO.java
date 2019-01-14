package com.reljicd.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OrderDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 200, message = "Поле не должно быть длиннее 200 символов")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String address;


    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String payingType;
}
