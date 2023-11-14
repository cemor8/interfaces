package com.example.modelo;

import java.util.ArrayList;

public class ListaRestaurantes {
    private ArrayList<Restaurante> listaRestaurantes;

    public ListaRestaurantes(ArrayList<Restaurante> listaRestaurantes) {
        this.listaRestaurantes = listaRestaurantes;
    }

    public ArrayList<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }
}
