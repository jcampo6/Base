package com.example.usuario.reservas;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USUARIO on 14/05/2015.
 */
public class EnvioPost {

    public String postUsuarios(String sql, String url, String ced, String nom, String ape, String ema, String pw, String tel, String na){

        try{
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            switch (sql){
                case "0":
                    params.add(new BasicNameValuePair("sql",sql));
                    params.add(new BasicNameValuePair("cedula",ced));
                    params.add(new BasicNameValuePair("nombre",nom));
                    params.add(new BasicNameValuePair("apellido",ape));
                    params.add(new BasicNameValuePair("email",ema));
                    params.add(new BasicNameValuePair("password",pw));
                    params.add(new BasicNameValuePair("telefono",tel));
                    params.add(new BasicNameValuePair("nivel_admin",na));
                    break;
                case "1":
                    params.add(new BasicNameValuePair("sql",sql));
                    params.add(new BasicNameValuePair("email",ema));
                    params.add(new BasicNameValuePair("password",pw));
                    break;
                default:
            }


            httppost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();

            String respuesta = EntityUtils.toString(ent);
            return respuesta;

        }catch(Exception e){
            return "error: "+e.getMessage();
        }

    }

    public String postEscenarios(String sql, String url, String nom, String ubi, String img, String idUsu){
        try{
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            switch (sql){
                case "0":
                    params.add(new BasicNameValuePair("sql",sql));
                    params.add(new BasicNameValuePair("nombre",nom));
                    params.add(new BasicNameValuePair("ubicacion",ubi));
                    params.add(new BasicNameValuePair("imagen",img));
                    break;
                case "1":
                    params.add(new BasicNameValuePair("sql",sql));
                    params.add(new BasicNameValuePair("id_usuarios",idUsu));
                    break;
                default:
            }


            httppost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();

            String respuesta = EntityUtils.toString(ent);
            return respuesta;

        }catch(Exception e){
            return "error: "+e.getMessage();
        }

    }
}
