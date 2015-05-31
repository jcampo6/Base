package com.example.usuario.reservas;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class Registrar_Usuario extends ActionBarActivity {

    private EditText cedula;
    private EditText nombre;
    private EditText apellido;
    private EditText email;
    private EditText pw;
    private EditText Rpw;
    private EditText telefono;
    private RadioButton admin;
    private Resources recursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__usuario);
        cedula = (EditText) findViewById(R.id.txt_cedula);
        nombre = (EditText) findViewById(R.id.txt_nombre);
        apellido = (EditText) findViewById(R.id.txt_apellido);
        email = (EditText) findViewById(R.id.txt_email);
        pw = (EditText) findViewById(R.id.txt_password);
        Rpw = (EditText) findViewById(R.id.txt_RetypePassword);
        telefono = (EditText) findViewById(R.id.txt_telefono);
        admin = (RadioButton) findViewById(R.id.rad_si);
        recursos = this.getResources();
    }

    public void guardar(View v){
        String ced = "", nom = "", ape = "", usu = "", pass = "", tel = "", na = "";
        ced = cedula.getText().toString();
        nom = nombre.getText().toString();
        ape = apellido.getText().toString();
        usu = email.getText().toString();
        pass = pw.getText().toString();
        tel = telefono.getText().toString();
        if(admin.isChecked()){
            na = "1";
        }else{
            na = "0";
        }
        if(ced.trim().equals("") || nom.trim().equals("") || ape.trim().equals("") || usu.trim().equals("") ||
                pass.trim().equals("") || tel.trim().equals("") || na.trim().equals("")){
            Toast.makeText(this, recursos.getString(R.string.error2),Toast.LENGTH_SHORT).show();
        }else{
            if(pw.getText().toString().equals(Rpw.getText().toString())){
                StrictMode.enableDefaults();
                EnvioPost env = new EnvioPost();
                String txt = env.postUsuarios("0","0","http://108.163.177.85/clase/jcampo6/app.php",ced,nom,ape,usu,pass,tel,na);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setCancelable(true);
                builder1.setMessage(txt);
                builder1.show();
                if(txt.equals("1")){
                    Toast.makeText(this, recursos.getString(R.string.inf0),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Registrar_Usuario.this,Login.class);
                    finish();
                    startActivity(i);
                }else{
                    Toast.makeText(this, recursos.getString(R.string.error2),Toast.LENGTH_SHORT).show();
                }
            }else{
                pw.setError(recursos.getString(R.string.error1));
                Rpw.setError(recursos.getString(R.string.error1));
                pw.requestFocus();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registrar__usuario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
