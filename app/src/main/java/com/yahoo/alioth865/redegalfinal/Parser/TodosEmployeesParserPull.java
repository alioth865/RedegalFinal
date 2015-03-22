package com.yahoo.alioth865.redegalfinal.Parser;

import android.util.Log;
import android.util.Xml;

import com.yahoo.alioth865.redegalfinal.Modelo.EmpleadoEspecifico;

import org.apache.commons.codec.binary.Base64;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by AliothAntonio on 21/03/2015.
 */
public class TodosEmployeesParserPull {
    private URL url;
    private String xml;
    private Map<String, String> configuracion;

    public TodosEmployeesParserPull(String url, Map<String, String> configuracion) {
        try {
            this.url = new URL(url);
            this.configuracion=configuracion;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public List<EmpleadoEspecifico> parse(){
        List<EmpleadoEspecifico> empleadoEspecificos=new LinkedList<>();
        XmlPullParser parser= Xml.newPullParser();
        try {
            parser.setInput(this.getInputStream(), null);
            int evento=parser.getEventType();
            while(evento!=XmlPullParser.END_DOCUMENT){
                String etiqueta=null;
                switch (evento){
                    case XmlPullParser.START_TAG:
                        etiqueta=parser.getName();
                        if(etiqueta.equals("employee")){
                            String urlEmpleadoEspecifico=parser.getAttributeValue(null,"href");
                            EmpleadoEspecificoParserPull parserEspecifico=new EmpleadoEspecificoParserPull(urlEmpleadoEspecifico,configuracion);
                            empleadoEspecificos.add(parserEspecifico.parse());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                }
                evento=parser.next();
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return empleadoEspecificos;
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
            urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
            return urlConnection.getInputStream();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
