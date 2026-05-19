package com.ecommerce.repository;

import com.ecommerce.entity.ItemPedido;
import com.ecommerce.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    List<ItemPedido> findByPedido(Pedido pedido);
}