package com.example.escenas;

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
    void cambiarStage(MouseEvent event) throws IOException {
        /**
         * Usamos la misma stage (ventana) que estabamos usando, basicamente le cambio la escena y su contenido que es el fxml
         * a la scene
         * */
        /*
        Button btn= (Button) event.getSource() ;
        Stage stage= (Stage) btn.getScene().getWindow();
        Parent root= FXMLLoader.load(getClass().getResource("hello-view-fxml1.fxml"));
        stage.setTitle("stage 2");
        stage.setScene(new Scene(root));

         */
        //para hacer ventanas nuevas.
        /*
        Stage stage=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("hello-view-fxml1.fxml"));
        stage.setTitle("stage 2");
        stage.setScene(new Scene(root));
        stage.show();

         */

    }

}

