package com.fundamentals.springdata.repository;

import com.fundamentals.springdata.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo,Long> {

}
