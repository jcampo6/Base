package com.example.usuario.reservas;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USUARIO on 16/05/2015.
 */
public class AdapterMenuPrincipal extends BaseAdapter {

    private Context contexto;
    private ArrayList<ObjListaPrincipal> lista;
    private Resources recursos;

    public AdapterMenuPrincipal(Context contexto, ArrayList<ObjListaPrincipal> lista) {
        this.contexto = contexto;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        recursos = contexto.getResources();

        View v = convertView;

        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.plantilla_lista, null);
        }
        ObjListaPrincipal obj = lista.get(position);
        ImageView img = (ImageView) v.findViewById(R.id.img_plantilla);
        img.setImageDrawable(lista.get(position).getImg());
        TextView opc = (TextView) v.findViewById(R.id.lbl_plantilla);

        return null;
    }
}
