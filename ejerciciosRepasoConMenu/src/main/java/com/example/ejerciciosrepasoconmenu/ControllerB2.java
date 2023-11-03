package com.example.ejerciciosrepasoconmenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerB2 {

    @FXML
    private Button btnAgenda;

    @FXML
    private Button btnPrimitiva;

    @FXML
    private Button btnVolver;

    @FXML
    void cambiarEscena(MouseEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setTitle("Menu bloques");
        stage.setScene(new Scene(root));
    }
    @FXML
    void cambiarVentana(MouseEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage=new Stage();
        Parent root= null;
        switch (btn.getId()){
            case "btnAgenda":
                root = FXMLLoader.load(getClass().getResource("mainViewAgenda.fxml"));
                stage.setTitle("Ejericicio Agenda");
                break;
            case "btnPrimitiva":
                root = FXMLLoader.load(getClass().getResource("mainViewPrimitiva.fxml"));
                stage.setTitle("Ejercicio primitiva");
                break;
        }
        stage.setScene(new Scene(root));
        stage.show();
    }

}

