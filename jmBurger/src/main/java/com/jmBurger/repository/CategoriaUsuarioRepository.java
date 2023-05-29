package com.jmBurger.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmBurger.entity.CategoriaUsuario;

@Repository
public interface CategoriaUsuarioRepository extends JpaRepository<CategoriaUsuario, Integer> {
    CategoriaUsuario findByNombreCategoriaUsuario(String nombreCategoriaUsuario);
}
