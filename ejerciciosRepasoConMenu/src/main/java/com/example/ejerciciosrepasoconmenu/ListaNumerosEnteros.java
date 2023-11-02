package com.example.ejerciciosrepasoconmenu;

import java.util.ArrayList;

public class ListaNumerosEnteros {
    private final ArrayList<Integer> numeros;

    public ListaNumerosEnteros() {
        this.numeros = new ArrayList<>();
    }

    public ArrayList<Integer> getNumeros() {
        return numeros;
    }
    /**
     * Método que recibe un numero, si este ya esta en la lista de numeros, no lo mete y lo indica,
     * si no, lo añade a esta.
     * */
    public void lista(Integer numero) throws Exception {
        if(!numeros.contains(numero)){
            numeros.add(numero);
        }else {
            throw new Exception("numero invalido");
        }

    }
}

