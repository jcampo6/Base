package com.example.usuario.reservas;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;


public class Fragment1 extends android.support.v4.app.Fragment {

    private EditText nombre;
    private EditText ubicacion;
    private TextView path;
    private ProgressDialog dialog;
    private Context contexto;
    private String id_usu = "";

    @Override
    public View onCreateView(
        LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.activity_fragment1, container, false);
        nombre = (EditText) v.findViewById(R.id.txt_EscNom);
        ubicacion = (EditText) v.findViewById(R.id.txt_ubicacion);
        path = (TextView) v.findViewById(R.id.lbl_path);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contexto = view.getContext();
        cargarConfiguracion();
    }

    //cargar configuración aplicación Android usando SharedPreferences
    public void cargarConfiguracion()
    {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("Config", Context.MODE_PRIVATE);
        id_usu = prefs.getString("id","");
        Toast.makeText(contexto,id_usu,Toast.LENGTH_SHORT).show();
    }


    private class TareaAsincrona extends AsyncTask<String, Float, String> {

        protected void onPreExecute() {
            dialog = new ProgressDialog(contexto);
            dialog.setMessage("Cargando...");
            dialog.setTitle("Progreso");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setCancelable(false);
            dialog.setProgress(0);
            dialog.setMax(100);
            dialog.show(); //Mostramos el diálogo antes de comenzar
        }

        protected String doInBackground(String... params) {
            /**
             * Simularemos que descargamos un fichero
             * mediante un sleep

             for (int i = 0; i < 100; i++) {
             //Simulamos cierto retraso
             try {Thread.sleep(20); }
             catch (InterruptedException e) {}
             publishProgress(i/100f); //Actualizamos los valores
             }*/
            String nom = "", ubi = "", img = "";
            nom = params[0];
            ubi = params[1];
            img = params[2];
            StrictMode.enableDefaults();
            EnvioPost env = new EnvioPost();
            String txt = env.postEscenarios("0","2","http://108.163.177.85/clase/jcampo6/app.php",nom,ubi,img,id_usu);
            return txt;
        }

        protected void onProgressUpdate (Float... valores) {
            int p = Math.round(100*valores[0]);
            dialog.setProgress(p);
        }

        protected void onPostExecute(String result){
            if(!result.equalsIgnoreCase("")){
                Bundle b = new Bundle();
                ArrayList<Escenario> lista = new ArrayList<>();
                org.json.JSONObject ja = null;
                try {
                    ja = new org.json.JSONObject(result);
                    Escenario u;
                    //byte[] img =
                    //u = new Escenario(ja.getString("nombre"),ja.getString("ubicacion"),ja.getString("imagen"))
                   // lista.add(u);
                    b.putString("id_usuario",ja.getString("id_usuarios"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.cancel();
                dialog = null;
            }else{
                dialog.cancel();
                dialog = null;
                Toast.makeText(contexto, "Datos Invalidos", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
