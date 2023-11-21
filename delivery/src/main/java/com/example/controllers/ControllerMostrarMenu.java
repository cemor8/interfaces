package com.example.controllers;

import com.example.delivery.MainApplication;
import com.example.modelo.Comida;
import com.example.modelo.Data;
import com.example.modelo.Restaurante;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
            contenedorComida.setOnMouseClicked(this::mostrarComida);
            contenedorComida.getChildren().addAll(imageView, labelNombreComida);
            mostrarComida.getChildren().add(contenedorComida);
        }
        System.out.println("hola");
    }
    public void mostrarComida(Event event){
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
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mostrar-comida.xfml"));
            Parent menuRestaurante = fxmlLoader.load();
            ControllerComida controllerComidac = fxmlLoader.getController();
            controllerComidac.recibirData(this.data);
            stage.setScene(new Scene(menuRestaurante));
            stage.show();


        }catch (IOException err){
            System.out.println(err.getMessage());
        }


    }

}
