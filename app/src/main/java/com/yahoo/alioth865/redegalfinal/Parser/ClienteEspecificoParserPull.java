package com.yahoo.alioth865.redegalfinal.Parser;


import android.util.Xml;

import com.yahoo.alioth865.redegalfinal.Modelo.ClienteEspecifico;

import org.apache.commons.codec.binary.Base64;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by Alioth on 07/04/2015.
 */
public class ClienteEspecificoParserPull {
    private URL url;
    private String xml;
    private Map<String, String> configuracion;

    public ClienteEspecificoParserPull(String url, Map<String, String> configuracion) {
        try {
            this.url = new URL(url);
            this.configuracion=configuracion;
        }catch (MalformedURLException ex){
            throw new RuntimeException(ex);
        }
    }

    public ClienteEspecifico parse(){
        XmlPullParser parser = Xml.newPullParser();
        ClienteEspecifico clienteEspecifico=new ClienteEspecifico();
        boolean readID=false;
        try {
            parser.setInput(this.getInputStream(), null);
            int evento=parser.getEventType();
            while(evento!=XmlPullParser.END_DOCUMENT){
                String etiqueta=null;
                switch (evento){
                    case XmlPullParser.START_TAG:
                        etiqueta=parser.getName();
                        if(etiqueta.equals("id") && !readID){
                            clienteEspecifico.setId(parser.nextText());
                            readID=true;
                        }else if(etiqueta.equals("firstname")){
                            clienteEspecifico.setNombre(parser.nextText());
                        }else if(etiqueta.equals("lastname")){
                            clienteEspecifico.setApellido(parser.nextText());
                        }else if(etiqueta.equals("email")){
                            clienteEspecifico.setEmail(parser.nextText());
                        }
                        break;
                }
                evento=parser.next();
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return clienteEspecifico;

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
