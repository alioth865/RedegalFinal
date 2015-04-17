package com.yahoo.alioth865.redegalfinal.Modelo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alioth on 05/04/2015.
 */
public class ClienteEspecifico implements Serializable{
    private String nombre;
    private String apellido;
    private String email;
    private String id;
    private List<String> direcciones;
    private String ventas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<String> direcciones) {
        this.direcciones = direcciones;
    }

    public String getVentas() {
        return ventas;
    }

    public void setVentas(String ventas) {
        this.ventas = ventas;
    }
}
