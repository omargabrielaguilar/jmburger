package com.jmBurger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmBurger.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Producto findByNombreProducto(String nombreProducto);
}

