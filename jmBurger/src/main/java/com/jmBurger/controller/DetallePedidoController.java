package com.jmBurger.controller;

import com.jmBurger.entity.DetallePedido;
import com.jmBurger.entity.Pedido;
import com.jmBurger.repository.DetallePedidoRepository;
import com.jmBurger.repository.PedidoRepository;
import com.jmBurger.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DetallePedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @GetMapping("detallepedidos")
    public String listarPedido(Model model){
        model.addAttribute("detallepedidos",detallePedidoRepository.findAll());
        return "detallepedidos";
    }

    @GetMapping("detallepedidos/nuevo")
    public String mostrarFormularioNuevo(Model model){
        model.addAttribute("detallepedido",new DetallePedido());
        model.addAttribute("pedido", pedidoRepository.findAll());
        model.addAttribute("producto", productoRepository.findAll());
        return "nuevoDetallePedido";
    }



}
