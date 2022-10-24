package com.carretas.carretas.carreta.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {
    private Integer codigo;
    private String cpf;
    private String nomeCliente;
    private Float total;
    private String dataPedido;
    private String status;
    private List<InformacaoItemPedidoDTO> items;
}
