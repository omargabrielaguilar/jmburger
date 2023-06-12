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
}
