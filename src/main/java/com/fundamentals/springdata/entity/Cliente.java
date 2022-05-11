package com.fundamentals.springdata.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Cliente extends Pessoa{

    @OneToMany(mappedBy = "cliente")
    private List<Projeto> projetos;

}
