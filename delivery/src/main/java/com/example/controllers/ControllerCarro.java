package com.example.controllers;

import com.example.modelo.Data;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControllerCarro {

    @FXML
    private MFXButton btnEliminar;

    @FXML
    private MFXButton btnPagar;
    private Data data = null;

    @FXML
    void eliminar(ActionEvent event) {

    }

    @FXML
    void pagar(ActionEvent event) {

    }
    public void recibirData(Data data){
        this.data = data;
    }

}

