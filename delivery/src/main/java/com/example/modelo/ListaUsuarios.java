package com.example.modelo;

import java.util.ArrayList;

public class ListaUsuarios {
    private ArrayList<Usuario> listaUsuarios;

    public ListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
}
