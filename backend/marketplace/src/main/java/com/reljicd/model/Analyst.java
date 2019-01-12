package com.reljicd.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class Analyst {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_of_system_id")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private User user;
}
