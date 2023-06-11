package com.jmBurger.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private int idProveedor;
    
    @Column(name ="nombre_proveedor")
    private String nombreProveedor;

    @Column(name ="direccion")
    private String direccion;

    @Column(name ="telefono")
    private String telefono;

    @Column

}
