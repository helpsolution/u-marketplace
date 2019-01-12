package com.reljicd.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user_of_system")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 5, message = "*Имя пользователя должно содержать минимум 5 символов")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String username;

    @Column(name = "password", nullable = false)
    @Length(min = 5, message = "*Пароль должен содержать минимум 5 символов")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    @JsonIgnore
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "*Пожалуйста, заполните Email правильно")
    @NotEmpty(message = "*Пожалуйста, заполните поле email")
    private String email;

    @Column(name = "fullname", nullable = false)
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String name;

    @Column(name = "phone", nullable = false)
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String phone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

}
