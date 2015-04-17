package com.yahoo.alioth865.redegalfinal.Parser;

import android.util.Xml;

import com.yahoo.alioth865.redegalfinal.Modelo.DireccionEspecifica;

import org.apache.commons.codec.binary.Base64;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by Alioth on 16/04/2015.
 */
public class DireccionEspecificaParserPull {
    private URL url;
    private String xml;
    private Map<String, String> configuracion;

    public DireccionEspecificaParserPull(String url, Map<String, String> configuracion) {
        try {
            this.url = new URL(url);
            this.configuracion=configuracion;
        }catch (MalformedURLException ex){
            throw new RuntimeException(ex);
        }
    }

    public DireccionEspecifica parse(){
        XmlPullParser parser = Xml.newPullParser();
        DireccionEspecifica direccionEspecifica=new DireccionEspecifica();
        try {
            parser.setInput(this.getInputStream(), null);
            int evento=parser.getEventType();
            while(evento!=XmlPullParser.END_DOCUMENT){
                String etiqueta=null;
                switch (evento){
                    case XmlPullParser.START_TAG:
                        etiqueta=parser.getName();
                        if(etiqueta.equals("id")){
                            direccionEspecifica.setId(parser.nextText());
                        }else if(etiqueta.equals("id_customer")){
                            direccionEspecifica.setId_customer(parser.nextText());
                        }else if(etiqueta.equals("address1")){
                            direccionEspecifica.setAddress1(parser.nextText());
                        }else if(etiqueta.equals("address2")){
                            direccionEspecifica.setAddress2(parser.nextText());
                        }else if(etiqueta.equals("postcode")){
                            direccionEspecifica.setPostcode(parser.nextText());
                        }else if(etiqueta.equals("city")){
                            direccionEspecifica.setCity(parser.nextText());
                        }
                        break;
                }
                evento=parser.next();
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return direccionEspecifica;

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
