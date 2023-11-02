package com.example.ejercicio4b1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApliEj4B1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApliEj4B1.class.getResource("mainViewEj4B1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ejercicio 4 bloque 1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}