package com.yahoo.alioth865.redegalfinal.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yahoo.alioth865.redegalfinal.Modelo.ProductoEspecifico;
import com.yahoo.alioth865.redegalfinal.R;

import java.util.List;

/**
 * Created by Alioth on 17/04/2015.
 */
public class AdapterCesta extends BaseAdapter {
    List<ProductoEspecifico> productoEspecificos;
    Activity actividad;

    public AdapterCesta(List<ProductoEspecifico> productoEspecificos, Activity actividad) {
        this.actividad=actividad;
        this.productoEspecificos = productoEspecificos;
    }

    @Override
    public int getCount() {
        return productoEspecificos.size();
    }

    @Override
    public Object getItem(int position) {
        return productoEspecificos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(productoEspecificos.get(position).getId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_cesta, null, true);
        TextView tvNombre =(TextView)view.findViewById(R.id.textView30);
        TextView tvCantidad =(TextView)view.findViewById(R.id.textView32);
        tvNombre.setText(productoEspecificos.get(position).getNombre());
        tvCantidad.setText(productoEspecificos.get(position).getReferencia());
        return view;
    }
}
