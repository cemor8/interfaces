package com.example.controllers;

import com.example.delivery.MainApplication;
import com.example.modelo.Data;
import com.example.modelo.Usuario;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerLogin {
    @FXML
    private Label infoContraseña;

    @FXML
    private Label infoCorreo;

    @FXML
    private MFXButton btnContnuar;

    @FXML
    private MFXTextField introducirContraseña;

    @FXML
    private MFXTextField introducirCorreo;
    private Data data = null;
    Map<String, String> columnasExpresiones = new HashMap<String, String>() {
        {
            put("Contraseña", "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
            put("Correo", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        }

    };

    /**
     * Método que comprueba el login, si es correcto, lleva a la pantalla del panel al usuario.
     */
    @FXML
    void enviarCredenciales(ActionEvent event) {
        boolean error = false;
        if (!validarContenido("Correo", this.introducirCorreo.getText())) {
            this.infoCorreo.setText("Correo inválido");
            error = true;
        }
        if (!validarContenido("Contraseña", this.introducirContraseña.getText())) {
            this.infoContraseña.setText("Contraseña inválida");
            error = true;
        }
        if (error) {
            return;
        }
        Optional<Usuario> usuarioEncontrado = this.data.getListaUsuarios().getUsuarios().stream().filter(usuario -> usuario.getCorreo().equalsIgnoreCase(this.introducirCorreo.getText()) &&
                usuario.getClave().equalsIgnoreCase(this.introducirContraseña.getText())).findFirst();
        if (usuarioEncontrado.isEmpty()) {
            this.infoCorreo.setText("Credenciales inválidas");
            return;
        }
        Usuario usuario = usuarioEncontrado.get();
        this.data.setCurrentUser(usuario);
        try {
            this.cambiarVentana();
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }

    }

    public void recibirData(Data data) {
        this.data = data;
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

    /**
     * Método que se encarga de abrir una nueva ventana y cargar los datos necesarios
     * para el correcto funcionamiento de esta.
     */
    public void cambiarVentana() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("panel-view.fxml"));
        Parent root = fxmlLoader.load();
        ControllerPanel controllerPanel = fxmlLoader.getController();
        //controllerPanel.establecerDatos(this.data);
        stage.setTitle("Ficha");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}
