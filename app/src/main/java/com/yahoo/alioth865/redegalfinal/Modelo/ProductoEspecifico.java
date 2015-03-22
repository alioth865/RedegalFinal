package com.yahoo.alioth865.redegalfinal.Modelo;

import java.io.Serializable;

/**
 * Created by AliothAntonio on 21/03/2015.
 */
public class ProductoEspecifico implements Serializable{
    private String nombre;
    private String id;
    private String referencia;
    //TODO HACER CORRECTAMENTE LO DE LA IMAGEN
    private String imagen;
    private String descripcion;
    private String precio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ProductoEspecifico{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", referencia='" + referencia + '\'' +
                ", imagen='" + imagen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }
}
