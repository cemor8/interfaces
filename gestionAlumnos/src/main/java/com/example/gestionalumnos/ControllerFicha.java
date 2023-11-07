package com.example.gestionalumnos;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerFicha implements Initializable {

    @FXML
    private Button btnEnviar;

    @FXML
    private TextField introducirApellidos;

    @FXML
    private TextField introducirDni;

    @FXML
    private TextField introducirNombre;

    @FXML
    private TextField introducirNota;

    @FXML
    private TextField introducirTelef;
    @FXML
    private ComboBox<String> introducirCiclo;
    private Alumno alumno = null;
    private DatosAlumnos datosAlumnos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.introducirCiclo.getItems().addAll("Desarrollo de Aplicaciones Multiplataforma", "Desarrollo de Aplicaciones Web", "Sistemas Microinformáticos y Redes");
        if (this.alumno == null) {
            this.introducirNombre.setPromptText("Nombre entre 3-20 caracteres");
            this.introducirApellidos.setPromptText("Apellidos separados por espacio");
            this.introducirDni.setPromptText("Dni tiene que ser válido");
            this.introducirTelef.setPromptText("formato xxx-xx-xx-xx");
            this.introducirCiclo.setPromptText("Despliega para seleccionar ciclo");
        } else {
            String[] nombreApellidos = this.alumno.getNombre().split(" ");
            String nombre = nombreApellidos[0];
            StringBuilder apellidos = new StringBuilder();
            for (int i = 1; i < nombreApellidos.length; i++) {
                apellidos.append(nombreApellidos[i]).append(" ");
            }
            apellidos = new StringBuilder(apellidos.toString().trim());
            this.introducirNombre.setPromptText(nombre);
            this.introducirApellidos.setPromptText(apellidos.toString());
            this.introducirDni.setPromptText(this.alumno.getDni());
            this.introducirTelef.setPromptText(this.alumno.getNum_telefono());
            this.introducirCiclo.setPromptText(this.alumno.getCiclo());
            this.introducirNota.setPromptText(String.valueOf(this.alumno.getNotaMedia()));
        }
    }

    @FXML
    void enviar(MouseEvent event) {
        System.out.println("empieza enviar");
        if (this.alumno == null) {
            if (!validarContenido("^[A-Z][a-z]{3,20}$", this.introducirNombre.getText()) || !validarContenido("^[A-Z][a-z]+(\\s[A-Z][a-z]+)?$", this.introducirApellidos.getText()) ||
                    !validarContenido("^\\d{3}-\\d{2}-\\d{2}-\\d{2}$", this.introducirTelef.getText()) ||
                    !validarContenido("^([0-9]{1}([.][0-9]{1,2})?|10)$", this.introducirNota.getText()) ||
                    this.introducirCiclo.getValue() == null || !calcularDni()
            ) {
                return;
            }
            Alumno alumnoCreado = new Alumno(this.introducirNombre.getText(), this.introducirApellidos.getText(),
                    this.introducirCiclo.getValue(), Double.parseDouble(this.introducirNota.getText()), this.introducirDni.getText(), this.introducirTelef.getText());
            this.datosAlumnos.meterAlumno(alumnoCreado);

        }

        if (validarContenido("^[A-Z][a-z]{3,20}$", this.introducirNombre.getText())) {
            this.alumno.setNombre(this.introducirNombre.getText());

        }
        if (validarContenido("^[A-Z][a-z]+\\\\s[A-Z][a-z]+$", this.introducirApellidos.getText())) {
            this.alumno.setApellidos(this.introducirApellidos.getText());

        }
        if (calcularDni()) {
            this.alumno.setDni(this.introducirDni.getText());
        }
        if (validarContenido("^\\d{3}-\\d{2}-\\d{2}-\\d{2}$", this.introducirTelef.getText())) {
            this.alumno.setNum_telefono(this.introducirTelef.getText());

        }
        if (validarContenido("(?:10|\\d(?:\\.\\d{1,2})?)", this.introducirNota.getText())) {
            this.alumno.setNotaMedia(Double.parseDouble(this.introducirNota.getText()));

        }
        if (this.introducirCiclo.getValue() != null) {
            this.alumno.setCiclo(this.introducirCiclo.getValue());
        }
    }

    public void recibirAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public boolean validarContenido(String patron, String texto_buscar) {
        Pattern patronValidar = Pattern.compile(patron);
        Matcher matcher = patronValidar.matcher(texto_buscar);
        return matcher.matches();
    }

    public boolean calcularDni() {
        ArrayList<String> letras = new ArrayList<>(List.of("T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K"));
        if (!validarContenido("^[0-9]{8}+[A-Z]{1}$", this.introducirDni.getText())) {
            return false;
        }
        String numeroString = this.introducirDni.getText().substring(0, 8);
        int numeroDni = Integer.parseInt(numeroString);
        String letra = letras.get(numeroDni % 23);
        return letra.charAt(0) == (this.introducirDni.getText().charAt(8));
    }
    public void establecerDatos(DatosAlumnos datosAlumnos){
        this.datosAlumnos=datosAlumnos;
    }

}
