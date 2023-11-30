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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private MFXButton btnRegistrarse;

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
        MFXButton btn= (MFXButton) event.getSource() ;
        boolean error = false;
        if (!validarContenido(this.columnasExpresiones.get("Correo"), this.introducirCorreo.getText())) {
            this.infoCorreo.setText("Correo inválido");
            this.introducirCorreo.setText("");
            error = true;
        }
        if (!validarContenido(this.columnasExpresiones.get("Contraseña"), this.introducirContraseña.getText())) {
            this.infoContraseña.setText("Contraseña inválida");
            this.introducirContraseña.setText("");
            error = true;
        }
        if (error) {
            return;
        }
        Optional<Usuario> usuarioEncontrado = this.data.getListaUsuarios().getUsuarios().stream().filter(usuario -> usuario.getCorreo().equalsIgnoreCase(this.introducirCorreo.getText()) &&
                usuario.getClave().equalsIgnoreCase(this.introducirContraseña.getText())).findFirst();
        if (usuarioEncontrado.isEmpty()) {
            this.infoCorreo.setText("Credenciales inválidas");
            this.introducirContraseña.setText("");
            this.introducirCorreo.setText("");
            return;
        }
        Usuario usuario = usuarioEncontrado.get();
        this.data.setCurrentUser(usuario);
        try {
            this.cambiarVentana(btn);
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }

    }
    /**
     * Método que carga la vista para poder registrar un usuario
     * */
    @FXML
    void registrarse(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage= (Stage) btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("registrar-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            ControllerRegistrar controllerRegistrar = fxmlLoader.getController();
            controllerRegistrar.recibirData(this.data);
            stage.setTitle("Registrarse");
            stage.setScene(new Scene(root));
        }catch (IOException err){
            System.out.println(err.getMessage());
        }
    }
    /**
     * Método que se encarga de recibir la informacion para
     * cargarla en el controlador
     * */
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
    public void cambiarVentana(MFXButton btn) throws IOException {
        Stage stage= (Stage) btn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("panel-view.fxml"));
        Parent root = fxmlLoader.load();
        ControllerPanel controllerPanel = fxmlLoader.getController();
        controllerPanel.establecerDatos(this.data,controllerPanel);
        stage.setTitle("Panel");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/estilos_restaurantes.css").toExternalForm());
        stage.setScene(scene);
    }
    /**
     * Método que se encarga de eliminar el texto cuando se introduce informacion erronea
     * */
    public void eliminarTexto(){
        this.infoCorreo.setText("");
        this.infoContraseña.setText("");
    }
    /**
     * Método que se encarga de llamar al metodo que elimina el texto de notificaion.
     * */
    @FXML
    void eliminarInfo(KeyEvent event) {
        this.eliminarTexto();
    }

}
