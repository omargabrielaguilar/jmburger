package com.jmBurger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.jmBurger.config.SecurityConfig;

@SpringBootApplication
@Import(SecurityConfig.class) // Agrega esta línea para importar la clase SecurityConfig
public class JmBurgerApplication {
    public static void main(String[] args) {
        SpringApplication.run(JmBurgerApplication.class, args);
    }
}
