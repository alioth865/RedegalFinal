package com.yahoo.alioth865.redegalfinal.Vistas;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.yahoo.alioth865.redegalfinal.Modelo.CategoriaEspecifica;
import com.yahoo.alioth865.redegalfinal.Modelo.ClienteEspecifico;
import com.yahoo.alioth865.redegalfinal.Modelo.DireccionEspecifica;
import com.yahoo.alioth865.redegalfinal.Parser.TodasCategoriasParsePull;
import com.yahoo.alioth865.redegalfinal.Parser.TodasDireccionesParserPull;
import com.yahoo.alioth865.redegalfinal.Parser.TodosClientesParserPull;
import com.yahoo.alioth865.redegalfinal.R;
import com.yahoo.alioth865.redegalfinal.Util.ReadConfig;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Principal extends ActionBarActivity {
    Button producto;
    Button cliente;
    boolean terminoCliente=false;
    boolean terminoProducto=false;
    boolean terminoDirecciones=false;
    ProgressBar progressBar;
    List<ClienteEspecifico> todosClientes=new LinkedList<>();
    List<CategoriaEspecifica> todasCategorias=new LinkedList<>();
    Map<String, List<DireccionEspecifica>> todasDirecciones=new HashMap<>();
    Map<String,String> configuracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /********************************************************/
        if(savedInstanceState!=null){
            terminoCliente=savedInstanceState.getBoolean("terminoCliente");
            terminoProducto=savedInstanceState.getBoolean("terminoProducto");
            terminoDirecciones=savedInstanceState.getBoolean("terminoDirecciones");
            todosClientes=(List<ClienteEspecifico>)savedInstanceState.getSerializable("Cliente");
            todasCategorias=(List<CategoriaEspecifica>) savedInstanceState.getSerializable("Categoria");
            todasDirecciones=(Map<String, List<DireccionEspecifica>>) savedInstanceState.getSerializable("Direcciones");
        }
        /********************************************************/
        setContentView(R.layout.activity_principal);
        inicializarComponentes();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void inicializarComponentes() {
        producto=(Button)findViewById(R.id.button2);
        cliente=(Button)findViewById(R.id.button3);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        configuracion= ReadConfig.Config(getResources());
        String urlCliente="http://"+configuracion.get("shop_url")+"/"+configuracion.get("name_shop")+"/api/customers";
        if(!terminoCliente)
            new HiloCliente().execute(urlCliente);
        String urlProducto="http://"+configuracion.get("shop_url")+"/"+configuracion.get("name_shop")+"/api/categories";
        if(!terminoProducto)
            new HiloCategorias().execute(urlProducto);
        String urlDirecciones="http://"+configuracion.get("shop_url")+"/"+configuracion.get("name_shop")+"/api/addresses";
        if(!terminoDirecciones)
            new HiloDirecciones().execute(urlDirecciones);
        if(terminoProducto&&terminoCliente&&terminoDirecciones){
            progressBar.setVisibility(View.INVISIBLE);
            producto.setEnabled(true);
            cliente.setEnabled(true);
        }

        producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Principal.this,Categorias.class);
                i.putExtra("Categorias", (java.io.Serializable) todasCategorias);
                startActivity(i);
            }
        });
        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Principal.this,Clientes.class);
                i.putExtra("Clientes", (java.io.Serializable) todosClientes);
                i.putExtra("Categorias", (java.io.Serializable) todasCategorias);
                i.putExtra("Direcciones", (java.io.Serializable) todasDirecciones);
                startActivity(i);
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(terminoCliente){
            outState.putSerializable("Cliente", (java.io.Serializable) todosClientes);
        }
        if(terminoProducto){
            outState.putSerializable("Categoria", (java.io.Serializable) todasCategorias);
        }
        if(terminoDirecciones){
            outState.putSerializable("Direcciones", (java.io.Serializable) todasDirecciones);
        }
        outState.putBoolean("terminoCliente",terminoCliente);
        outState.putBoolean("terminoProducto",terminoProducto);
        outState.putBoolean("terminoDirecciones", terminoDirecciones);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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

    @Override
    protected void onPause() {
        super.onPause();
    }

    class HiloCategorias extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            String url=params[0];
            TodasCategoriasParsePull todasCategoriasParsePull=new TodasCategoriasParsePull(url,configuracion);
            todasCategorias=todasCategoriasParsePull.parse();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            terminoProducto=true;
            if(terminoCliente&&terminoDirecciones){
                progressBar.setVisibility(View.INVISIBLE);
                cliente.setEnabled(true);
                producto.setEnabled(true);
            }
        }
    }
    class HiloCliente extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            String url=params[0];
            TodosClientesParserPull todasClientesParsePull=new TodosClientesParserPull(url,configuracion);
            todosClientes=todasClientesParsePull.parse();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            terminoCliente=true;

            if(terminoProducto&&terminoDirecciones){
                progressBar.setVisibility(View.INVISIBLE);
                cliente.setEnabled(true);
                producto.setEnabled(true);
            }
        }
    }

    private class HiloDirecciones extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            String url=params[0];
            TodasDireccionesParserPull todasDireccionesParserPull=new TodasDireccionesParserPull(url,configuracion);
            todasDirecciones=todasDireccionesParserPull.parse();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            terminoDirecciones=true;
            if(terminoProducto&&terminoCliente){
                progressBar.setVisibility(View.INVISIBLE);
                cliente.setEnabled(true);
                producto.setEnabled(true);
            }
        }
    }
}
