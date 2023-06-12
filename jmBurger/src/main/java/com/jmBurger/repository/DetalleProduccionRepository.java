package com.jmBurger.repository;

import com.jmBurger.entity.DetalleProduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleProduccionRepository extends JpaRepository<DetalleProduccion, Integer> {
}
