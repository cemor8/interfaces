package com.example.ejercicio6b1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApliEj6B1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApliEj6B1.class.getResource("mainViewEj6B1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ejercicio 6 bloque 1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}