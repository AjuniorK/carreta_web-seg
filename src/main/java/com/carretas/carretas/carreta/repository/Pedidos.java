package com.carretas.carretas.carreta.repository;

import com.carretas.carretas.carreta.entity.Cliente;
import com.carretas.carretas.carreta.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

//@Repository
public interface Pedidos extends JpaRepository<Pedido, Integer > {

    List<Pedido> findByCliente(Cliente cliente);
}
