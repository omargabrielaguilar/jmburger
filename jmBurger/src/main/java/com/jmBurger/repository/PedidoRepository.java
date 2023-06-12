package com.jmBurger.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jmBurger.entity.Pedido;

import java.util.Date;

@Repository
public interface PedidoRepository extends  JpaRepository<Pedido, Integer> {
    Pedido findByFechaPedido(Date fechaPedido);
}
