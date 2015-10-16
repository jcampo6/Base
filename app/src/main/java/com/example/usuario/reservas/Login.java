package com.example.usuario.reservas;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Login extends ActionBarActivity {

    private EditText usuario;
    private EditText pw;
    private ProgressDialog dialog;
    private String cadJson;
    private Resources recursos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (EditText) findViewById(R.id.txt_usuario);
        pw = (EditText) findViewById(R.id.txt_clave);
        recursos = this.getResources();
    }

    @Override
    protected void onStop() {

        super.onStop();

        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }

    }

    public void enviar(View view){
        if(!usuario.getText().toString().trim().equals("") || !pw.getText().toString().trim().equals("") ) {
            new TareaAsincrona().execute(usuario.getText().toString(), pw.getText().toString());
        }else{
            Toast.makeText(this, recursos.getString(R.string.error0),Toast.LENGTH_SHORT).show();
        }
    }

    public void registro(View v){
        Intent i = new Intent(Login.this, Registrar_Usuario.class);
        startActivity(i);
    }

   public void mostrar1Json(String json) throws JSONException {
       //Codigo para traer un registro del json
       ArrayList<Usuario> lista = new ArrayList<>();
       org.json.JSONObject ja = null;
       try {
           ja = new org.json.JSONObject(json);
           Usuario u;
           Log.v("HEYYYY",ja.getString("id_usuarios"));
           u = new Usuario(ja.getString("id_usuarios"),ja.getString("cedula"),ja.getString("nombre"),
                   ja.getString("apellido"),ja.getString("email"),ja.getString("password"),ja.getString("telefono"),
                   ja.getString("nivel_admin"));
           lista.add(u);
           AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
           builder1.setCancelable(true);
           builder1.setMessage(ja.getString("id_usuarios"));
           builder1.show();
       } catch (JSONException e) {
           e.printStackTrace();
       }
   }

    public void mostrarVariosJson(String json){
        JSONArray jarray = null;
        org.json.JSONObject ja = null;
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            jarray = new JSONArray(json);
            Usuario u;
            Log.v("HEYYYY",ja.getString("id_usuarios"));
            for (int i = 0; i < jarray.length(); i++) {
                ja = jarray.getJSONObject(i);
                u = new Usuario(ja.getString("id_usuarios"),ja.getString("cedula"),ja.getString("nombre"),
                        ja.getString("apellido"),ja.getString("email"),ja.getString("password"),ja.getString("telefono"),
                        ja.getString("nivel_admin"));
                lista.add(u);
            }

            /*AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
            builder1.setCancelable(true);
            builder1.setMessage(ja.getString("id_usuarios"));
            builder1.show();*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void guardarConfiguracion(boolean estado, String id_usu)
    {
        SharedPreferences prefs =
                getSharedPreferences("Config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("estado", estado);
        editor.putString("usu", usuario.getText().toString());
        editor.putString("pass", pw.getText().toString());
        editor.putString("id", id_usu);
        editor.commit();
    }

    private class TareaAsincrona extends AsyncTask<String, Float, String> {

        protected void onPreExecute() {
            dialog = new ProgressDialog(Login.this);
            dialog.setMessage("Iniciado Sesion...");
            dialog.setTitle("Progreso");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setCancelable(false);
            dialog.setProgress(0);
            dialog.setMax(100);
            dialog.show(); //Mostramos el diálogo antes de comenzar
        }

        protected String doInBackground(String... params) {

            String ced = "", usu = "", nom = "", ape = "", pass = "", tel = "", na = "";
            usu = params[0];
            pass = params[1];
            StrictMode.enableDefaults();
            EnvioPost env = new EnvioPost();
            //String sql = "INSERT INTO Usuarios (cedula,nombre,apellido,email,password,telefono,nivel_admin) VALUES ('1045721276','JORGE','CAMPO',ing_jcampo@hotmail.com,'1234567','3660043','1')";
            String txt = env.postUsuarios("1","1","http://108.163.177.85/clase/jcampo6/app.php",ced,nom,ape,usu,pass,tel,na);
        /*pw.setText(txt);*/
        return txt;
        }

        protected void onProgressUpdate (Float... valores) {
            int p = Math.round(100*valores[0]);
            dialog.setProgress(p);
        }

        protected void onPostExecute(String result){
            if(!result.equalsIgnoreCase("")){
                ArrayList<Usuario> lista = new ArrayList<>();
                org.json.JSONObject ja = null;
                try {
                    ja = new org.json.JSONObject(result);
                    Usuario u;
                    Log.v("HEYYYY",ja.getString("id_usuarios"));
                    u = new Usuario(ja.getString("id_usuarios"),ja.getString("cedula"),ja.getString("nombre"),
                            ja.getString("apellido"),ja.getString("email"),ja.getString("password"),ja.getString("telefono"),
                            ja.getString("nivel_admin"));
                    lista.add(u);
                    guardarConfiguracion(true,ja.getString("id_usuarios"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.cancel();
                dialog = null;
                Intent in = new Intent(Login.this,Principal.class);
                finish();
                startActivity(in);

            }else{
                dialog.cancel();
                dialog = null;
                Toast.makeText(Login.this, "Datos Invalidos",Toast.LENGTH_SHORT).show();
            }
            /*AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
            builder1.setCancelable(true);
            builder1.setMessage(result);
            builder1.show();*/

        }
    }
}
