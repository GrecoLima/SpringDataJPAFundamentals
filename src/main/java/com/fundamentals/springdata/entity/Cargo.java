package com.fundamentals.springdata.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 40,unique = true)
    private String nome;

}
