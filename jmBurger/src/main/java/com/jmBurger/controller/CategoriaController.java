package com.jmBurger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.jmBurger.entity.Categoria;
import com.jmBurger.repository.CategoriaRepository;

@Controller
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("categorias")
    public String listarCategorias(Model model){
        model.addAttribute("categorias",
                categoriaRepository.findAll());
        return "categorias";
    }

    @GetMapping("categorias/nuevo")
    public String mostrarFormularioNuevo(Model model){
        model.addAttribute("categoria",
                new Categoria());
        return "nuevaCategoria";
    }

    @PostMapping("categorias/nuevo")
    public String crearCategoria(@ModelAttribute("categoria") Categoria categoria){
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("categorias/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("Categoria no encontrada con ID: " + id));

        model.addAttribute("categoria", categoria);
        return "editarCategoria";
    }



}
