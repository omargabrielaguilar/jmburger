package com.jmBurger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private double precio;

    @Column(name = "stock_actual")
    private int stockActual;

    @Column(name = "stock_minimo")
    private int stockMinimo;

    @Column(name = "stock_maximo")
    private int stockMaximo;

    @Column(name = "fecharegistro")
    private Timestamp fechaRegistro;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
	
	
}