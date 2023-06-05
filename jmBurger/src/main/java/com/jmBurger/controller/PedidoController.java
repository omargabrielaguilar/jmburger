package com.jmBurger.controller;
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
        return "nuevoPedido";
    }

    @PostMapping("pedidos/nuevo")
    public String crearPedido(@ModelAttribute("pedido") Pedido pedido){
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


    @GetMapping("pedidos/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable("id") int id, Model model){

    }

}
