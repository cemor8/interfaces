package com.example.gestionalumnos;

public class Alumno {
    private String nombre;
    private String apellidos;
    private String ciclo;
    private double notaMedia;
    private String dni;
    private String num_telefono;

    public Alumno(String nombre,String apellidos, String ciclo, double notaMedia, String dni, String num_telefono) {
        this.nombre = nombre;
        this.apellidos=apellidos;
        this.ciclo = ciclo;
        this.notaMedia = notaMedia;
        this.dni = dni;
        this.num_telefono = num_telefono;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombre() {
        return nombre;
    }


    public String getCiclo() {
        return ciclo;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public String getDni() {
        return dni;
    }

    public String getNum_telefono() {
        return num_telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNum_telefono(String num_telefono) {
        this.num_telefono = num_telefono;
    }
}
