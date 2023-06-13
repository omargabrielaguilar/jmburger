package com.jmBurger.controller;

import com.jmBurger.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DetalleProduccionController {
    @Autowired
    private ProduccionRepository produccionRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private DetalleProduccionRepository detalleProduccionRepository;

    @GetMapping("detalleproduccion")
    public String listarDetalleProduccion(Model model){
        model.addAttribute("detalleproduccion", detalleProduccionRepository.findAll());
        return "detalleproduccion";
    }
}
