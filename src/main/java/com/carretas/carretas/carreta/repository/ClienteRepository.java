package com.carretas.carretas.carreta.repository;

import com.carretas.carretas.carreta.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    @Modifying
    @Transactional
    @Query("update Cliente c set c.cpf = ?1, c.nome = ?2, c.email = ?3 where c.id = ?4 ")
    void setClienteInfoById(String cpf, String nome, String email, Integer id);
}
