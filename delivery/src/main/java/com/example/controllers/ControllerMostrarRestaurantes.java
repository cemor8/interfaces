package com.example.controllers;

import com.example.delivery.MainApplication;
import com.example.modelo.Data;
import com.example.modelo.Restaurante;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ControllerMostrarRestaurantes {

    @FXML
    private AnchorPane rellenarRestaurantes;
    private Data data = null;
    public void recibirRestaurantes(Data data) throws IOException {
        this.data = data;
        this.crearRestaurantes();
    }


    public void crearRestaurantes(){
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("grid_pane");
        gridPane.setHgap(20);
        gridPane.setVgap(20);

        double imageSize = 290;
        double fitImageSize = 120;


        int columna = 0;
        int fila = 0;

        for(Restaurante cada_restaurante : this.data.getListaRestaurantes().getListaRestaurantes()){
            AnchorPane contenedorRestaurante = new AnchorPane();
            contenedorRestaurante.setPrefSize(imageSize, 200);
            contenedorRestaurante.getStyleClass().add("cada_restaurante");
            Image imagen = new Image(getClass().getResourceAsStream(cada_restaurante.getImagen()));
            ImageView imageView = new ImageView();
            imageView.setImage(imagen);
            imageView.setFitWidth(290);
            imageView.setFitHeight(120);

            imageView.getStyleClass().add("imagen_restaurante");
            Label labelNombre = new Label(cada_restaurante.getNombre());
            labelNombre.setLayoutY(fitImageSize + 10);
            labelNombre.setLayoutX(200/2);

            labelNombre.getStyleClass().add("texto_restaurante");

            contenedorRestaurante.getChildren().addAll(imageView, labelNombre);
            gridPane.add(contenedorRestaurante, columna, fila);
            contenedorRestaurante.setId(cada_restaurante.getNombre());
            contenedorRestaurante.setOnMouseClicked(this::llevarMenu);
            columna++;
            if (columna > 2) {
                columna = 0;
                fila++;
            }


        }
        gridPane.setPadding(new Insets(15));
        AnchorPane.setTopAnchor(gridPane, 10.0);
        AnchorPane.setLeftAnchor(gridPane, 20.0);
        this.rellenarRestaurantes.getChildren().add(gridPane);
        this.rellenarRestaurantes.getStyleClass().add("anchor_principal");

    }

    public void llevarMenu(Event event){
        AnchorPane anchorPane = (AnchorPane) event.getSource();
        String nombreRestaurante =anchorPane.getId();
        Optional<Restaurante> restauranteOptional = this.data.getListaRestaurantes().getListaRestaurantes().stream().filter(restaurante -> restaurante.getNombre().equalsIgnoreCase(nombreRestaurante)).findAny();
        if(restauranteOptional.isEmpty()){
            return;
        }
        Restaurante restauranteEncontrado = restauranteOptional.get();
        this.data.setRestauranteSeleccionado(restauranteEncontrado);
        System.out.println(restauranteEncontrado.getNombre());
        try {
            this.mostrarMenuRestaurante();
        }catch (IOException err){
            System.out.println(err.getMessage());
        }


    }
    public void mostrarMenuRestaurante() throws IOException {
        System.out.println("A camabiar");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mostrar-menu-restaurante.fxml"));
        Parent menuRestaurante = fxmlLoader.load();

        ControllerMostrarMenu controllerMostrarMenu = fxmlLoader.getController();
        controllerMostrarMenu.recibirData(this.data);


        FXMLLoader panelLoader = new FXMLLoader(MainApplication.class.getResource("panel-view.fxml"));
        panelLoader.load();
        ControllerPanel controllerPanel = panelLoader.getController();
        controllerPanel.cambiarContenido(menuRestaurante);



    }

}

