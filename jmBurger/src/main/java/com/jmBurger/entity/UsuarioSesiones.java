package com.jmBurger.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jmBurger.entity.Usuario;
import com.jmBurger.repository.UsuarioRepository;

@Service
public class UsuarioSesiones implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository; // Reemplaza "UsuarioRepository" por el nombre de tu repositorio de usuarios

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Realiza una consulta a la base de datos para obtener los datos del usuario por su nombre de usuario o correo electrónico
        Usuario usuario = usuarioRepository.findByNombreUsuario(username);

        // Lanza una excepción UsernameNotFoundException si no se encuentra el usuario en la base de datos
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // Construye un objeto UserDetails con los detalles del usuario obtenidos de la base de datos
        // Puedes utilizar la clase org.springframework.security.core.userdetails.User para construir el objeto UserDetails
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(usuario.getNombreUsuario())
                .password(usuario.getContraseniaHash())
                .roles(usuario.getCategoriaUsuario().getNombreCategoriaUsuario()) // Reemplaza "getRol()" por el método que obtenga los roles del usuario
                .build();

        return userDetails;
    }
}
