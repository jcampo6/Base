package com.example.usuario.reservas;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class Fragment1 extends android.support.v4.app.Fragment {

    private EditText nombre;
    private EditText ubicacion;
    private TextView path;
    private ProgressDialog dialog;
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
        dialog = new ProgressDialog(view.getContext());
        dialog.setMessage("Iniciado Sesion...");
        dialog.setTitle("Progreso");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setProgress(0);
        dialog.setMax(100);
        dialog.show(); //Mostramos el diálogo antes de comenzar
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        dialog.cancel();
    }
}
