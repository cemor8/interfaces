package com.example.controllers;

import com.example.delivery.MainApplication;
import com.example.modelo.Data;
import com.example.modelo.Usuario;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerRegistrar {
    @FXML
    private Label avisoApellidos;

    @FXML
    private Label avisoContraseña;

    @FXML
    private Label avisoCorreo;

    @FXML
    private Label avisoNombre;

    @FXML
    private MFXButton btnCrearCuenta;

    @FXML
    private MFXTextField introducirApellidos;

    @FXML
    private MFXTextField introducirContraseña;

    @FXML
    private MFXTextField introducirCorreo;

    @FXML
    private MFXTextField introducirNombre;
    private Data data = null;
    Map<String, String> columnasExpresiones = new HashMap<String, String>() {
        {
            put("Nombre", "^[A-Z][a-z]{3,20}$");
            put("Apellidos", "^[A-Z][a-z]+(\\s[A-Z][a-z]+)?$");
            put("Contraseña", "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
            put("Correo", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        }

    };

    @FXML
    void crearUsuario(ActionEvent event) {
        boolean error = false;
        System.out.println("hola");
        if(!validarContenido(columnasExpresiones.get("Nombre"),this.introducirNombre.getText())){
            System.out.println("mal nombre");
            this.avisoNombre.setText("Nombre inválido");
            error = true;
        }
        if(!validarContenido(columnasExpresiones.get("Apellidos"),this.introducirApellidos.getText())){
            System.out.println("mal apellidos");
            this.avisoApellidos.setText("Apellidos Inválidos");
            error = true;
        }
        if(!validarContenido(columnasExpresiones.get("Contraseña"),this.introducirContraseña.getText())){
            System.out.println("mal contraseña");
            this.avisoContraseña.setText("Contraseña inválida");
            error = true;
        }
        if(!validarContenido(columnasExpresiones.get("Correo"),this.introducirCorreo.getText())){
            System.out.println("mal correo");
            this.avisoCorreo.setText("Correo inválido");
            error = true;
        }
        if(error){
            return;
        }
        System.out.println("bien");
        Usuario usuarioCreado = new Usuario(this.introducirNombre.getText(),this.introducirApellidos.getText(),this.introducirCorreo.getText(),this.introducirContraseña.getText());
        this.data.getListaUsuarios().getUsuarios().add(usuarioCreado);
        this.volverLogin(event);
    }

    /**
     * Método que devuelve true si se cumple una expresion regular en una string
     *
     * @param patron       expresion regular
     * @param texto_buscar texto donde buscar el patron
     */
    public boolean validarContenido(String patron, String texto_buscar) {
        Pattern patronValidar = Pattern.compile(patron);
        Matcher matcher = patronValidar.matcher(texto_buscar);
        return matcher.matches();
    }
    public void volverLogin(Event event){
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
    public void recibirData(Data data) {
        this.data = data;
    }

}

