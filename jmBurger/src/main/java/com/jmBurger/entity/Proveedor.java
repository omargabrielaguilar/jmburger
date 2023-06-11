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

    @Column(name = "precio")
    private double precio;

    @Column(name ="correo_electronico")
    private String correoElectronico;

    @Column(name = "sitio_web")
    private String sitioWeb;

    @Column(name ="descripcion")
    private String descripcion;

    public Proveedor(){}


    public Proveedor(int idProveedor, String nombreProveedor, String direccion, String telefono, double precio, String correoElectronico, String sitioWeb, String descripcion) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.direccion = direccion;
        this.telefono = telefono;
        this.precio = precio;
        this.correoElectronico = correoElectronico;
        this.sitioWeb = sitioWeb;
        this.descripcion = descripcion;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
