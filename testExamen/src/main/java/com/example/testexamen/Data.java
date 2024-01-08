package com.example.testexamen;

import java.util.ArrayList;

public class Data {
    private ArrayList<Alumno> listaAlumnos;

    public Data(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public ArrayList<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }
}
