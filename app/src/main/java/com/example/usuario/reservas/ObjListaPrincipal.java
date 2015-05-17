package com.example.usuario.reservas;

import android.graphics.drawable.Drawable;

/**
 * Created by USUARIO on 16/05/2015.
 */
public class ObjListaPrincipal {
    private String opcion;
    private Drawable img;
    private long id;

    public ObjListaPrincipal(Drawable img, String opcion) {
        this.img = img;
        this.opcion = opcion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
