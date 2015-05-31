package com.example.usuario.reservas;

/**
 * Created by Jhon Campo Mendoza on 28/05/2015.
 */
public class Escenario {
    private String id_esc;
    private String nombre;
    private String ubicacion;
    private byte[] imgagen;

    public Escenario(String nombre, String ubicacion, byte[] imgagen) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.imgagen = imgagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public byte[] getImgagen() {
        return imgagen;
    }

    public void setImgagen(byte[] imgagen) {
        this.imgagen = imgagen;
    }

    public String getId_esc() {
        return id_esc;
    }

    public void setId_esc(String id_esc) {
        this.id_esc = id_esc;
    }
}
