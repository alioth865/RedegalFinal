package com.yahoo.alioth865.redegalfinal.Parser;

import android.util.Xml;

import com.yahoo.alioth865.redegalfinal.Modelo.CategoriaEspecifica;
import com.yahoo.alioth865.redegalfinal.Modelo.ProductoEspecifico;

import org.apache.commons.codec.binary.Base64;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by AliothAntonio on 22/03/2015.
 */
public class CategoriaEspecificaParserPull {
    private URL url;
    private String xml;
    private Map<String, String> configuracion;
    private String lenguaje;

    public CategoriaEspecificaParserPull(String url, Map<String, String> configuracion) {
        try {
            this.url = new URL(url);
            this.configuracion = configuracion;
            lenguaje=configuracion.get("id_language");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public CategoriaEspecifica parse(){
        CategoriaEspecifica categoriaEspecifica=new CategoriaEspecifica();
        boolean leerNombre=false;
        boolean leerDescripcion=false;
        boolean leerId=true;
        XmlPullParser parser= Xml.newPullParser();
        try {
            parser.setInput(this.getInputStream(), null);
            int evento=parser.getEventType();
            while(evento!=XmlPullParser.END_DOCUMENT){
                String etiqueta=null;
                switch (evento){
                    case XmlPullParser.START_TAG:
                        etiqueta=parser.getName();
                        if(etiqueta.equals("id")&&leerId){
                            leerId=false;
                            categoriaEspecifica.setId(parser.nextText());
                        }else if(etiqueta.equals("name")){
                            leerNombre=true;
                        }else if(etiqueta.equals("description")){
                            leerDescripcion=true;
                        }else if(etiqueta.equals("product")){
                            String url=parser.getAttributeValue(null, "href");
                            ProductoParserPull productoParserPull=new ProductoParserPull(url,configuracion);
                            ProductoEspecifico producto=productoParserPull.parse();
                            categoriaEspecifica.addProducto(producto);
                        }else if(etiqueta.equals("language")){
                            if(parser.getAttributeValue(null,"id").equals(lenguaje)){
                                if(leerNombre){
                                    categoriaEspecifica.setName(parser.nextText());
                                }else if(leerDescripcion){
                                    categoriaEspecifica.setDescripcion(parser.nextText());
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        etiqueta=parser.getName();
                        if(etiqueta.equals("name")){
                            leerNombre=false;
                        }else if(etiqueta.equals("description")){
                            leerDescripcion=false;
                        }
                        break;
                }
                evento=parser.next();
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return categoriaEspecifica;
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
