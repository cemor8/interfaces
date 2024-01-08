package com.example.testexamen;

public class Alumno {
    private String nombre;
    private String apellidos;
    private int nota;
    private String ciclo;

    public Alumno(String nombre, String apellidos, int nota, String ciclo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nota = nota;
        this.ciclo = ciclo;
    }

    public Alumno(String nombre, int nota, String ciclo) {
        this.nombre = nombre;
        this.nota = nota;
        this.ciclo = ciclo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNota() {
        return nota;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
