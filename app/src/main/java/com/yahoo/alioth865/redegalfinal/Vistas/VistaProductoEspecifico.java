package com.yahoo.alioth865.redegalfinal.Vistas;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.yahoo.alioth865.redegalfinal.Adapter.GalleryAdapter;
import com.yahoo.alioth865.redegalfinal.Modelo.ProductoEspecifico;
import com.yahoo.alioth865.redegalfinal.R;
import com.yahoo.alioth865.redegalfinal.Util.ReadConfig;

import java.util.List;
import java.util.Map;


public class VistaProductoEspecifico extends ActionBarActivity {
    Map<String,String> configuracion;
    TextView nombre;
    TextView codigo;
    TextView referencia;
    TextView descripcion;
    TextView precio;
    ImageView seleccionada;
    Gallery gallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_especifico);
        inicializarComponente();
    }

    private void inicializarComponente() {
        configuracion= ReadConfig.Config(getResources());
        final ProductoEspecifico p=(ProductoEspecifico)getIntent().getParcelableExtra("Producto");
        this.nombre=(TextView)findViewById(R.id.textView14);
        this.codigo=(TextView)findViewById(R.id.textView16);
        this.referencia=(TextView)findViewById(R.id.textView17);
        this.descripcion=(TextView)findViewById(R.id.textView18);
        this.precio=(TextView)findViewById(R.id.textView19);
        this.nombre.setText(p.getNombre());
        this.codigo.setText(p.getId());
        this.referencia.setText(p.getReferencia());
        this.precio.setText(p.getPrecio());
        this.descripcion.setText(p.getDescripcion());
        //RELATIVO A LA IMAGEN
        //TODO ESTO HAY QUE MEJORAR EN CUANTO A ESTÉTICA!!!
        this.seleccionada=(ImageView)findViewById(R.id.imageView);
        gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // BitmapDrawable bitmapDrawable=(BitmapDrawable)p.getImagen(configuracion).get(position);
                /*Bitmap bm = Bitmap.createScaledBitmap(bitmapDrawable.getBitmap(),
                        (int) (bitmapDrawable.getIntrinsicHeight() * 1.0),
                        (int) (bitmapDrawable.getIntrinsicWidth() * 1.0), false);*/
                Bitmap bm=p.getImagen(configuracion).get(position);
                seleccionada.setImageBitmap(bm);
                seleccionada.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        });
        new Hilo().execute(p);
        /*
        //TODO MIRAR ESTA MIERDA
        List<Bitmap> imagenes=p.getImagen(configuracion);
        gallery.setAdapter(new GalleryAdapter(getApplicationContext(),imagenes));*/



    }


    class Hilo extends AsyncTask<ProductoEspecifico, Void,Void>{
        ProductoEspecifico p;
        @Override
        protected Void doInBackground(ProductoEspecifico... params) {
            p=params[0];
            p.getImagen(configuracion);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            List<Bitmap> imagenes=p.getImagen(configuracion);
            gallery.setAdapter(new GalleryAdapter(getApplicationContext(),imagenes));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_producto_especifico, menu);
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
