package com.example.gestionalumnos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApliGestion extends Application {
    private DatosAlumnos datosAlumnos;
    @Override
    public void start(Stage stage) throws IOException {
        this.datosAlumnos=cargarDatos();
        FXMLLoader fxmlLoader = new FXMLLoader(ApliGestion.class.getResource("mainView.fxml"));
        Parent root=fxmlLoader.load();
        ControllerGestion controllerGestion = fxmlLoader.getController();
        controllerGestion.establecerDatos(this.datosAlumnos);
        Scene scene = new Scene(root);
        stage.setTitle("Gestión de alumnos");
        stage.setScene(scene);
        stage.show();
    }
    private DatosAlumnos cargarDatos(){
        return new DatosAlumnos();
    }


    public static void main(String[] args) {
        launch();
    }
}