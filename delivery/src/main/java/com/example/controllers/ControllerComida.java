package com.example.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ControllerComida {

    @FXML
    private AnchorPane anchorCarrito;

    @FXML
    private MFXButton meterCarrito;

    @FXML
    private MFXComboBox<?> mostrarCantidad;

    @FXML
    private ImageView mostrarComida;

    @FXML
    void meterCarrito(ActionEvent event) {

    }

}

