package com.example.biblioteca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Data {
    public Data() {

    }

    private Usuario currentUser;
    private ObservableList<Libro> libros = FXCollections.observableArrayList();
    private String vistaAnterior;
    private Locale locale = new Locale("es","ES");
    private ResourceBundle bundle;
    private boolean filtrar = false;
    private boolean oscuro = true;

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public ObservableList<Libro> getLibros() {
        return libros;
    }

    public String getVistaAnterior() {
        return vistaAnterior;
    }

    public Locale getLocale() {
        return locale;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setLibros(ObservableList<Libro> libros) {
        this.libros = libros;
    }

    public void setVistaAnterior(String vistaAnterior) {
        this.vistaAnterior = vistaAnterior;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public boolean isFiltrar() {
        return filtrar;
    }

    public void setFiltrar(boolean filtrar) {
        this.filtrar = filtrar;
    }

    public boolean isOscuro() {
        return oscuro;
    }

    public void setOscuro(boolean oscuro) {
        this.oscuro = oscuro;
    }
}
