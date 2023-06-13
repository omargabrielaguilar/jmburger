package com.jmBurger.controller;

import com.jmBurger.entity.Pedido;
import com.jmBurger.entity.Produccion;
import com.jmBurger.entity.Producto;
import com.jmBurger.entity.Proveedor;
import com.jmBurger.repository.PedidoRepository;
import com.jmBurger.repository.ProduccionRepository;
import com.jmBurger.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("produccion/nuevo")
    public String crearProduccion(@ModelAttribute("produccion") Produccion produccion){
        Producto producto = productoRepository.findByNombreProducto(produccion.getProducto().getNombreProducto());
        Pedido pedido = pedidoRepository.findByFechaPedido(produccion.getPedido().getFechaPedido());

        if(pedido == null){
            pedido = new Pedido();
            pedido.setFechaPedido(produccion.getPedido().getFechaPedido());
            pedidoRepository.save(pedido);
        }

        if(producto == null){
            producto = new Producto();
            producto.setNombreProducto(produccion.getProducto().getNombreProducto());
            productoRepository.save(producto);
        }

        produccion.setPedido(pedido);
        produccion.setProducto(producto);
        produccionRepository.save(produccion);
        return "redirect:/produccion";
    }

    @GetMapping("produccion/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model){
        Produccion produccion = produccionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con ID: " + id));

        model.addAttribute("produccion", produccion);
        model.addAttribute("pedido", pedidoRepository.findAll());
        model.addAttribute("producto", productoRepository.findAll());

        return "editarProduccion";
    }

    @PostMapping("produccion/{id}/editar")
    public String actualizarProduccion(@PathVariable("id") int id, @ModelAttribute("produccion") Produccion produccion){
        return "redirect:/produccion";
    }

}
