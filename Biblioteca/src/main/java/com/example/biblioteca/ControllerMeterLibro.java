package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControllerMeterLibro {

    @FXML
    private MFXButton btnEnviar;

    @FXML
    private MFXTextField introducirAutor;

    @FXML
    private MFXTextField introducirAño;

    @FXML
    private MFXTextField introducirIsbn;

    @FXML
    private MFXTextField introducirNombre;

    @FXML
    private Label labelAutor;

    @FXML
    private Label labelAño;

    @FXML
    private Label labelIsbn;

    @FXML
    private Label labelNombre;
    private Data data;

    @FXML
    void enviar(MouseEvent event) {

    }
    public void recibirData(Data data){
        this.data = data;
    }

}

