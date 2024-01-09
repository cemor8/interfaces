package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControllerConfiguracion {

    @FXML
    private MFXButton btnGuardar;

    @FXML
    private Label labelColor;

    @FXML
    private Label labelIdioma;

    @FXML
    private MFXComboBox<String> opcionesColor;

    @FXML
    private MFXComboBox<String> opcionesIdioma;
    private Data data;

    @FXML
    void guardar(MouseEvent event) {

    }
    public void recibirData(Data data){
        this.data = data;
    }

}

