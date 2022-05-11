package com.fundamentals.springdata.repository;

import com.fundamentals.springdata.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto,Long> {

}
