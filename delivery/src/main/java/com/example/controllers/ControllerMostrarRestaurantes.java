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
    public void recibirData(Data data) throws IOException {
        this.data = data;
    }

    @FXML
    public void mostrarMenu(Event event){
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
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mostrar-menu.fxml"));
        Parent menuRestaurante = fxmlLoader.load();

        ControllerMostrarMenu controllerMostrarMenu = fxmlLoader.getController();
        controllerMostrarMenu.recibirData(this.data);


        FXMLLoader panelLoader = new FXMLLoader(MainApplication.class.getResource("panel-view.fxml"));
        panelLoader.load();
        ControllerPanel controllerPanel = panelLoader.getController();
        controllerPanel.cambiarContenido(menuRestaurante);

    }

}

