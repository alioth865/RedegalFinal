package com.yahoo.alioth865.redegalfinal.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.yahoo.alioth865.redegalfinal.Modelo.CategoriaEspecifica;
import com.yahoo.alioth865.redegalfinal.R;

import java.util.List;

/**
 * Created by AliothAntonio on 22/03/2015.
 */
public class AdapterTodasCategorias extends BaseAdapter {
    List<CategoriaEspecifica> categoriaEspecificas;
    Activity actividad;

    public AdapterTodasCategorias(List<CategoriaEspecifica> categoriaEspecificas, Activity actividad) {
        this.actividad=actividad;
        this.categoriaEspecificas = categoriaEspecificas;
    }

    @Override
    public int getCount() {
        return categoriaEspecificas.size();
    }

    @Override
    public Object getItem(int position) {
        return categoriaEspecificas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(categoriaEspecificas.get(position).getId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_categoria, null, true);
        TextView tvNombre =(TextView)view.findViewById(R.id.textView6);
        TextView tvID =(TextView)view.findViewById(R.id.textView9);
        tvNombre.setText(categoriaEspecificas.get(position).getName());
        tvID.setText(categoriaEspecificas.get(position).getId());
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(actividad.getApplicationContext(),categoriaEspecificas.get(position).getName(),Toast.LENGTH_SHORT);
            }
        });*/
        return view;
    }


}
