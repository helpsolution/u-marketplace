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
public class Analyst {

    @Id
    @Column(name = "user_of_system_id")
    private Long userOfSystemId;

    @Length(max = 200, message = "Поле не должно быть длиннее 200 символов")
    private String specialization;
}
