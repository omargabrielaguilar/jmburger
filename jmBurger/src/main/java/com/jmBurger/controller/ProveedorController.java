package com.jmBurger.controller;

import com.jmBurger.entity.Proveedor;
import com.jmBurger.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProveedorController {
    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping("proveedores")
    public String listarProveedores(Model model){
        model.addAttribute("proveedores", proveedorRepository.findAll());
        return "proveedores";
    }

    @GetMapping("proveedores/nuevo")
    public String mostrarFormularioNuevo(Model model){
        model.addAttribute("proveedor", new Proveedor());
        return "nuevoProveedor";
    }

    @PostMapping("proveedores/nuevo")
    public String crearProveedor(@ModelAttribute("proveedor") Proveedor proveedor){
        proveedorRepository.save(proveedor);
        return "redirect:/proveedores";
    }

    @GetMapping("proveedores/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model){
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("Proveedor no encontrado con ID: " + id));
        model.addAttribute("proveedor", proveedor);
        return "editarProveedor";
    }

    @PostMapping("proveedores/{id}/editar")
    public String actualizarProveedor(@PathVariable("id") int id, @ModelAttribute("proveedor") Proveedor proveedor){
        proveedor.setIdProveedor(id);
        proveedorRepository.save(proveedor);
        return "redirect:/proveedores";
    }

    @GetMapping("proveedores/{id}/borrar")
    public String borrarProveedor(@PathVariable("id") int id){
        proveedorRepository.deleteById(id);
        return "redirect:/proveedores";
    }
}
