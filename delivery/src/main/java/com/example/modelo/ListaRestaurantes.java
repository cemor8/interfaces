package com.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaRestaurantes {
    private ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();

    public ArrayList<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }
    public void inicializarRestaurantes(){
        Comida nuggets = new Comida("nuggets","/imagenes/nuggets.jpg",5.25);
        Comida bocadillos = new Comida("bocadillos","/imagenes/bocadillos.jpg",3.95);
        Comida hamburguesa = new Comida("hamburguesa","/imagenes/hamburguesa.jpg",1.0);
        Comida kebab = new Comida("kebab","/imagenes/kebab.jpg",6.99);
        Comida patatasFritas = new Comida("patatas fritas","/imagenes/patatasFritas.png",3.99);
        Comida taco = new Comida("taco","/imagenes/taco.jpg",9.99);
        Comida pollo = new Comida("pollo frito","/imagenes/pollo.jpg",8.99);
        Restaurante mcDonalds = new Restaurante("mcdonalds",new ArrayList<Comida>(List.of(patatasFritas,hamburguesa)),"/imagenes/mcDonalds.jpg");
        Restaurante bk = new Restaurante("burguerking",new ArrayList<Comida>(List.of(nuggets)),"/imagenes/logoBK.jpg");
        Restaurante kfc = new Restaurante("KFC",new ArrayList<Comida>(List.of(pollo)),"/imagenes/kfc.jpg");
        Restaurante tacoBell = new Restaurante("Tacobell",new ArrayList<Comida>(List.of(taco)),"/imagenes/tacoBell.png");
        Restaurante restauranteKebab = new Restaurante("RestauranteKebabs",new ArrayList<Comida>(List.of(kebab)),"/imagenes/kebabRestaurante.jpg");
        Restaurante subway = new Restaurante("SUBWAY",new ArrayList<Comida>(List.of(bocadillos)),"/imagenes/subway.jpg");
        this.listaRestaurantes = new ArrayList<>(List.of(mcDonalds,bk,kfc,tacoBell,restauranteKebab,subway));
    }
}
