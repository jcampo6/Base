package com.example.usuario.reservas;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Login extends ActionBarActivity {

    private EditText usuario;
    private EditText pw;
    private ProgressDialog dialog;
    private String cadJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (EditText) findViewById(R.id.txt_usuario);
        pw = (EditText) findViewById(R.id.txt_clave);
    }

    public void enviar(View view){
        if(!usuario.getText().toString().trim().equals("") || !pw.getText().toString().trim().equals("") ) {
            new TareaAsincrona().execute(usuario.getText().toString(), pw.getText().toString());
            Bundle b = new Bundle();
            showJSON(cadJson);
        }
    }

    public void prueba(View v){
        Intent i = new Intent(Login.this, Principal.class);
        startActivity(i);
    }

    public ArrayList<Usuario> showJSON(String json){
        try{
            JSONArray jarray = new JSONArray(json);
            JSONObject obj;
            ArrayList<Usuario> lista = new ArrayList<Usuario>();
            for (int i = 0; i < jarray.length() ; i++) {
                obj = (JSONObject) jarray.get(i);
                Usuario u = new Usuario(obj.get("id_usuarios").toString(),obj.get("cedula").toString(),obj.get("nombre").toString(),obj.get("apellido").toString(),obj.get("email").toString(),obj.get("password").toString(),
                        obj.get("telefono").toString(),obj.get("nivel_admin").toString());
               lista.add(u);
            }
            return lista;
        }catch (Exception e){
            return null;
        }
    }

    private class TareaAsincrona extends AsyncTask<String, Float, String> {

        protected void onPreExecute() {
            dialog = new ProgressDialog(Login.this);
            dialog.setMessage("Iniciado Sesion...");
            dialog.setTitle("Progreso");
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setCancelable(false);
            dialog.setProgress(0);
            dialog.setMax(100);
            dialog.show(); //Mostramos el diálogo antes de comenzar
        }

        protected String doInBackground(String... params) {
            /**
             * Simularemos que descargamos un fichero
             * mediante un sleep
             */
            String ced = "", usu = "", nom = "", ape = "", pass = "", tel = "", na = "";
            usu = params[0];
            pass = params[1];
             StrictMode.enableDefaults();
            EnvioPost env = new EnvioPost();
            //String sql = "INSERT INTO Usuarios (cedula,nombre,apellido,email,password,telefono,nivel_admin) VALUES ('1045721276','JORGE','CAMPO',ing_jcampo@hotmail.com,'1234567','3660043','1')";
            String txt = env.postUsuarios("1","http://108.163.177.85/clase/jcampo6/app.php",ced,nom,ape,usu,pass,tel,na);
        /*pw.setText(txt);*/
        return txt;
        }

        protected void onProgressUpdate (Float... valores) {
            int p = Math.round(100*valores[0]);
            dialog.setProgress(p);
        }

        protected void onPostExecute(String result){
            dialog.dismiss();
            cadJson = result;
            /*AlertDialog.Builder builder1 = new AlertDialog.Builder(Login.this);
            builder1.setCancelable(true);
            builder1.setMessage(result);
            builder1.show();*/

        }
    }
}
