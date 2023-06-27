package com.jmBurger.controller;

import com.jmBurger.entity.Pedido;
import com.jmBurger.entity.Produccion;
import com.jmBurger.entity.Producto;
import com.jmBurger.repository.PedidoRepository;
import com.jmBurger.repository.ProduccionRepository;
import com.jmBurger.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



@Controller
public class ProduccionController {
    @Autowired
    ProduccionRepository produccionRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    PedidoRepository pedidoRepository;

    private boolean autenticado = false;

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

    @GetMapping("produccion")
    public String listarProduccion(Model model){
        if (!autenticado) {
            return "redirect:/login";
        }
        model.addAttribute("produccion",
                produccionRepository.findAll());
        return "produccion";
    }

}
