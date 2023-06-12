package com.jmBurger.repository;

import com.jmBurger.entity.Pedido;
import com.jmBurger.entity.Produccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ProduccionRepository extends JpaRepository<Produccion, Integer> {
    Produccion findByFechaPedido(Date fechaPedido);

}
