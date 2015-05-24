package com.example.usuario.reservas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;


public class Registrar_Usuario extends ActionBarActivity {

    private EditText cedula;
    private EditText nombre;
    private EditText apellido;
    private EditText email;
    private EditText pw;
    private EditText Rpw;
    private EditText telefono;
    private RadioButton admin;

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
