package com.yahoo.alioth865.redegalfinal.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by AliothAntonio on 22/03/2015.
 */
public class CategoriaEspecifica implements Parcelable {
    private String name;
    private String id;
    private String descripcion;
    private List<ProductoEspecifico> productoEspecificos;

    public CategoriaEspecifica() {
        this.productoEspecificos = new LinkedList<>();
    }

    public CategoriaEspecifica(Parcel in) {
        this.productoEspecificos = new LinkedList<>();
        readFromParcel(in);
    }

    public static final Creator<CategoriaEspecifica> CREATOR = new Creator<CategoriaEspecifica>() {
        @Override
        public CategoriaEspecifica createFromParcel(Parcel source) {
            return new CategoriaEspecifica(source);
        }

        @Override
        public CategoriaEspecifica[] newArray(int size) {
            return new CategoriaEspecifica[0];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(descripcion);
        dest.writeTypedList(productoEspecificos);
    }


    public void readFromParcel(Parcel in){
        this.name=in.readString();
        this.id=in.readString();
        this.descripcion=in.readString();
        in.readTypedList(productoEspecificos, ProductoEspecifico.CREATOR);
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
