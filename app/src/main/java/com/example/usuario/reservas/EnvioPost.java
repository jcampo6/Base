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
    public String post(String url,String sql){

        try{
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("sql",sql));

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
