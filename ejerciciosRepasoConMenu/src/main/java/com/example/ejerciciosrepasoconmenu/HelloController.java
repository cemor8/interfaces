package com.example.ejerciciosrepasoconmenu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button botonMenu1;

    @FXML
    private Button botonMenu2;

    @FXML
    void cambiarEscena(MouseEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        Parent root = null;
        switch (btn.getId()) {
            case "botonMenu1":
                root = FXMLLoader.load(getClass().getResource("menuB1.fxml"));
                break;
            case "botonMenu2":
                root = FXMLLoader.load(getClass().getResource("menuB2.fxml"));
                break;
        }

        stage.setTitle("Menu bloques");
        stage.setScene(new Scene(root));
    }

}
