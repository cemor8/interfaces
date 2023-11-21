package com.example.controllers;

import com.example.delivery.MainApplication;
import com.example.modelo.Data;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPanel {

    @FXML
    private MFXButton btnCarro;

    @FXML
    private MFXButton btnCerrarSesion;

    @FXML
    private MFXCheckbox filtrarAlfabetico;

    @FXML
    private MFXCheckbox filtrarPopulares;

    @FXML
    private MFXCheckbox filtrarPrecio;

    @FXML
    private AnchorPane mostrarContenido;

    @FXML
    private AnchorPane mostrarFiltros;
    @FXML
    private Label mostrarNombreUsuario;

    @FXML
    private AnchorPane mostrarTopBar;
    private Data data = null;

    @FXML
    void cerrarSesion(ActionEvent event) {
        this.data.setCurrentUser(null);
        Button btn = (Button) event.getSource();
        Stage stage= (Stage) btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            ControllerLogin controllerLogin = fxmlLoader.getController();
            controllerLogin.recibirData(this.data);
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }

    public void cambiarContenido(Parent nuevoContenido) throws IOException {
        this.mostrarContenido.getChildren().setAll(nuevoContenido);
    }

    @FXML
    void mostrarCarrito(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("carrito.fxml"));
        Parent root =fxmlLoader.load();
        ControllerCarro controllerCarro = fxmlLoader.getController();
        controllerCarro.recibirData(this.data);
        stage.setTitle("Carrito");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (! isNowFocused) {
                stage.hide();
            }
        });
        stage.show();
    }

    public void establecerDatos(Data data, ControllerPanel controllerPanel) throws IOException {
        this.data = data;
        this.mostrarNombreUsuario.setText("Hola, "+this.data.getCurrentUser().getNombre());

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("crear-restaurantes.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ControllerMostrarRestaurantes controllerMostrarRestaurantes = fxmlLoader.getController();
        controllerMostrarRestaurantes.recibirData(this.data,controllerPanel);
        AnchorPane secondFXML = (AnchorPane) root;
        this.mostrarContenido.getChildren().setAll(secondFXML);

    }
    @FXML
    void volverInicio(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage= (Stage) btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("panel-view.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            ControllerPanel controllerPanel = fxmlLoader.getController();
            controllerPanel.establecerDatos(this.data,controllerPanel);
        }catch (IOException err){
            System.out.println(err.getMessage());
        }

        stage.setTitle("Panel");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


}

