package com.jmBurger.controller;

import com.jmBurger.repository.CategoriaUsuarioRepository;
import com.jmBurger.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jmBurger.entity.CategoriaUsuario;
import com.jmBurger.entity.Usuario;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
    @Autowired
    private CategoriaUsuarioRepository categoriaUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("usuarios")
    public String listarUsuarios(Model model){
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @GetMapping("usuarios/nuevo")
    public String mostrarFormularioNuevo(Model model){
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("categoriaUsuarios", categoriaUsuarioRepository.findAll());
        return "nuevoUsuario";
    }

    @PostMapping("usuarios/nuevo")
    public String crearUsuario(@ModelAttribute("usuario") Usuario usuario){
        //verificamos si la categoriaUsuario ya existe en la base de datos
        CategoriaUsuario categoria = categoriaUsuarioRepository.findByNombreCategoriaUsuario(usuario.getCategoriaUsuario().getNombreCategoriaUsuario());

        if(categoria == null){
            //la categoria no existe, crear nueva
            categoria = new CategoriaUsuario();
            categoria.setNombreCategoriaUsuario(usuario.getCategoriaUsuario().getNombreCategoriaUsuario());
            categoriaUsuarioRepository.save(categoria);
        }

        //asignamos la categoriaUsuario al usuario
        usuario.setCategoriaUsuario(categoria);
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("usuarios/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
        model.addAttribute("usuario", usuario);
        model.addAttribute("categoriaUsuarios", categoriaUsuarioRepository.findAll());
        return "editarUsuarios";
    }

    @PostMapping("usuarios/{id}/editar")
    public String actualizarUsuario(@PathVariable("id") int id,  @ModelAttribute("usuario") Usuario usuario){
        usuario.setIdUsuario(id);

        //verificamos si la categoriaUsuario ya existe en la base de datos
        CategoriaUsuario categoria = categoriaUsuarioRepository.findByNombreCategoriaUsuario(usuario.getCategoriaUsuario().getNombreCategoriaUsuario());
        if(categoria == null){
            //la categoria no existe, crear nueva
            categoria = new CategoriaUsuario();
            categoria.setNombreCategoriaUsuario(usuario.getCategoriaUsuario().getNombreCategoriaUsuario());
            categoriaUsuarioRepository.save(categoria);
        }

        //asignamos la categoriaUsuario al usuario
        usuario.setCategoriaUsuario(categoria);

        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("usuarios/{id}/borrar")
    public String borrarUsuario(@PathVariable("id") int id){
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }
}
