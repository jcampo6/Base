package com.example.usuario.reservas;

/**
 * Created by USUARIO on 16/05/2015.
 */
public class ObjListaPrincipal {
    private String opcion;
    private int img;
    private long id;

    public ObjListaPrincipal(String opcion, int img) {
        this.opcion = opcion;
        this.img = img;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
