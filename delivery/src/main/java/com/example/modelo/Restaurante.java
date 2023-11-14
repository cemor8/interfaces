package com.example.modelo;

import java.util.ArrayList;

public class Restaurante {
    private String nombre;
    private ArrayList<Comida> comidaDisponible;
    private String imagenRestarurante;

    public Restaurante(String nombre, ArrayList<Comida> comidaDisponible, String imagenRestarurante) {
        this.nombre = nombre;
        this.comidaDisponible = comidaDisponible;
        this.imagenRestarurante = imagenRestarurante;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Comida> getComidaDisponible() {
        return comidaDisponible;
    }

    public String getImagenRestarurante() {
        return imagenRestarurante;
    }
}
