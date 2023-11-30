package com.example.controllers;

import com.example.delivery.MainApplication;
import com.example.modelo.Data;
import com.example.modelo.Restaurante;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPanel {

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
    /**
     * Método que se encarga de cerrar sesion para logearse con otro usuario
     *
     * */
    @FXML
    public void cerrarSesion(Event event) {
        this.data.setCurrentUser(null);
        AnchorPane img = (AnchorPane) event.getSource();
        Stage stage = (Stage) img.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            ControllerLogin controllerLogin = fxmlLoader.getController();
            controllerLogin.recibirData(this.data);
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
    /**
     * Método que cambia el contenido del contenedor anchorpane
     * */
    public void cambiarContenido(Parent nuevoContenido) throws IOException {
        this.mostrarContenido.getChildren().setAll(nuevoContenido);
    }
    /**
     * Método que se encarga de abrir una nueva ventana para mostrar el carrito
     * **/
    @FXML
    void mostrarCarrito(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("carrito.fxml"));
        Parent root = fxmlLoader.load();
        ControllerCarro controllerCarro = fxmlLoader.getController();
        controllerCarro.recibirData(this.data);
        stage.setTitle("Carrito");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                stage.hide();
            }
        });
        stage.show();
    }
    /**
     * Método que se encarga de cargar los datos en el controlador, tambien carga el fxml donde se encuentran los
     * restaurantes.
     * */
    public void establecerDatos(Data data, ControllerPanel controllerPanel) throws IOException {
        this.data = data;
        this.mostrarNombreUsuario.setText("Hola, " + this.data.getCurrentUser().getNombre());

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("crear-restaurantes.fxml"));
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ControllerMostrarRestaurantes controllerMostrarRestaurantes = fxmlLoader.getController();
        controllerMostrarRestaurantes.recibirData(this.data, controllerPanel);
        AnchorPane secondFXML = (AnchorPane) root;
        this.mostrarContenido.getChildren().setAll(secondFXML);

    }
    /**
     * Método que se encarga de volver al panel central.
     * */
    @FXML
    void volverInicio(Event event) {
        ImageView btn = (ImageView) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("panel-view.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            ControllerPanel controllerPanel = fxmlLoader.getController();
            controllerPanel.establecerDatos(this.data, controllerPanel);
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }

        stage.setTitle("Panel");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    /**
     * Método que se encarga de volver al panel central.
     * */
    @FXML
    void volver(MouseEvent event) {
        AnchorPane btn = (AnchorPane) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("panel-view.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
            ControllerPanel controllerPanel = fxmlLoader.getController();
            controllerPanel.establecerDatos(this.data, controllerPanel);
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }

        stage.setTitle("Panel");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }



    /**
     * Método que busca un restaurante aleatorio y muestra su menu en el panel.
     * */
    @FXML
    public void mostrarMenuAleatorio(Event event){

        Restaurante restauranteEncontrado = this.data.getListaRestaurantes().getListaRestaurantes().get((int) Math.floor(Math.random() * this.data.getListaRestaurantes().getListaRestaurantes().size()));
        this.data.setRestauranteSeleccionado(restauranteEncontrado);

        try {
            this.mostrarMenuRestaurante();
        }catch (IOException err){
            System.out.println(err.getMessage());
        }


    }
    /**
     * Método que se encarga de mostrar el menu del restaurante, cargando el fxml y luego
     * añadiendolo al anchorpane establecido como contenedor
     * **/
    public void mostrarMenuRestaurante() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mostrar-menu.fxml"));
        Parent menuRestaurante = fxmlLoader.load();

        ControllerMostrarMenu controllerMostrarMenu = fxmlLoader.getController();
        controllerMostrarMenu.recibirData(this.data);

        this.cambiarContenido(menuRestaurante);

    }
}

