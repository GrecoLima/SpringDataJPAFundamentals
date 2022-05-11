package com.fundamentals.springdata.entity;

import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String descricao;
    @Column(name = "data_inicio",nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataInicio;
    @Column(name = "data_fim")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataFim;

    @ManyToOne
    //Muitos projetos estao relacionados a um cliente e um cliente pode ter varios projetos
    @JoinColumn(name = "cliente_id_fk",nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "lider_id_fk",nullable = false)
    private Funcionario lider;

    @Column(nullable = false)
    @NumberFormat(style = NumberFormat.Style.CURRENCY,pattern = "#,##0.00")
    private BigDecimal orcamento;

    @Column(nullable = false)
    @NumberFormat(style = NumberFormat.Style.CURRENCY,pattern = "#,##0.00")
    private BigDecimal gastos;

    @ManyToMany
    @JoinTable(
            name = "projeto_funcionario",
            joinColumns = @JoinColumn(name = "projeto_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id_fk")
    )
    private List<Funcionario> equipe;
}
