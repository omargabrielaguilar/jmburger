package com.jmBurger.controller;

import com.jmBurger.entity.*;
import com.jmBurger.repository.DetallePedidoRepository;
import com.jmBurger.repository.PedidoRepository;
import com.jmBurger.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("detallepedidos/nuevo")
    public String crearDetallePedido(@ModelAttribute("detallepedido") DetallePedido detallePedido){
        Pedido pedido = pedidoRepository.findByFechaPedido(detallePedido.getPedido().getFechaPedido());

        Producto producto = productoRepository.findByNombreProducto(detallePedido.getProducto().getNombreProducto());

        if(pedido == null){
            pedido = new Pedido();
            pedido.setFechaPedido(detallePedido.getPedido().getFechaPedido());
            pedidoRepository.save(pedido);
        }

        if(producto == null){
            producto = new Producto();
            producto.setNombreProducto(detallePedido.getProducto().getNombreProducto());
            productoRepository.save(producto);
        }

        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);

        detallePedidoRepository.save(detallePedido);
        return "redirect:/detallepedidos";
    }


}
