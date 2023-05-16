package com.jmBurger.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
	
    // Constructores, getters y setters

    public Producto() {
    }

    // Constructor sin la clave primaria "idProducto"
    public Producto(String nombreProducto, String descripcion, double precio, int stockActual,
                    int stockMinimo, int stockMaximo, Timestamp fechaRegistro, Categoria categoria) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.fechaRegistro = fechaRegistro;
        this.categoria = categoria;
    }

    // Getters y setters

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(int stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}