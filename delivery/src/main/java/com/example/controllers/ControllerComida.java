package com.example.controllers;

import com.example.modelo.Comida;
import com.example.modelo.Data;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerComida implements Initializable {

    @FXML
    private AnchorPane anchorCarrito;

    @FXML
    private MFXButton meterCarrito;

    @FXML
    private ComboBox<Integer> mostrarCantidad;

    @FXML
    private ImageView mostrarComida;
    private Data data;
    @FXML
    private Label info;

    @FXML
    void meterCarrito(ActionEvent event) {
        this.info.setText("");
        if(this.mostrarCantidad.getValue() == null){
            return;
        }
        Comida comida = new Comida(this.data.getComidaSeleccionada().getNombre(),this.data.getComidaSeleccionada().getFoto(),this.data.getComidaSeleccionada().getPrecio());
        comida.setCantidad(this.mostrarCantidad.getValue());
        Optional<Comida> comidaOptional = this.data.getCurrentUser().getCarro().stream().filter(comida1 -> comida1.getNombre().equalsIgnoreCase(comida.getNombre())).findAny();
        if(comidaOptional.isPresent()){
            if(comidaOptional.get().getCantidad() + comida.getCantidad() >20){
                this.info.setText("Cantidad máxima del artículo superada");
                return;
            }
            comidaOptional.get().setCantidad(comidaOptional.get().getCantidad() + comida.getCantidad());
            return;
        }
        if(this.data.getCurrentUser().getCarro().size()>3){
            this.info.setText("Cántidad máxima alcanzada");
            return;
        }
        this.data.getCurrentUser().getCarro().add(comida);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("creado");
        for (int i = 1; i <= 20; i++) {
            mostrarCantidad.getItems().add(i);
        }
        this.mostrarCantidad.setValue(1);

    }

    public void recibirData(Data data){
        this.data = data;
        this.mostrarComida.setImage(new Image(getClass().getResourceAsStream(this.data.getComidaSeleccionada().getFoto())));
        this.mostrarComida.setFitHeight(300);
        this.mostrarComida.setFitWidth(300);
    }

}

