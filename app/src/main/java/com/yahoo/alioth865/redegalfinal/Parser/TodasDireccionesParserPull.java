package com.yahoo.alioth865.redegalfinal.Parser;

import android.util.Xml;

import com.yahoo.alioth865.redegalfinal.Modelo.DireccionEspecifica;

import org.apache.commons.codec.binary.Base64;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Alioth on 16/04/2015.
 */
public class TodasDireccionesParserPull {
    private URL url;
    private String xml;
    private Map<String, String> configuracion;

    public TodasDireccionesParserPull(String url, Map<String, String> configuracion) {
        try {
            this.url = new URL(url);
            this.configuracion=configuracion;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Map<String,List<DireccionEspecifica>> parse(){
        Map<String,List<DireccionEspecifica>> todasDirecciones=new HashMap<>();
        XmlPullParser parser= Xml.newPullParser();
        try {
            parser.setInput(this.getInputStream(), null);
            int evento=parser.getEventType();
            while(evento!=XmlPullParser.END_DOCUMENT){
                String etiqueta=null;
                switch (evento){
                    case XmlPullParser.START_TAG:
                        etiqueta=parser.getName();
                        if(etiqueta.equals("address")){
                            String urlDireccionEspecifica=parser.getAttributeValue(null,"href");
                            DireccionEspecificaParserPull parserEspecifico=new DireccionEspecificaParserPull(urlDireccionEspecifica,configuracion);
                            DireccionEspecifica direccionEspecifica=parserEspecifico.parse();
                            if(!direccionEspecifica.getId_customer().equals("0")){
                                if(todasDirecciones.get(direccionEspecifica.getId_customer())==null){
                                    List<DireccionEspecifica> list=new LinkedList<>();
                                    list.add(direccionEspecifica);
                                    todasDirecciones.put(direccionEspecifica.getId_customer(), list);
                                }else{
                                    todasDirecciones.get(direccionEspecifica.getId_customer()).add(direccionEspecifica);
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                }
                evento=parser.next();

            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return todasDirecciones;
    }

    private InputStream getInputStream()
    {

        // CON LA AYUDA DE
        // http://www.avajava.com/tutorials/lessons/how-do-i-connect-to-a-url-using-basic-authentication.html?page=1
        try
        {
            String name = configuracion.get("key");
            String password = "";
            String authString = name + ":" + password;
            byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
            String authStringEnc = new String(authEncBytes);
            URLConnection urlConnection=url.openConnection();
            //urlConnection.setConnectTimeout(5000);
            urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
            return urlConnection.getInputStream();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
