package com.reljicd.model;

import lombok.Data;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "companyName", unique = true, nullable = false)
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    @Length(max = 13, message = "*Your company name is too long")
    private String companyName;

    @Column(name = "inn", nullable = false)
    @Length(max = 12, message = "*Your inn is too long")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String inn;

    @Column(name = "ogrn", nullable = false)
    @Length(max = 13, message = "*Your inn is too long")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String ogrn;

    @Column(name = "legal_address", nullable = false)
    @Length(max = 200, message = "*Your legal address is too long")
    @NotEmpty(message = "*Пожалуйста, заполните поле")
    private String legal_address;

    @Column(name = "actual_address", nullable = false)
    @Length(max = 200, message = "*Your actual address is too long")
    private String actual_address;
}
