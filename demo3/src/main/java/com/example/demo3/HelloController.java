package com.example.demo3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HelloController {
    @FXML
    private Label operacion;
    @FXML
    private Button boton_prueba;

    @FXML
    private void onMove(MouseEvent event) {
        operacion.setText("hola");
    }
    @FXML
    private void eliminar(MouseEvent event) {
        operacion.setText("eliminar");
    }

    @FXML
    private void igual(MouseEvent event) {
        operacion.setText("igual");
    }

    @FXML
    private void restar(MouseEvent event) {
        operacion.setText("restar");
    }

    @FXML
    private void sumar(MouseEvent event) {
        operacion.setText("sumar");
    }

}