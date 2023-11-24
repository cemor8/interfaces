package com.example.modelo;

import java.util.ArrayList;

public class Restaurante {
    private String nombre;
    private int tiempoInicio;
    private int tiempoFin;
    private ArrayList<Comida> comidaDisponible;
    private String imagenRestarurante;

    public Restaurante(String nombre, ArrayList<Comida> comidaDisponible, String imagenRestarurante, int tiempoInicio, int tiempoFin) {
        this.nombre = nombre;
        this.comidaDisponible = comidaDisponible;
        this.imagenRestarurante = imagenRestarurante;
        this.tiempoFin=tiempoFin;
        this.tiempoInicio=tiempoInicio;
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
}
