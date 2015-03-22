package com.yahoo.alioth865.redegalfinal.Modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by AliothAntonio on 22/03/2015.
 */
public class CategoriaEspecifica implements Serializable {
    private String name;
    private String id;
    private String descripcion;
    private List<ProductoEspecifico> productoEspecificos;

    public CategoriaEspecifica() {
        this.productoEspecificos = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void addProducto(ProductoEspecifico productoEspecifico){
        this.productoEspecificos.add(productoEspecifico);
    }

    public List<ProductoEspecifico> getProductoEspecificos() {
        return productoEspecificos;
    }

    @Override
    public String toString() {
        return "CategoriaEspecifica{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", productoEspecificos=" + productoEspecificos.size() +
                '}';
    }
}
