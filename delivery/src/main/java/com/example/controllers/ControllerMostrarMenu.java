package com.example.controllers;

import com.example.modelo.Comida;
import com.example.modelo.Data;
import com.example.modelo.Restaurante;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ControllerMostrarMenu {

    @FXML
    private AnchorPane contenidoMenu;

    @FXML
    private HBox mostrarComida;

    @FXML
    private Label mostrarNombreRestaurante;
    private Data data = null;
    private Restaurante restaurante = null;
    public void recibirData(Data data){
        this.data = data;
        this.crearComida();
        this.mostrarNombreRestaurante.setText(this.data.getRestauranteSeleccionado().getNombre());
    }
    public void crearComida(){
        for(Comida cada_comida : this.data.getRestauranteSeleccionado().getComidaDisponible()){
            AnchorPane contenedorComida = new AnchorPane();
            contenedorComida.setPrefSize(150, 150);
            Image imagenComida = new Image(getClass().getResourceAsStream(cada_comida.getFoto()));
            ImageView imageView = new ImageView();
            imageView.setImage(imagenComida);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            Label labelNombreComida = new Label(cada_comida.getNombre());
            labelNombreComida.setLayoutY(110);
            contenedorComida.getChildren().addAll(imageView, labelNombreComida);
            mostrarComida.getChildren().add(contenedorComida);
        }
        System.out.println("hola");
    }

}
