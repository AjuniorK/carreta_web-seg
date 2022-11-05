package com.carretas.carretas.carreta.repository;

import com.carretas.carretas.carreta.entity.Pedido;
import com.carretas.carretas.carreta.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@Repository
public interface Produtos extends JpaRepository<Produto, Integer > {
    //SELECT PRODUTO_ID FROM ITEM_PEDIDO where QUANTIDADE = (SELECT MAX(QUANTIDADE ) as QUANTIDADE  from ITEM_PEDIDO )
    @Query(value = "select p.* from Produto p where p.id in (SELECT ip.produto_id FROM ITEM_PEDIDO ip where ip.quantidade in (SELECT MAX(ip2.QUANTIDADE ) from ITEM_PEDIDO ip2))", nativeQuery = true)
    List<Produto> topProduct();
}
