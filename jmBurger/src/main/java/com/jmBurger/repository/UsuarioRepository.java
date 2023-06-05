package com.jmBurger.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmBurger.entity.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{
    Usuario findByNombreUsuario(String nombreUsuario);
}
