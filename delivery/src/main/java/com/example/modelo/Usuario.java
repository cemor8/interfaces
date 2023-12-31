package com.example.modelo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String apellidos;
    private String correo;
    private String clave;
    private ArrayList<Comida> carro = new ArrayList<>();
    private double dineroGastado = 0;

    public Usuario(String nombre, String apellidos, String correo, String clave) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public ArrayList<Comida> getCarro() {
        return carro;
    }

    public void setCarro(ArrayList<Comida> carro) {
        this.carro = carro;
    }

    public double getDineroGastado() {
        return dineroGastado;
    }
    public void sumarDineroGastado(double dinero){
        this.dineroGastado += dinero;
    }
}
