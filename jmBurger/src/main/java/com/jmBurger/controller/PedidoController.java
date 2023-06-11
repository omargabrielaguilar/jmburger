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

import com.jmBurger.repository.UsuarioRepository;
import com.jmBurger.entity.Usuario;
import com.jmBurger.repository.PedidoRepository;
import com.jmBurger.entity.Pedido;

@Controller
public class PedidoController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("pedidos")
    public String listarPedidos(Model model){
        model.addAttribute("pedidos",
                pedidoRepository.findAll());
        return "pedidos";
    }

    @GetMapping("pedidos/nuevo")
    public String mostrarFormularioNuevo(Model model){
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("usuario",
                usuarioRepository.findAll());
        model.addAttribute("proveedor",
                proveedorRepository.findAll());
        return "nuevoPedido";
    }

    @PostMapping("pedidos/nuevo")
    public String crearPedido(@ModelAttribute("pedido") Pedido pedido){
        Usuario usuario = usuarioRepository.findByNombreUsuario(pedido.getUsuario().getNombreUsuario());
        Proveedor proveedor = proveedorRepository.findByNombreProveedor(pedido.getProveedor().getNombreProveedor());

        if(usuario == null){
            usuario = new Usuario();
            usuario.setNombreUsuario(pedido.getUsuario().getNombreUsuario());
            usuarioRepository.save(usuario);
        }

        pedido.setUsuario(usuario);
        pedidoRepository.save(pedido);
        return "redirect:/pedidos";
    }


    @GetMapping("pedidos/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido no encontrado con ID: " + id));

        model.addAttribute("pedido", pedido);
        model.addAttribute("usuario", usuarioRepository.findAll());

        return "editarPedidos";
    }

    @PostMapping("pedidos/{id}/editar")
    public String actualizarPedido(@PathVariable("id") int id, @ModelAttribute("pedido") Pedido pedido){
        pedido.setIdPedido(id);

        Usuario usuario = usuarioRepository.findByNombreUsuario(pedido.getUsuario().getNombreUsuario());

        if(usuario == null){
            usuario = new Usuario();
            usuario.setNombreUsuario(pedido.getUsuario().getNombreUsuario());
            usuarioRepository.save(usuario);
        }
        pedido.setUsuario(usuario);
        pedidoRepository.save(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("pedidos/{id}/borrar")
    public String borrarPedido(@PathVariable("id") int id){
        pedidoRepository.deleteById(id);
        return "redirect:/pedidos";
    }

}
