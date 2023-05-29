package com.jmBurger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.jmBurger.repository.CategoriaUsuarioRepository;
import com.jmBurger.entity.CategoriaUsuario;

@Controller
public class CategoriaUsuarioController {
    @Autowired
    private CategoriaUsuarioRepository categoriaUsuarioRepository;

    @GetMapping("categoriausurios")
    public String listarCategoriaUsuarios(Model model){
        model.addAttribute("categoriausurios",
                categoriaUsuarioRepository.findAll());
        return "categoriausurios";
    }

    @GetMapping("categoriausurios/nuevo")
    public String mostrarFormularioNuevo(Model model){
        model.addAttribute("categoriausuario",
                new CategoriaUsuario());
        return "nuevaCategoriaUsuario";
    }

    @PostMapping("categoriausurios/nuevo")
    public String crearCategoriaUsuario(@ModelAttribute("categoriausuario") CategoriaUsuario categoriaUsuario){
        categoriaUsuarioRepository.save(categoriaUsuario);
        return "redirect:/categoriausurios";
    }

    @GetMapping("categoriausurios/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model){
        CategoriaUsuario categoriaUsuario = categoriaUsuarioRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("CategoriaUsuario no encontrada con ID: " + id));
        model.addAttribute("categoriaUsuario", categoriaUsuario);
        return "editarCategoriaUsuario";
    }

    @PostMapping("categoriausurios/{id}/editar")
    public String actualizarCategoriaUsuario(@PathVariable("id") int id, @ModelAttribute("categoriausuario") CategoriaUsuario categoriaUsuario){
        categoriaUsuario.setIdCategoriaUsuario(id);
        categoriaUsuarioRepository.save(categoriaUsuario);
        return "redirect:/categoriausurios";
    }

    @GetMapping("categoriausurios/{id}/borrar")
    public String borrarCategoriaUsuario(@PathVariable("id") int id){
        categoriaUsuarioRepository.deleteById(id);
        return  "redirect:/categoriausurios";
    }

}
