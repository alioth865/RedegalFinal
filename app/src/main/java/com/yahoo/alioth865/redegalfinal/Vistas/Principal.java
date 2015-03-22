package com.yahoo.alioth865.redegalfinal.Vistas;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.yahoo.alioth865.redegalfinal.Modelo.CategoriaEspecifica;
import com.yahoo.alioth865.redegalfinal.Parser.TodasCategoriasParsePull;
import com.yahoo.alioth865.redegalfinal.R;
import com.yahoo.alioth865.redegalfinal.Util.ReadConfig;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Principal extends ActionBarActivity {
    Button producto;
    Map<String,String> configuracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        configuracion= ReadConfig.Config(getResources());
        producto=(Button)findViewById(R.id.button2);
        producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://"+configuracion.get("shop_url")+"/"+configuracion.get("name_shop")+"/api/categories";
                new Hilo().execute(url);
            }
        });

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

    class Hilo extends AsyncTask<String, Void, Void>{
        List<CategoriaEspecifica> todasCategorias=new LinkedList<>();
        @Override
        protected Void doInBackground(String... params) {
            String url=params[0];
            TodasCategoriasParsePull todasCategoriasParsePull=new TodasCategoriasParsePull(url,configuracion);
            todasCategorias=todasCategoriasParsePull.parse();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent i=new Intent(Principal.this,Categorias.class);
            i.putExtra("Categorias", (java.io.Serializable) todasCategorias);
            startActivity(i);
        }
    }
}
