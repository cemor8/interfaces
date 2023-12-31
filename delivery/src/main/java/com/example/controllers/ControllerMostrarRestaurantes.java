package com.example.controllers;

import com.example.delivery.MainApplication;
import com.example.modelo.Data;
import com.example.modelo.Restaurante;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ControllerMostrarRestaurantes {

    @FXML
    private AnchorPane rellenarRestaurantes;
    private Data data = null;
    @FXML
    private VBox contenedorOfertas;
    private ControllerPanel controllerPanel = null;
    @FXML
    private MFXScrollPane scrollRestaurantes;
    @FXML
    private Label mostrarGastado;
    /**
     * Método que se encarga de recibir la informacion y crear los
     * restaurantes
     * */
    public void recibirData(Data data, ControllerPanel controllerPanel) {
        this.data = data;
        this.controllerPanel = controllerPanel;
        this.crearRestaurantes();
        this.mostrarGastado.setText(String.valueOf(this.data.getCurrentUser().getDineroGastado() + "€"));

    }
    /**
     * Método que se encarga de crear los restaurantes y meterlos
     * en un scrollpane
     * */
    public void crearRestaurantes(){

        VBox vBox = new VBox();
        HBox hBox = new HBox();

        vBox.setSpacing(30);
        for(int i = 0; i<this.data.getListaRestaurantes().getListaRestaurantes().size() ; i++){
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setId(this.data.getListaRestaurantes().getListaRestaurantes().get(i).getNombre());
            anchorPane.setMinWidth(200);
            anchorPane.setMinHeight(220);
            anchorPane.getStyleClass().add("test");
            anchorPane.setOnMouseClicked(this::mostrarMenu);

            Image image = new Image(getClass().getResourceAsStream(this.data.getListaRestaurantes().getListaRestaurantes().get(i).getImagen()));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(290);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(false);

            Rectangle clipRect = new Rectangle(290, 150);
            clipRect.setArcWidth(20);
            clipRect.setArcHeight(20);
            imageView.setClip(clipRect);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(imageView);
            stackPane.setPrefHeight(270);
            stackPane.setPrefWidth(150);

            Label nombre = new Label(this.data.getListaRestaurantes().getListaRestaurantes().get(i).getNombreMostrar());
            nombre.getStyleClass().add("nombre_restaurante");
            nombre.setLayoutY(160);

            Label tiempo = new Label("Tiempo de Entrega: "+this.data.getListaRestaurantes().getListaRestaurantes().get(i).getTiempoInicio()+" - "+this.data.getListaRestaurantes().getListaRestaurantes().get(i).getTiempoFin()+" mins.");
            tiempo.getStyleClass().add("tiempo-entrega");
            tiempo.setLayoutY(190);

            anchorPane.getChildren().add(imageView);
            anchorPane.getChildren().add(nombre);
            anchorPane.getChildren().add(tiempo);

            Insets insets = new Insets(0, 0, 0, 25);
            HBox.setMargin(anchorPane, insets);

            if (i % 3 == 0) {
                if (i > 0) {
                    vBox.getChildren().add(hBox);
                }
                hBox = new HBox();
            }
            hBox.getChildren().add(anchorPane);

            if (i == this.data.getListaRestaurantes().getListaRestaurantes().size() - 1) {
                vBox.getChildren().add(hBox);
            }
        }
        this.scrollRestaurantes.setContent(vBox);

    }
    /**
     * Método que se encarga de mostrar el menu de un restaurante, se utiliza cuando
     * se selecciona un restaurante.
     * */
    @FXML
    public void mostrarMenu(Event event){
        AnchorPane anchorPane = (AnchorPane) event.getSource();
        String nombreRestaurante =anchorPane.getId();
        Optional<Restaurante> restauranteOptional = this.data.getListaRestaurantes().getListaRestaurantes().stream().filter(restaurante -> restaurante.getNombre().trim().equalsIgnoreCase(nombreRestaurante.trim())).findAny();
        if(restauranteOptional.isEmpty()){
            return;
        }
        Restaurante restauranteEncontrado = restauranteOptional.get();
        this.data.setRestauranteSeleccionado(restauranteEncontrado);
        try {
            this.mostrarMenuRestaurante();
        }catch (IOException err){
            System.out.println(err.getMessage());
        }

    }
    /**
     * Método que se encarga de buscar un restaurante aleatorio dependiendo
     * de en la categoria que se ha clickado.
     * */
    @FXML
    public void mostrarMenuAleatorio(Event event){

        ArrayList<Restaurante> posibles = new ArrayList<>();
        Button btn = (Button) event.getSource();
        switch (btn.getId()){
            case "btnHambur":
                this.comporbarComidaRestaurante(posibles,"hamburguesa");
                break;
            case "btnBoca":
                this.comporbarComidaRestaurante(posibles,"bocadillos");
                break;
            case "btnPollo":
                this.comporbarComidaRestaurante(posibles,"pollo");
                break;
            case "btnTacos":
                this.comporbarComidaRestaurante(posibles,"taco");
                break;
            case "btnKebab":
                this.comporbarComidaRestaurante(posibles,"kebab");
                break;
            case "todos","btn_ver_mas":
                posibles.addAll(this.data.getListaRestaurantes().getListaRestaurantes());
                break;
        }

        Restaurante restauranteEncontrado = posibles.get((int) Math.floor(Math.random() * posibles.size()));
        this.data.setRestauranteSeleccionado(restauranteEncontrado);

        try {
            this.mostrarMenuRestaurante();
        }catch (IOException err){
            System.out.println(err.getMessage());
        }


    }
    /**
     * Método que se encarga de comprobar si un restaurante tiene
     * comida de una categoria
     * @param posibles lista de restaurantes posibles
     * @param tipo categoria de comida
     * */
    private void comporbarComidaRestaurante (ArrayList<Restaurante> posibles, String tipo) {
        for(Restaurante restaurante : this.data.getListaRestaurantes().getListaRestaurantes()){
            for(String comida : restaurante.getTipoComida()){
                if(comida.equalsIgnoreCase(tipo)){
                    posibles.add(restaurante);
                    break;
                }
            }
        }
    }

    /**
     * Método que se encarga de mostrar el menu de un restaurante
     * */
    public void mostrarMenuRestaurante() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mostrar-menu.fxml"));
        Parent menuRestaurante = fxmlLoader.load();

        ControllerMostrarMenu controllerMostrarMenu = fxmlLoader.getController();
        controllerMostrarMenu.recibirData(this.data);
        this.controllerPanel.cambiarContenido(menuRestaurante);

    }

}

