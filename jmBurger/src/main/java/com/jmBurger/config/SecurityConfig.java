package com.jmBurger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jmBurger.entity.UsuarioSesiones;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioSesiones usuarioSesiones;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioSesiones)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/**").hasRole("administrador")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") // Especifica la URL de la página de inicio de sesión
                .defaultSuccessUrl("/dashboard") // Especifica la URL de éxito después de iniciar sesión
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    // Otras configuraciones de seguridad, como autorizaciones y configuraciones de CORS, CSRF, etc.
}
