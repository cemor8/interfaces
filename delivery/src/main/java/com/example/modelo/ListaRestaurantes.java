package com.example.modelo;

import java.util.ArrayList;
import java.util.List;

public class ListaRestaurantes {
    private ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();

    public ArrayList<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }
    public void inicializarRestaurantes(){
        Comida nuggets = new Comida("Nuggets de pollo","/imagenes/nuggets.jpg",5.00);
        Comida bocadillos = new Comida("Bocaditos pollo","/imagenes/bocadillos.jpg",3.50);
        Comida hamburguesa = new Comida("Hamburguesa con queso","/imagenes/hamburguesa.jpg",10.0);
        Comida kebab = new Comida("Kebab con carne y pollo","/imagenes/kebab.jpg",6.50);
        Comida patatasFritas = new Comida("Patatas fritas","/imagenes/patatasFritas.png",3.50);
        Comida taco = new Comida("Taco con tomate","/imagenes/taco.jpg",9.50);
        Comida pollo = new Comida("Pollo frito con salsa BBQ","/imagenes/pollo.jpg",8.99);
        Comida bigMac = new Comida("Big mac","/imagenes/bigmac.png",10.50);
        Comida bigMacPollo = new Comida("Big Mac Pollo","/imagenes/bigmacpollo.png",12.00);
        Comida burrito = new Comida("Burrito","/imagenes/burrito.png",9.00);
        Comida cocacola = new Comida("Cocacola Zero","/imagenes/cocacola.png",3.50);
        Comida hamburKfc = new Comida("Hamburguesa","/imagenes/hamburKfc.jpg",10.00);
        Comida kebab1 = new Comida("Kebab Solo Pollo","/imagenes/kebab1.jpg",7.50);
        Comida kebab2 = new Comida("Kebab Solo Carne","/imagenes/kebab2.jpg",7.50);
        Comida mcFlurry = new Comida("Mc Flurry Kit-Kat","/imagenes/mcflurry.png",4.50);
        Comida menuSuper = new Comida("Menu Super KFC","/imagenes/menuSuperkfc.jpg",12.50);
        Comida muchosTacos = new Comida("Pack de 4 Tacos de Pollo","/imagenes/muchosTacos.jpg",20.00);
        Comida patatasKfc = new Comida("Patatas Fritas","/imagenes/patatasKfc.jpg",5.50);
        Comida subway1= new Comida("Bocadillo Chicken Tikka","/imagenes/subway1.jpg",10.00);
        Comida bocadillo= new Comida("Bocadillo Albóndigas","/imagenes/subwayalbondigas.jpg",12.00);
        Comida bocadilloTomate = new Comida("Bocadillo con Tomate","/imagenes/subwaytomate.jpg",11.00);
        Comida tacoPatatas = new Comida("Patatas Fritas","/imagenes/tacoPatatas.jpg",5.00);


        Restaurante mcDonalds = new Restaurante("mcdonalds",new ArrayList<Comida>(List.of(patatasFritas,bigMacPollo,bigMac,mcFlurry)),"/imagenes/mcDonalds.jpg",10,45);
        Restaurante bk = new Restaurante("burguerking",new ArrayList<Comida>(List.of(nuggets,cocacola,hamburguesa)),"/imagenes/logoBK.jpg",20,30);
        Restaurante kfc = new Restaurante("KFC",new ArrayList<Comida>(List.of(pollo,hamburKfc,menuSuper,patatasKfc)),"/imagenes/kfc.jpg",25,50);
        Restaurante tacoBell = new Restaurante("Tacobell",new ArrayList<Comida>(List.of(taco,muchosTacos,tacoPatatas,burrito)),"/imagenes/tacoBell.png",10,40);
        Restaurante restauranteKebab = new Restaurante("RestauranteKebabs",new ArrayList<Comida>(List.of(kebab,kebab2,kebab1)),"/imagenes/kebabRestaurante.jpg",35,50);
        Restaurante subway = new Restaurante("SUBWAY",new ArrayList<Comida>(List.of(bocadillos,subway1,bocadilloTomate,bocadillo)),"/imagenes/subway.jpg",10,20);
        this.listaRestaurantes = new ArrayList<>(List.of(mcDonalds,bk,kfc,tacoBell,restauranteKebab,subway));
    }
}
