package com.yahoo.alioth865.redegalfinal.Vistas;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yahoo.alioth865.redegalfinal.Modelo.EmpleadoEspecifico;
import com.yahoo.alioth865.redegalfinal.Parser.TodosEmployeesParserPull;
import com.yahoo.alioth865.redegalfinal.R;
import com.yahoo.alioth865.redegalfinal.Util.ReadConfig;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;


public class Registro extends ActionBarActivity {
    private Map<String, String> configuracion;
    private Button registrarse;
    private TextView email;
    private TextView contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializarComponente();
    }

    private void inicializarComponente() {
        this.configuracion= ReadConfig.Config(getResources());
        registrarse=(Button)findViewById(R.id.button);
        email=(TextView)findViewById(R.id.editText2);
        contraseña=(TextView)findViewById(R.id.editText);
        final String urlEmpleado="http://"+configuracion.get("shop_url")+"/"+configuracion.get("name_shop")+"/api/employees";
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailString=email.getText().toString().trim();
                String contraseñaString=contraseña.getText().toString();
                String cookieKey=configuracion.get("cookie_key");
                new Hilo().execute(urlEmpleado,emailString,cookieKey+contraseñaString);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
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

    class Hilo extends AsyncTask<String,Void,Void>{
        boolean isUsuarioValido=false;
        List<EmpleadoEspecifico> allEmpleoyee;
        @Override
        protected Void doInBackground(String... params) {
            String url=params[0];
            TodosEmployeesParserPull parser=new TodosEmployeesParserPull(url,configuracion,getApplicationContext());
            allEmpleoyee =parser.parse();
            String usuario=params[1];
            String contraseña=params[2];
            for(EmpleadoEspecifico tmp: allEmpleoyee){
                try {
                    MessageDigest digest = MessageDigest.getInstance("MD5");
                    digest.update(contraseña.getBytes());
                    byte[] messageDigest = digest.digest();
                    String contraseñaencryptada = "";
                    for (int i=0; i<messageDigest.length; i++){
                        contraseñaencryptada+=Integer.toHexString((messageDigest[i]>>4) & 0xf );
                        contraseñaencryptada+= Integer.toHexString(messageDigest[i] & 0xf);
                    }

                    //Log.i("Contraseña Sin Encriptar",contraseña);
                    //Log.i("Contraseña",contraseñaencryptada);
                    if(tmp.getEmail().equals(usuario) && tmp.getContraseña().equals(contraseñaencryptada)){
                        isUsuarioValido=true;
                        break;
                    }
                }catch(Exception e){
                    Log.e("ERROR CON ENCRIPTACION", e.getMessage());
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(isUsuarioValido){
                Intent i= new Intent(Registro.this, Principal.class);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(), "Usuario o contraseña no válidas", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
