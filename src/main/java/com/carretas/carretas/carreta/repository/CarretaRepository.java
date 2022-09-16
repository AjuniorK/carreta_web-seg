package com.carretas.carretas.carreta.repository;

import com.carretas.carretas.carreta.entity.Carreta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarretaRepository extends JpaRepository<Carreta,Integer> {

    @Modifying
    @Transactional
    @Query("update Carreta c set c.placa = ?1, c.tipo = ?2, c.volume = ?3 where c.id = ?4 ")
    void setCarretaInfoById(String placa, String tipo, String volume, Integer id);
}
