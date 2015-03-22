package com.yahoo.alioth865.redegalfinal.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yahoo.alioth865.redegalfinal.Modelo.EmpleadoEspecifico;
import com.yahoo.alioth865.redegalfinal.R;

import java.util.List;

/**
 * Created by AliothAntonio on 21/03/2015.
 */
public class AdapterTodosEmpleados extends BaseAdapter {
    List<EmpleadoEspecifico> listaEmpleado;
    Activity actividad;

    public AdapterTodosEmpleados(List<EmpleadoEspecifico> listaEmpleado, Activity actividad) {
        this.actividad=actividad;
        this.listaEmpleado = listaEmpleado;
    }

    @Override
    public int getCount() {
        return listaEmpleado.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEmpleado.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_empleado, null, true);
        TextView tvNombre =(TextView)view.findViewById(R.id.textView);
        TextView tvApellido =(TextView)view.findViewById(R.id.textView2);
        TextView tvContraseña =(TextView)view.findViewById(R.id.textView3);
        TextView tvEmail =(TextView)view.findViewById(R.id.textView4);
        TextView tvID =(TextView)view.findViewById(R.id.textView5);

        tvNombre.setText(listaEmpleado.get(position).getNombre());
        tvApellido.setText(listaEmpleado.get(position).getApellido());
        tvContraseña.setText(listaEmpleado.get(position).getContraseña());
        tvEmail.setText(listaEmpleado.get(position).getEmail());
        tvID.setText(listaEmpleado.get(position).getId());
        return view;
    }
}
