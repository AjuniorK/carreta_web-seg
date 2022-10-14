package com.carretas.carretas.carreta.repository;

import com.carretas.carretas.carreta.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface Produtos extends JpaRepository<Produto, Integer > {
}
