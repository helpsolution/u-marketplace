package com.reljicd.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name = "user_of_system_id")
    private Long id;

    @Length(max = 200, message = "*Поле слишком длинное")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String address;


}
