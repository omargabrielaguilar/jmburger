package com.jmBurger.controller;

import com.jmBurger.entity.*;
import com.jmBurger.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("detalleproduccion/nuevo")
    public String mostrarFormularioNuevo(Model model){
        model.addAttribute("detalleproduccion", new DetalleProduccion());
        model.addAttribute("produccion", produccionRepository.findAll());
        model.addAttribute("producto", productoRepository.findAll());
        return "nuevoDetalleProduccion";
    }

    @PostMapping("detalleproduccion/nuevo")
    public String crearDetalleProduccion(@ModelAttribute("detalleproduccion") DetalleProduccion detalleProduccion){
        Produccion produccion = produccionRepository.findByFechaProduccion(detalleProduccion.getProduccion().getFechaProduccion());

        Producto producto = productoRepository.findByNombreProducto(detalleProduccion.getProducto().getNombreProducto());

        if(produccion == null){
            produccion = new Produccion();
            produccion.setFechaProduccion(detalleProduccion.getProduccion().getFechaProduccion());
            produccionRepository.save(produccion);
        }

        if(producto == null){
            producto = new Producto();
            producto.setNombreProducto(detalleProduccion.getProducto().getNombreProducto());
            productoRepository.save(producto);
        }

        detalleProduccion.setProduccion(produccion);
        detalleProduccion.setProducto(producto);

        detalleProduccionRepository.save(detalleProduccion);
        return "redirect:/detalleproduccion";
    }

    @GetMapping("detalleproduccion/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model){
        DetalleProduccion detalleProduccion = detalleProduccionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DetalleProduccion no encontrado con ID: " + id));

        model.addAttribute("detalleproduccion", detalleProduccion);
        model.addAttribute("produccion", produccionRepository.findAll());
        model.addAttribute("producto", productoRepository.findAll());
        return "editarDetalleProduccion";
    }

    @PostMapping("detalleproduccion/{id}/editar")
    public String actualizarDetalleProduccion(@PathVariable("id") int id, @ModelAttribute("detalleproduccion") DetalleProduccion detalleProduccion){
        detalleProduccion.setIdDetalleProduccion(id);

        Produccion produccion = produccionRepository.findByFechaProduccion(detalleProduccion.getProduccion().getFechaProduccion());

        Producto producto = productoRepository.findByNombreProducto(detalleProduccion.getProducto().getNombreProducto());

        if(produccion == null){
            produccion = new Produccion();
            produccion.setFechaProduccion(detalleProduccion.getProduccion().getFechaProduccion());
            produccionRepository.save(produccion);
        }

        if(producto == null){
            producto = new Producto();
            producto.setNombreProducto(detalleProduccion.getProducto().getNombreProducto());
            productoRepository.save(producto);
        }

        detalleProduccion.setProduccion(produccion);
        detalleProduccion.setProducto(producto);
        detalleProduccionRepository.save(detalleProduccion);
        return "redirect:/detalleproduccion";
    }

    @GetMapping("detalleproduccion/{id}/borrar")
    public String borrarDetalleProduccion(@PathVariable("id") int id){
        detalleProduccionRepository.deleteById(id);
        return "redirect:/detalleproduccion";
    }

}
