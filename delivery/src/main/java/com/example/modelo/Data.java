package com.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private ListaUsuarios listaUsuarios= new ListaUsuarios();
    private ListaRestaurantes listaRestaurantes= new ListaRestaurantes();
    private Usuario currentUser = null;
    private Restaurante restauranteSeleccionado = null;

    public ListaUsuarios getListaUsuarios() {
        return listaUsuarios;
    }

    public ListaRestaurantes getListaRestaurantes() {
        return listaRestaurantes;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    public void setListaRestaurantes(ListaRestaurantes listaRestaurantes) {
        this.listaRestaurantes = listaRestaurantes;
    }

    public Restaurante getRestauranteSeleccionado() {
        return restauranteSeleccionado;
    }

    public void setRestauranteSeleccionado(Restaurante restauranteSeleccionado) {
        this.restauranteSeleccionado = restauranteSeleccionado;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

}
