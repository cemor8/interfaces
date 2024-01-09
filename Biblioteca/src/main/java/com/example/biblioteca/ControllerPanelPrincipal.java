package com.example.biblioteca;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ControllerPanelPrincipal {

    @FXML
    private Label labelMostrarNombre;

    @FXML
    private AnchorPane main;

    @FXML
    private AnchorPane menuLateral;

    @FXML
    private AnchorPane rellenarContenido;
    private Data data;

    @FXML
    void meterLibro(MouseEvent event) {

    }

    @FXML
    void verConfiguracion(MouseEvent event) {

    }

    @FXML
    void verLibros(MouseEvent event) {

    }

    @FXML
    void verTabla(MouseEvent event) {

    }

    @FXML
    void verUsuario(MouseEvent event) {

    }
    public void establecerDatos(Data data){
        this.data = data;
    }

}

