package com.jmBurger.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria_usuario")
public class CategoriaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_usuario")
    private int idCategoriaUsuario;

    @Column(name = "nombre_categoria_usuario")
    private String nombreCategoriaUsuario;

    public CategoriaUsuario(){

    }

    public CategoriaUsuario(int idCategoriaUsuario, String nombreCategoriaUsuario) {
        this.idCategoriaUsuario = idCategoriaUsuario;
        this.nombreCategoriaUsuario = nombreCategoriaUsuario;
    }

    public int getIdCategoriaUsuario() {
        return idCategoriaUsuario;
    }

    public void setIdCategoriaUsuario(int idCategoriaUsuario) {
        this.idCategoriaUsuario = idCategoriaUsuario;
    }

    public String getNombreCategoriaUsuario() {
        return nombreCategoriaUsuario;
    }

    public void setNombreCategoriaUsuario(String nombreCategoriaUsuario) {
        this.nombreCategoriaUsuario = nombreCategoriaUsuario;
    }
}
