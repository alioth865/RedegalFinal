package com.yahoo.alioth865.redegalfinal.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.yahoo.alioth865.redegalfinal.Modelo.CategoriaEspecifica;
import com.yahoo.alioth865.redegalfinal.Modelo.ClienteEspecifico;
import com.yahoo.alioth865.redegalfinal.Modelo.DireccionEspecifica;
import com.yahoo.alioth865.redegalfinal.R;
import com.yahoo.alioth865.redegalfinal.Vistas.Clientes;
import com.yahoo.alioth865.redegalfinal.Vistas.Pedido;
import com.yahoo.alioth865.redegalfinal.Vistas.RealizarPedido;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Alioth on 07/04/2015.
 */
public class AdapterTodosClientes extends BaseAdapter {
    private List<ClienteEspecifico> clientesList;
    private Activity actividad;
    List<CategoriaEspecifica> categoriaEspecificaList;
    Map<String, List<DireccionEspecifica>> direcciones;
    public AdapterTodosClientes(List<ClienteEspecifico> clientesList, Activity actividad, List<CategoriaEspecifica> categoriaEspecificaList, Map<String, List<DireccionEspecifica>> direcciones) {
        this.clientesList=clientesList;
        this.actividad=actividad;
        this.categoriaEspecificaList=categoriaEspecificaList;
        this.direcciones=direcciones;
    }

    @Override
    public int getCount() {
        return clientesList.size();
    }

    @Override
    public Object getItem(int position) {
        return clientesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(clientesList.get(position).getId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = actividad.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_cliente, null, true);
        TextView tvNombre =(TextView)view.findViewById(R.id.textView22);
        TextView tvApellido =(TextView)view.findViewById(R.id.textView23);
        TextView tvEmail =(TextView)view.findViewById(R.id.textView24);
        tvNombre.setText(clientesList.get(position).getNombre());
        tvApellido.setText(clientesList.get(position).getApellido());
        tvEmail.setText(clientesList.get(position).getEmail());
        ImageButton realizarPedido=(ImageButton)view.findViewById(R.id.imageButton);
        realizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(actividad, RealizarPedido.class);
                i.putExtra("Categorias", (Serializable)categoriaEspecificaList);
                i.putExtra("Cliente", clientesList.get(position));
                i.putExtra("Direcciones", (java.io.Serializable) direcciones.get(clientesList.get(position).getId()));
                actividad.startActivity(i);
            }
        });
        return view;
    }
}
