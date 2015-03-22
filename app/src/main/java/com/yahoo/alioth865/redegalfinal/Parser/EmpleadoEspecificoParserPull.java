package com.yahoo.alioth865.redegalfinal.Parser;

import android.util.Log;
import android.util.Xml;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.yahoo.alioth865.redegalfinal.Modelo.EmpleadoEspecifico;

import org.apache.http.Header;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.xmlpull.v1.XmlPullParser;

/**
 * Created by AliothAntonio on 21/03/2015.
 */
public class EmpleadoEspecificoParserPull {
    private URL url;
    private String xml;
    private Map<String, String> configuracion;

    public EmpleadoEspecificoParserPull(String url, Map<String, String> configuracion) {
        try {
            this.url = new URL(url);
            this.configuracion=configuracion;
        }catch (MalformedURLException ex){
            throw new RuntimeException(ex);
        }
    }

    public EmpleadoEspecifico parse(){
        XmlPullParser parser = Xml.newPullParser();
        EmpleadoEspecifico empleadoEspecifico=new EmpleadoEspecifico();
        try {
            parser.setInput(this.getInputStream(), null);
            int evento=parser.getEventType();
            while(evento!=XmlPullParser.END_DOCUMENT){
                String etiqueta=null;
                switch (evento){
                    case XmlPullParser.START_TAG:
                        etiqueta=parser.getName();
                        if(etiqueta.equals("id")){
                            empleadoEspecifico.setId(parser.nextText());
                        }else if(etiqueta.equals("firstname")){
                            empleadoEspecifico.setNombre(parser.nextText());
                        }else if(etiqueta.equals("lastname")){
                            empleadoEspecifico.setApellido(parser.nextText());
                        }else if(etiqueta.equals("email")){
                            empleadoEspecifico.setEmail(parser.nextText());
                        }else if(etiqueta.equals("passwd")){
                            empleadoEspecifico.setContraseña(parser.nextText());
                        }
                        break;
                }
                evento=parser.next();
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return empleadoEspecifico;

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
