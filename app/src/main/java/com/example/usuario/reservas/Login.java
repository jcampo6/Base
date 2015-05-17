package com.example.usuario.reservas;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends ActionBarActivity {

    private EditText usuario;
    private EditText pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = (EditText) findViewById(R.id.txt_usuario);
        pw = (EditText) findViewById(R.id.txt_clave);
    }

    public void enviar(View view){
        StrictMode.enableDefaults();
        EnvioPost env = new EnvioPost();
        String sql = "INSERT INTO Usuarios VALUES (null,'JORGE','CAMPO',elkb@hotmail.es,'1234567','3604300',false)";
        String txt = env.post("http://185.27.134.11/app.php", sql);
        if(txt.equals("1")){
            Toast msg = Toast.makeText(this, "Datos Guardados!",Toast.LENGTH_SHORT);
            msg.show();
        }else{
            Toast msg = Toast.makeText(this, "Error Al Guardar Datos!",Toast.LENGTH_SHORT);
            msg.show();
        }

    }

    public void prueba(View v){
        Intent i = new Intent(Login.this, Principal2.class);
        startActivity(i);
    }
}
