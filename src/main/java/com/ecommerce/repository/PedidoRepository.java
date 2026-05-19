package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.entity.Pedido;
import com.ecommerce.entity.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuarioOrderByDataPedidoDesc(Usuario usuario);
    List<Pedido> findTop5ByOrderByDataPedidoDesc();
    List<Pedido> findTop10ByOrderByDataPedidoDesc();
    
    @Modifying
    @Transactional
    @Query("UPDATE Pedido p SET p.statusPedido = :status WHERE p.idPedido = :id")
    void updateStatusPedido(@Param("id") Long id, @Param("status") Pedido.StatusPedido status);
}