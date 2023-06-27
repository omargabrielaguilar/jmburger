package com.jmBurger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Nombre del archivo HTML del formulario de inicio de sesión
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }

    @PostMapping("/login")
    public String postLogin() {
        // Realiza cualquier lógica adicional de autenticación o verificación aquí
        // ...

        return "redirect:/dashboard"; // Redirige al usuario a la página de dashboard
    }
}
