package com.example.gestionalumnos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatosAlumnos {
    private Alumno alumnoSeleccionado;
    private ObservableList<Alumno> listaAlumnos= FXCollections.observableArrayList();

    public ObservableList<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }
    public void meterAlumno(Alumno alumno){
        this.listaAlumnos.add(alumno);
    }

    public void setAlumnoSeleccionado(Alumno alumnoSeleccionado) {
        this.alumnoSeleccionado = alumnoSeleccionado;
    }
}
