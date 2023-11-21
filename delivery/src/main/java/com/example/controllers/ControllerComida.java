package com.example.controllers;

import com.example.modelo.Comida;
import com.example.modelo.Data;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerComida implements Initializable {

    @FXML
    private AnchorPane anchorCarrito;

    @FXML
    private MFXButton meterCarrito;

    @FXML
    private MFXComboBox<Integer> mostrarCantidad;

    @FXML
    private ImageView mostrarComida;
    private Data data;

    @FXML
    void meterCarrito(ActionEvent event) {
        if(this.mostrarCantidad.getValue() == null){
            return;
        }
        this.data.getCurrentUser().getCarro().add(new Comida(this.data.getComidaSeleccionada().getNombre(),this.data.getComidaSeleccionada().getFoto(),this.data.getComidaSeleccionada().getPrecio()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 1; i <= 20; i++) {
            mostrarCantidad.getItems().add(i);
        }

    }

    public void recibirData(Data data){
        this.data = data;
        this.mostrarComida.setImage(new Image(this.data.getComidaSeleccionada().getFoto()));
    }

}

