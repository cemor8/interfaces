package com.example.biblioteca;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogin {

    @FXML
    private MFXButton btnContnuar;

    @FXML
    private Label infoContraseña;

    @FXML
    private Label infoUsuario;

    @FXML
    private MFXPasswordField introducirContraseña;

    @FXML
    private MFXTextField introducirUsuario;
    private Data data;

    @FXML
    void continuar(MouseEvent event) {
        this.data.setCurrentUser(new Usuario(this.introducirUsuario.getText()));
        Button btn = (Button) event.getSource();
        Stage stage= (Stage) btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("panel-view.fxml"));
        Parent root =null;
        try {
             root = fxmlLoader.load();
        }catch (IOException err ){
            System.out.println(err.getMessage());
        }
        ControllerPanelPrincipal controllerPanel = fxmlLoader.getController();
        //controllerPanel.establecerDatos(this.data);
        stage.setTitle("Panel");
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }
    public void recibirData(Data data){
        this.data = data;
    }

}
