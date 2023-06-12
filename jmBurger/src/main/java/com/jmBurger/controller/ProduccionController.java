package com.jmBurger.controller;

import com.jmBurger.entity.Produccion;
import com.jmBurger.repository.PedidoRepository;
import com.jmBurger.repository.ProduccionRepository;
import com.jmBurger.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProduccionController {
    @Autowired
    ProduccionRepository produccionRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping("produccion")
    public String listarProduccion(Model model){
        model.addAttribute("produccion",
                produccionRepository.findAll());
        return "produccion";
    }

    @GetMapping("produccion/nuevo")
    public String mostrarFormularioNuevo(Model model){
        model.addAttribute("produccion", new Produccion());
        model.addAttribute("pedido",
                pedidoRepository.findAll());
        model.addAttribute("producto",
                productoRepository.findAll());
        return "nuevaProduccion";
    }
}
