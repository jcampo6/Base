package com.example.usuario.reservas;

/**
 * Created by Jhon Campo Mendoza on 21/05/2015.
 */
public class Usuario {
    private String id_usuarios;
    private String cedula;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    private String nivel_admin;

    public Usuario(String id_usuarios, String cedula, String nombre, String apellido, String email, String password, String telefono, String nivel_admin) {
        this.id_usuarios = id_usuarios;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.nivel_admin = nivel_admin;
    }

    public String getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(String id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNivel_admin() {
        return nivel_admin;
    }

    public void setNivel_admin(String nivel_admin) {
        this.nivel_admin = nivel_admin;
    }
}
