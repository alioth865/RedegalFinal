package com.yahoo.alioth865.redegalfinal.Vistas;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yahoo.alioth865.redegalfinal.Adapter.AdapterTodasCategorias;
import com.yahoo.alioth865.redegalfinal.Modelo.CategoriaEspecifica;
import com.yahoo.alioth865.redegalfinal.R;

import java.io.Serializable;
import java.util.List;


public class Categorias extends ActionBarActivity {
    ListView todasCategorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        inicializarComponente();
    }

    private void inicializarComponente() {
        List<CategoriaEspecifica> list=(List<CategoriaEspecifica>)getIntent().getExtras().get("Categorias");
        todasCategorias=(ListView)findViewById(R.id.listView);
        todasCategorias.setAdapter(new AdapterTodasCategorias(list,this));
        todasCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(Categorias.this, Productos.class);
                i.putExtra("Productos", (Serializable) ((CategoriaEspecifica)parent.getAdapter().getItem(position)).getProductoEspecificos());
                startActivity(i);
                //Toast.makeText(getApplicationContext(), "You clicked on position : " + position + " and id : " + ((CategoriaEspecifica)parent.getAdapter().getItem(position)).getDescripcion(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categorias, menu);
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
