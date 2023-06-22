package com.jmBurger.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_produccion")
public class DetalleProduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_detalle_produccion")
    private int idDetalleProduccion;

    @ManyToOne
    @JoinColumn(name = "id_produccion")
    private Produccion produccion;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;


    public DetalleProduccion(){}

    public DetalleProduccion(int idDetalleProduccion, Produccion produccion, Producto producto) {
        this.idDetalleProduccion = idDetalleProduccion;
        this.produccion = produccion;
        this.producto = producto;
    }

    public int getIdDetalleProduccion() {
        return idDetalleProduccion;
    }

    public void setIdDetalleProduccion(int idDetalleProduccion) {
        this.idDetalleProduccion = idDetalleProduccion;
    }

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
