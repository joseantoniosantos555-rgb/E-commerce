package com.ecommerce.repository;

import com.ecommerce.entity.Pagamento;
import com.ecommerce.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByPedido(Pedido pedido);
}