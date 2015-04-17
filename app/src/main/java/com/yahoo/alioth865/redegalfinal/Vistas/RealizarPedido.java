package com.yahoo.alioth865.redegalfinal.Vistas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yahoo.alioth865.redegalfinal.Modelo.ClienteEspecifico;
import com.yahoo.alioth865.redegalfinal.Modelo.DireccionEspecifica;
import com.yahoo.alioth865.redegalfinal.Modelo.ProductoEspecifico;
import com.yahoo.alioth865.redegalfinal.R;

import java.util.LinkedList;
import java.util.List;


public class RealizarPedido extends ActionBarActivity {
    Spinner spinnerEntrega;
    Spinner spinnerFactura;
    DireccionEspecifica entrega;
    DireccionEspecifica factura;
    Button buscarProducto;
    ListView listViewCesta;
    List<ProductoEspecifico> cesta=new LinkedList<>();
    TextView nombreCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_pedido);
        inicializarComponente();
    }

    private void inicializarComponente() {
        spinnerEntrega=(Spinner) findViewById(R.id.spinner2);
        spinnerFactura=(Spinner) findViewById(R.id.spinner);
        List<DireccionEspecifica> direccionEspecificas=(List<DireccionEspecifica> )getIntent().getExtras().get("Direcciones");
        ArrayAdapter<DireccionEspecifica> adapter=new ArrayAdapter<DireccionEspecifica>(this,android.R.layout.simple_spinner_item,direccionEspecificas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEntrega.setAdapter(adapter);
        spinnerEntrega.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                entrega=((DireccionEspecifica)parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerFactura.setAdapter(adapter);
        spinnerFactura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                factura = ((DireccionEspecifica) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ClienteEspecifico cliente=(ClienteEspecifico)getIntent().getExtras().getSerializable("Cliente");
        nombreCliente=(TextView)findViewById(R.id.textView26);
        nombreCliente.setText(cliente.getApellido()+", "+cliente.getNombre());
        //TODO HACER EL ADAPTADOR DE ESTE LISTVIEW Y AÑADIRSELO
        listViewCesta=(ListView)findViewById(R.id.listView4);

        //TODO PROGRAMAR ESTE BOTON
        buscarProducto=(Button)findViewById(R.id.button4);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_realizar_pedido, menu);
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
