package com.carretas.carretas.carreta.service.impl;

import com.carretas.carretas.carreta.dto.ItemPedidoDTO;
import com.carretas.carretas.carreta.dto.PedidoDTO;
import com.carretas.carretas.carreta.entity.Cliente;
import com.carretas.carretas.carreta.entity.Pedido;
import com.carretas.carretas.carreta.entity.Produto;
import com.carretas.carretas.carreta.entity.itemPedido;
import com.carretas.carretas.carreta.enums.StatusPedido;
import com.carretas.carretas.carreta.repository.Clientes;
import com.carretas.carretas.carreta.repository.Pedidos;
import com.carretas.carretas.carreta.repository.Produtos;
import com.carretas.carretas.carreta.repository.itemsPedido;
import com.carretas.carretas.carreta.service.PedidoService;
import com.carretas.carretas.carreta.util.excecao.PedidoNaoEncontradoException;
import com.carretas.carretas.carreta.util.excecao.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final itemsPedido itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        //pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<itemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);

        int qtd = itemsPedido.stream().filter(o -> o.getQuantidade()> 0).mapToInt(itemPedido::getQuantidade).sum();

        float soma_preco = 0;
        float total = 0;
        for (itemPedido item : itemsPedido) {
            Produto prd = item.getProduto();
            soma_preco = soma_preco + prd.getPreco();
        }
        total = soma_preco * qtd;

        pedido.setTotal(total);

        return pedido;
    }

   // @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    public List<Pedido> obterTodosPedidos() {
        return repository.findAll();
    }

    //@Override
    @Transactional
    public void atualizaStatus( Integer id, StatusPedido statusPedido ) {
        repository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException() );
    }

    private List<itemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    itemPedido itemPedido = new itemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());


    }

}
