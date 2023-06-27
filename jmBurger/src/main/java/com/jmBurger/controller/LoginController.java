package com.jmBurger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Nombre del archivo HTML del formulario de inicio de sesión
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard"; // Nombre de la vista del panel de control después de iniciar sesión
    }
}
