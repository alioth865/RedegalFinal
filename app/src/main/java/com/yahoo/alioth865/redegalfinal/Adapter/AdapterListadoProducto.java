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
 * Created by AliothAntonio on 22/03/2015.
 */
public class AdapterListadoProducto extends BaseAdapter{
    List<ProductoEspecifico> productoEspecificos;
    Activity actividad;

    public AdapterListadoProducto(List<ProductoEspecifico> productoEspecificos, Activity actividad) {
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

        View view = inflater.inflate(R.layout.item_producto, null, true);
        TextView tvNombre =(TextView)view.findViewById(R.id.textView12);
        TextView tvReferencia =(TextView)view.findViewById(R.id.textView13);
        tvNombre.setText(productoEspecificos.get(position).getNombre());
        tvReferencia.setText(productoEspecificos.get(position).getReferencia());
        return view;
    }
}
