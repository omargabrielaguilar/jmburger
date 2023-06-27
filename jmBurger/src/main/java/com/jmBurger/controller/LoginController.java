package com.jmBurger.controller;

import com.jmBurger.entity.Usuario;
import com.jmBurger.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    //tema de sesionesss
    private final CategoriaController categoriaController;
    private final CategoriaUsuarioController categoriaUsuarioController;




    private boolean autenticado = false;

    @Autowired
    public LoginController(UsuarioRepository usuarioRepository, CategoriaController categoriaController, CategoriaUsuarioController categoriaUsuarioController) {
        this.usuarioRepository = usuarioRepository;
        this.categoriaController = categoriaController;
        this.categoriaUsuarioController = categoriaUsuarioController;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("nombreUsuario") String nombreUsuario, @RequestParam("contraseniaHash") String contraseniaHash, Model model) {

        // Verificar las credenciales del usuario
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (usuario != null && nombreUsuario.equals(usuario.getNombreUsuario()) && contraseniaHash.equals(usuario.getContraseniaHash())) {


            System.out.println("Credenciales válidas. Redireccionando al dashboard");
            categoriaController.setAutenticado(true);
            categoriaUsuarioController.setAutenticado(true);


            autenticado = true; // Marcar al usuario como autenticado
            // Credenciales válidas, redirigir al dashboard
            return "redirect:/dashboard";
        } else {
            System.out.println("Credenciales inválidas. Mostrando mensaje de error");
            // Credenciales inválidas, mostrar un mensaje de error en la página de inicio de sesión
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        if (!autenticado) {
            // El usuario no está autenticado, redirigir al login
            return "redirect:/login";
        }
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout() {
        // Realizar las acciones de cierre de sesión si es necesario
        autenticado = false; // Marcar al usuario como no autenticado

        // Redirigir a la página de inicio de sesión
        return "redirect:/login";
    }

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }
}

