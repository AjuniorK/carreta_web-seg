package com.carretas.carretas.carreta.repository;

import com.carretas.carretas.carreta.entity.itemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Repository
public interface itemsPedido extends JpaRepository<itemPedido, Integer > {
}
