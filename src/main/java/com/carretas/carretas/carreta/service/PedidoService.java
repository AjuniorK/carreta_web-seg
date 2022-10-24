package com.carretas.carretas.carreta.service;

import com.carretas.carretas.carreta.dto.PedidoDTO;
import com.carretas.carretas.carreta.entity.Pedido;
import com.carretas.carretas.carreta.enums.StatusPedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto );
    Optional<Pedido> obterPedidoCompleto(Integer id);

    List<Pedido> obterTodosPedidos();
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
