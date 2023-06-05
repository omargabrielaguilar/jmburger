package com.jmBurger.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jmBurger.entity.Pedido;

@Repository
public interface PedidoRepository extends  JpaRepository<Pedido, Integer> {
}
