package com.fundamentals.springdata.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 2)
    @Enumerated(EnumType.STRING)
    private UF uf;

    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false)
    private String bairro;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String logradouro;
    @Column(nullable = false)
    private String numero;

    private String complemento;




}
