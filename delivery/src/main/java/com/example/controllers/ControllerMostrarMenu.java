package com.example.controllers;

import com.example.delivery.MainApplication;
import com.example.modelo.Comida;
import com.example.modelo.Data;
import com.example.modelo.Restaurante;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ControllerMostrarMenu {

    @FXML
    private AnchorPane contenidoMenu;

    @FXML
    private HBox mostrarComida;

    private Data data = null;
    private Restaurante restaurante = null;
    public void recibirData(Data data){
        this.data = data;
        this.crearComida();
    }
    public void crearComida(){
        this.mostrarComida.setAlignment(Pos.CENTER);
        this.mostrarComida.setSpacing(20.0);
        for(Comida cada_comida : this.data.getRestauranteSeleccionado().getComidaDisponible()){
            Insets insets = new Insets(0, 10, 0, 10); // Márgenes: arriba, derecha, abajo, izquierda
            AnchorPane contenedorComida = new AnchorPane();
            contenedorComida.setPrefSize(150, 150);
            contenedorComida.setId(cada_comida.getNombre());
            Image imagenComida = new Image(getClass().getResourceAsStream(cada_comida.getFoto()));
            ImageView imageView = new ImageView();
            imageView.setImage(imagenComida);
            imageView.setFitWidth(200);
            imageView.setFitHeight(150);
            imageView.getStyleClass().add("imagen-comida");
            Label labelNombreComida = new Label(cada_comida.getNombre());
            labelNombreComida.setLayoutY(160);
            labelNombreComida.getStyleClass().add("nombreComida");
            contenedorComida.setOnMouseClicked(this::mostrarComida);
            contenedorComida.getChildren().addAll(imageView, labelNombreComida);

            HBox.setMargin(contenedorComida, insets);

            mostrarComida.getChildren().add(contenedorComida);
        }

        System.out.println("hola");
    }
    public void mostrarComida(Event event){
        System.out.println("aqqui");
        AnchorPane anchorPane = (AnchorPane) event.getSource();
        String nombreComida =anchorPane.getId();
        Optional<Comida> comidaOptional = this.data.getRestauranteSeleccionado().getComidaDisponible().stream().filter(comida -> comida.getNombre().equalsIgnoreCase(nombreComida)).findAny();
        if(comidaOptional.isEmpty()){
            return;
        }
        Comida comidaEncontrada = comidaOptional.get();
        this.data.setComidaSeleccionada(comidaEncontrada);
        System.out.println(comidaEncontrada.getNombre());

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mostrar-comida.fxml"));
            Parent menuRestaurante = fxmlLoader.load();
            ControllerComida controllerComida = fxmlLoader.getController();
            controllerComida.recibirData(this.data);
            stage.setScene(new Scene(menuRestaurante));
            stage.show();


        }catch (IOException err){
            System.out.println(err.getMessage());
        }


    }

}
