package com.yahoo.alioth865.redegalfinal.Vistas;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yahoo.alioth865.redegalfinal.Adapter.AdapterListadoProducto;
import com.yahoo.alioth865.redegalfinal.Modelo.ProductoEspecifico;
import com.yahoo.alioth865.redegalfinal.R;
import com.yahoo.alioth865.redegalfinal.Util.ReadConfig;

import java.util.List;
import java.util.Map;


public class Productos extends ActionBarActivity {
    Map<String,String> configuracion;
    List<ProductoEspecifico> list;
    ListView lvProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        inicializarComponente();
    }

    private void inicializarComponente() {
        configuracion= ReadConfig.Config(getResources());
        list=(List<ProductoEspecifico>)getIntent().getExtras().getSerializable("Productos");
        lvProductos=(ListView)findViewById(R.id.listView2);
        lvProductos.setAdapter(new AdapterListadoProducto(list,this));
        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ProductoEspecifico p=((ProductoEspecifico)parent.getAdapter().getItem(position));
                Intent i=new Intent(Productos.this, VistaProductoEspecifico.class);
                i.putExtra("Producto", p );
                startActivity(i);
       //         Hilo hilo=new Hilo();
       //         hilo.execute(p);

                //Toast.makeText(getApplicationContext(), "You clicked on position : " + position + " and id : " + ((CategoriaEspecifica)parent.getAdapter().getItem(position)).getDescripcion(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_productos, menu);
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

    /*class Hilo extends AsyncTask<ProductoEspecifico, Void,Void>{

        ProductoEspecifico p;



        @Override
        protected Void doInBackground(ProductoEspecifico... params) {
            p=params[0];
            p.getImagen(configuracion);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent i=new Intent(Productos.this, VistaProductoEspecifico.class);
            i.putExtra("Producto", p);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(i);
        }
    }*/
}
