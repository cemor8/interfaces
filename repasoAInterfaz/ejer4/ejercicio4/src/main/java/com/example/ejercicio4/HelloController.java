package com.example.ejercicio4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.List;

public class HelloController {

    @FXML
    private TextField nombreBuscar;
    @FXML
    private Label labelNota;
    private HashMap<String,Double> listaEstudiantes=new HashMap<String,Double>(){{
        put("Carlos",10.0);
        put("Pepe",9.5);
        put("Juan",9.0);
        put("Alex",8.5);
    }};

    @FXML
    void buscarEstudiante(MouseEvent event) {
        if(this.listaEstudiantes.containsKey(this.nombreBuscar.getText())){
            this.labelNota.setText(this.listaEstudiantes.get(this.nombreBuscar.getText()));
        }

    }

}

