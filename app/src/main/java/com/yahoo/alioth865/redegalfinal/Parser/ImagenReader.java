package com.yahoo.alioth865.redegalfinal.Parser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.commons.codec.binary.Base64;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by Alioth on 24/03/2015.
 */
public class ImagenReader {
    private URL url;
    private String xml;
    private Map<String, String> configuracion;
    private String lenguaje;

    public ImagenReader(String url, Map<String, String> configuracion) {
        try {
            this.url = new URL(url);
            this.configuracion = configuracion;
            lenguaje=configuracion.get("id_language");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Bitmap getImagen(){
        Log.i("URL IMAGEN", url.toString());
        Bitmap imagen= BitmapFactory.decodeStream(getInputStream());
        return imagen;
    }



    private InputStream getInputStream()
    {
        try
        {
            String name = configuracion.get("key");
            String password = "";
            String authString = name + ":" + password;
            byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
            String authStringEnc = new String(authEncBytes);
            URLConnection urlConnection=url.openConnection();
            urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
            return urlConnection.getInputStream();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
