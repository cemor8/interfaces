package com.example.biblioteca;

public class Usuario {
    private String nombreUsuario;
    private String Apellidos;
    private String edad;
    private String correo;

    public Usuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getEdad() {
        return edad;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }
}
