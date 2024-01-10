package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

