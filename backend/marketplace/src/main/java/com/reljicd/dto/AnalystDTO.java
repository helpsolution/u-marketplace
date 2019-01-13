package com.reljicd.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class AnalystDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 5, message = "*Имя пользователя должно содержать минимум 5 символов")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String username;

    @Length(min = 5, message = "*Пароль должен содержать минимум 5 символов")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    @JsonIgnore
    private String password;

    @Email(message = "*Пожалуйста, заполните Email правильно")
    @NotEmpty(message = "*Пожалуйста, заполните поле email")
    private String email;

    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String name;

    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String phone;

    @Length(max = 200, message = "Поле не должно быть длиннее 200 символов")
    private String specialization;
}
