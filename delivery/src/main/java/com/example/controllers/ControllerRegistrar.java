package com.example.controllers;

import com.example.modelo.Usuario;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
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
    Map<String, String> columnasExpresiones = new HashMap<String, String>() {
        {
            put("Nombre", "[a-zA-Z]");
            put("Apellidos", "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
            put("Contraseña", "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
            put("Correo", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        }

    };

    @FXML
    void crearUsuario(ActionEvent event) {
        boolean error = false;
        if(!validarContenido("Nombre",this.introducirNombre.getText())){
            this.avisoNombre.setText("Nombre inválido");
            error = true;
        }
        if(!validarContenido("Apellidos",this.introducirApellidos.getText())){
            this.avisoApellidos.setText("Apellidos Inválidos");
            error = true;
        }
        if(!validarContenido("Contraseña",this.introducirContraseña.getText())){
            this.avisoContraseña.setText("Contraseña inválida");
            error = true;
        }
        if(!validarContenido("Correo",this.introducirCorreo.getText())){
            this.avisoNombre.setText("Correo inválido");
            error = true;
        }
        if(error){
            return;
        }
        Usuario usuarioCreado = new Usuario(this.avisoNombre.getText(),this.introducirApellidos.getText(),this.introducirCorreo.getText(),this.introducirContraseña.getText());

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

}

