package com.example.ejerciciosrepasoconmenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerB1 {

    @FXML
    private Button btnEjer1;

    @FXML
    private Button btnEjer2;

    @FXML
    private Button btnEjer3;

    @FXML
    private Button btnEjer4;

    @FXML
    private Button btnEjer6;

    @FXML
    private Button btnVolver;

    @FXML
    void cambiarEscena(MouseEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("menuB1.fxml"));
        stage.setTitle("Menu bloques");
        stage.setScene(new Scene(root));
    }
    @FXML
    void cambiarVentana(MouseEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage=new Stage();
        Parent root= null;
        switch (btn.getId()){
            case "btnEjer1":
                root = FXMLLoader.load(getClass().getResource("mainViewEj1B1.fxml"));
                stage.setTitle("Ejercicio1");
                break;
            case "btnEjer2":
                root = FXMLLoader.load(getClass().getResource("mainViewEj2B1.fxml"));
                stage.setTitle("Ejercicio2");
                break;
            case "btnEjer3":
                root = FXMLLoader.load(getClass().getResource("mainViewEj3B1.fxml"));
                stage.setTitle("Ejercicio3");
                break;
            case "btnEjer4":
                root = FXMLLoader.load(getClass().getResource("mainViewEj4B1.fxml"));
                stage.setTitle("Ejercicio4");
                break;
            case "btnEjer6":
                root = FXMLLoader.load(getClass().getResource("mainViewEj6B1.fxml"));
                stage.setTitle("Ejercicio6");
                break;
        }
        stage.setScene(new Scene(root));
        stage.show();
    }

}

