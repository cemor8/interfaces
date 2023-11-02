package com.example.ejerciciosrepasoconmenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApliAgenda extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApliAgenda.class.getResource("mainViewAgenda.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Agenda");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}