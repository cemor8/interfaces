package com.example.modelo;

public class Comida {
    private String nombre;
    private String foto;
    private double precio;
    private int cantidad;
    private String descripcion;

    public Comida(String nombre, String foto, double precio, String descripcion) {
        this.nombre = nombre;
        this.foto = foto;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFoto() {
        return foto;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
