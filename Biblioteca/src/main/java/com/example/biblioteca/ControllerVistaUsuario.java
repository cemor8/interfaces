package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControllerVistaUsuario {

    @FXML
    private MFXButton btnGuardar;

    @FXML
    private MFXButton btnSalir;

    @FXML
    private MFXTextField introducirApellido;

    @FXML
    private MFXTextField introducirEdad;

    @FXML
    private MFXTextField introducirEmail;

    @FXML
    private MFXTextField introducirNombre;

    @FXML
    private Label labelApellido;

    @FXML
    private Label labelEdad;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelNombre;
    private Data data;

    @FXML
    void guardar(MouseEvent event) {

    }

    @FXML
    void salir(MouseEvent event) {

    }
    public void recibirData(Data data){
        this.data = data;
    }

}

