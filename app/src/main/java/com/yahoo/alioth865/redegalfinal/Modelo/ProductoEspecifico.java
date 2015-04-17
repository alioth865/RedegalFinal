package com.yahoo.alioth865.redegalfinal.Modelo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import com.yahoo.alioth865.redegalfinal.Parser.ImagenReader;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by AliothAntonio on 21/03/2015.
 */
public class ProductoEspecifico implements Parcelable{
    private String nombre;
    private String id;
    private String referencia;
    private List<Bitmap> imagen;
    private List<String> urlImagen;
    private String descripcion;
    private String precio;
    private boolean isReadyImage=false;

    public static final Creator<ProductoEspecifico> CREATOR =new Creator<ProductoEspecifico>() {
        @Override
        public ProductoEspecifico createFromParcel(Parcel source) {
            return new ProductoEspecifico(source);
        }

        @Override
        public ProductoEspecifico[] newArray(int size) {
            return new ProductoEspecifico[size];
        }
    };

    public ProductoEspecifico() {
        this.imagen = new LinkedList<>();
        this.urlImagen=new LinkedList<>();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(id);
        dest.writeString(referencia);
        dest.writeTypedList(imagen);
        dest.writeStringList(urlImagen);
        dest.writeString(descripcion);
        dest.writeString(precio);
        dest.writeBooleanArray(new boolean[]{isReadyImage});
    }

    public ProductoEspecifico(Parcel in) {
        this.imagen = new LinkedList<>();
        this.urlImagen=new LinkedList<>();
        readFromParcel(in);
    }




    private void readFromParcel(Parcel in) {
        this.nombre=in.readString();
        this.id=in.readString();
        this.referencia=in.readString();
        in.readTypedList(this.imagen,Bitmap.CREATOR);
        in.readStringList(this.urlImagen);
        this.descripcion=in.readString();
        this.precio=in.readString();
        boolean[] temp = new boolean[1];
        in.readBooleanArray(temp);
        this.isReadyImage = temp[0];
    }


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

    public List<Bitmap> getImagen(Map<String, String> configuracion) {
        if(!isReadyImage){
            for(String url:urlImagen){
                ImagenReader ir=new ImagenReader(url,configuracion);
                this.imagen.add(ir.getImagen());
            }
            isReadyImage=true;
        }
        return imagen;
    }

    public void addImagen(String urlImagen) {
        this.urlImagen.add(urlImagen);
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
