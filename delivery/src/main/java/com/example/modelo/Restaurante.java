package com.example.modelo;

import java.util.ArrayList;

public class Restaurante {
    private String nombre;
    private int tiempoInicio;
    private int tiempoFin;
    private ArrayList<Comida> comidaDisponible;
    private String imagenRestarurante;
    private String nombreMostrar;

    public Restaurante(String nombre, ArrayList<Comida> comidaDisponible, String imagenRestarurante, int tiempoInicio, int tiempoFin, String nombreMostrar) {
        this.nombre = nombre;
        this.comidaDisponible = comidaDisponible;
        this.imagenRestarurante = imagenRestarurante;
        this.tiempoFin=tiempoFin;
        this.tiempoInicio=tiempoInicio;
        this.nombreMostrar = nombreMostrar;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Comida> getComidaDisponible() {
        return comidaDisponible;
    }

    public String getImagen() {
        return imagenRestarurante;
    }
    public double precioTotal(){
        double precio = 0;
        for (Comida cada_comida : this.comidaDisponible){
            precio+= cada_comida.getPrecio();
        }
        return precio;
    }

    public int getTiempoInicio() {
        return tiempoInicio;
    }

    public int getTiempoFin() {
        return tiempoFin;
    }

    public String getImagenRestarurante() {
        return imagenRestarurante;
    }

    public String getNombreMostrar() {
        return nombreMostrar;
    }
}
