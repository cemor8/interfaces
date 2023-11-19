package com.example.delivery;

import com.example.controllers.ControllerLogin;
import com.example.modelo.Data;
import com.example.modelo.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Data data = new Data();
        data.getListaRestaurantes().inicializarRestaurantes();
        data.getListaUsuarios().getUsuarios().add(new Usuario("Carlos","Morais","carlosmoraisblanco@gmail.com","Carlos12"));
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ControllerLogin controllerLogin = fxmlLoader.getController();
        controllerLogin.recibirData(data);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}