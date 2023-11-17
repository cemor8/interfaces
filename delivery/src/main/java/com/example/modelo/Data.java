package com.example.modelo;

import java.util.ArrayList;

public class Data {
    private ListaUsuarios listaUsuarios= new ListaUsuarios();
    private ListaRestaurantes listaRestaurantes= new ListaRestaurantes();
    private Usuario currentUser = null;

    public ListaUsuarios getListaUsuarios() {
        return listaUsuarios;
    }

    public ListaRestaurantes getListaRestaurantes() {
        return listaRestaurantes;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }
}
