package com.example.gestionalumnos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

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
    public Alumno getAlumnoSeleccionado() {
        return alumnoSeleccionado;
    }

    public void resetear() {
        this.listaAlumnos = FXCollections.observableArrayList();
        this.alumnoSeleccionado=null;
    }
}
