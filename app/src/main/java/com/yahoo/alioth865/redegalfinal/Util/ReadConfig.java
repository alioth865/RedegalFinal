package com.yahoo.alioth865.redegalfinal.Util;

import android.content.res.Resources;
import android.util.Log;

import com.yahoo.alioth865.redegalfinal.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alioth on 16/03/2015.
 */
public class ReadConfig {
    public static Map<String, String> Config(Resources r){
        Map<String,String> m=new HashMap<>();
        try
        {
            InputStreamReader isr = new InputStreamReader(r.openRawResource(R.raw.info));
            BufferedReader br = new BufferedReader(isr);
            String linea;
            while ((linea=br.readLine())!=null){
                String[] parts=linea.split(":");
                m.put(parts[0],parts[1]);
            }
            br.close();
            isr.close();
        }
        catch (IOException ex){
            Log.e("Ficheros", "Error al leer fichero desde recurso raw");
            return null;
        }
        return m;
    }
}
