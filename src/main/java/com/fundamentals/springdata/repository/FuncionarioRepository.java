package com.fundamentals.springdata.repository;

import com.fundamentals.springdata.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
