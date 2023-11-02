package com.example.ejerciciosrepasoconmenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApliEj1B1 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApliEj1B1.class.getResource("mainViewEj1B1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Ejercicio1 bloque 1");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}