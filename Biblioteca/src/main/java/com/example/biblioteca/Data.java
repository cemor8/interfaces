package com.example.biblioteca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Data {
    public Data() {

    }

    private Usuario currentUser;
    private ObservableList<Libro> libros = FXCollections.observableArrayList();

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public ObservableList<Libro> getLibros() {
        return libros;
    }
}
