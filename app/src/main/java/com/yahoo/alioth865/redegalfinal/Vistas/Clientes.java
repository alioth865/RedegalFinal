package com.yahoo.alioth865.redegalfinal.Vistas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.yahoo.alioth865.redegalfinal.Adapter.AdapterTodosClientes;
import com.yahoo.alioth865.redegalfinal.Adapter.AdapterTodosEmpleados;
import com.yahoo.alioth865.redegalfinal.Modelo.CategoriaEspecifica;
import com.yahoo.alioth865.redegalfinal.Modelo.ClienteEspecifico;
import com.yahoo.alioth865.redegalfinal.Modelo.DireccionEspecifica;
import com.yahoo.alioth865.redegalfinal.R;

import java.util.List;
import java.util.Map;


public class Clientes extends ActionBarActivity {
    ListView lvClientes;
    List<CategoriaEspecifica> categorias;
    Map<String, List<DireccionEspecifica>> direcciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        inicializarComponente();
    }

    private void inicializarComponente() {
        lvClientes=(ListView)findViewById(R.id.listView3);
        List<ClienteEspecifico> cliente=(List<ClienteEspecifico>)getIntent().getExtras().getSerializable("Clientes");
        categorias=(List<CategoriaEspecifica>)getIntent().getExtras().getSerializable("Categorias");
        direcciones=(Map<String, List<DireccionEspecifica>>)getIntent().getExtras().getSerializable("Direcciones");
                lvClientes.setAdapter(new AdapterTodosClientes(cliente, this, categorias, direcciones));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clientes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
