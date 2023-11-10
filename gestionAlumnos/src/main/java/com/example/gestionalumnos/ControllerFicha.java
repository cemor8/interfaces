package com.example.gestionalumnos;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerFicha {

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
    private TableView<Alumno> tabla;
    /**
     * Método que se encarga de comprobar los requisitos dependiendo de la funcion de la ventana,
     * si la ventana es para añadir un nuevo alumno, todos los campos deben de estar cubiertos y correctos.
     * Si la ventana se abre para modificar un alumno, comprueba si los datos que se han introducido son válidos
     * y modifica el alumno, si no se introduce algun dato, se dejara el anterior valor.
     * */
    @FXML
    void enviar(MouseEvent event) {
        if (this.alumno == null) {
            if (!validarContenido("^[A-Z][a-z]{3,20}$", this.introducirNombre.getText()) || !validarContenido("^[A-Z][a-z]+(\\s[A-Z][a-z]+)?$", this.introducirApellidos.getText()) ||
                    !validarContenido("^\\d{3}-\\d{2}-\\d{2}-\\d{2}$", this.introducirTelef.getText()) ||
                    !validarContenido("^([0-9]{1}([.][0-9]{1,2})?|10)$", this.introducirNota.getText()) ||
                    this.introducirCiclo.getValue() == null || !calcularDni()
            ) {
                return;
            }else if(!this.comprobarIgualdad()){
                return;
            }
            Alumno alumnoCreado = new Alumno(this.introducirNombre.getText(), this.introducirApellidos.getText(),
                    this.introducirCiclo.getValue(), Double.parseDouble(this.introducirNota.getText()), this.introducirDni.getText(), this.introducirTelef.getText());
            this.datosAlumnos.meterAlumno(alumnoCreado);
        }else {
            if (validarContenido("^[A-Z][a-z]{3,20}$", this.introducirNombre.getText())) {
                this.alumno.setNombre(this.introducirNombre.getText());

            }
            if (validarContenido("^[A-Z][a-z]+(\\s[A-Z][a-z]+)?$", this.introducirApellidos.getText())) {
                this.alumno.setApellidos(this.introducirApellidos.getText());
            }
            if (calcularDni() && this.comprobarIgualdad()) {
                this.alumno.setDni(this.introducirDni.getText());
            }
            if (validarContenido("^\\d{3}-\\d{2}-\\d{2}-\\d{2}$", this.introducirTelef.getText())&& this.comprobarIgualdad()) {
                this.alumno.setNum_telefono(this.introducirTelef.getText());

            }
            if (validarContenido("^([0-9]{1}([.][0-9]{1,2})?|10)$", this.introducirNota.getText())) {
                this.alumno.setNotaMedia(Double.parseDouble(this.introducirNota.getText()));

            }
            if (this.introducirCiclo.getValue() != null) {
                this.alumno.setCiclo(this.introducirCiclo.getValue());
            }
        }
        this.tabla.refresh();
        this.alumno=null;
        this.datosAlumnos.setAlumnoSeleccionado(null);
    }
    /**
     * Método que devuelve true si se cumple una expresion regular en una string
     * @param patron expresion regular
     * @param texto_buscar texto donde buscar el patron
     * */
    public boolean validarContenido(String patron, String texto_buscar) {
        Pattern patronValidar = Pattern.compile(patron);
        Matcher matcher = patronValidar.matcher(texto_buscar);
        return matcher.matches();
    }
    /**
     * Método que se encarga de comprobar la validez de un DNI español.
     * */
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
    /**
     * Método que se encarga de recibir los datos para trabajar con ellos.
     * */
    public void establecerDatos(DatosAlumnos datosAlumnos){
        this.datosAlumnos=datosAlumnos;
        this.alumno=this.datosAlumnos.getAlumnoSeleccionado();
        this.comprobarAlumno();
    }
    /**
     * Método que comprueba si hay un alumno seleccionado, si lo hay muestra sus datos como placeholder, si no lo hay,
     * muestra el formato de los datos a introducir.
     * */
    public void comprobarAlumno(){
        this.introducirCiclo.getItems().addAll("Desarrollo de Aplicaciones Multiplataforma", "Desarrollo de Aplicaciones Web", "Sistemas Microinformáticos y Redes");
        this.introducirNombre.setStyle("-fx-prompt-text-fill: #333333; -fx-font-style: italic;");
        this.introducirApellidos.setStyle("-fx-prompt-text-fill: #333333; -fx-font-style: italic;");
        this.introducirDni.setStyle("-fx-prompt-text-fill: #333333; -fx-font-style: italic;");
        this.introducirTelef.setStyle("-fx-prompt-text-fill: #333333; -fx-font-style: italic;");
        this.introducirCiclo.setStyle("-fx-prompt-text-fill: #333333; -fx-font-style: italic;");
        if (this.alumno == null) {
            this.introducirNombre.setPromptText("Nombre entre 3-20 caracteres");
            this.introducirApellidos.setPromptText("Apellidos separados por espacio");
            this.introducirDni.setPromptText("Dni tiene que ser válido");
            this.introducirTelef.setPromptText("Formato xxx-xx-xx-xx");
            this.introducirCiclo.setPromptText("Despliega para elejir ciclo");
        } else {
            this.introducirNombre.setPromptText(this.alumno.getNombre());
            this.introducirApellidos.setPromptText(this.alumno.getApellidos());
            this.introducirDni.setPromptText(this.alumno.getDni());
            this.introducirTelef.setPromptText(this.alumno.getNum_telefono());
            this.introducirCiclo.setPromptText(this.alumno.getCiclo());
            this.introducirNota.setPromptText(String.valueOf(this.alumno.getNotaMedia()));
        }
    }
    /**
     * Método que se encarga de recibir una tabla para trabajar con ella.
     * @param tabla tabla de datos a mostrar.
     * */
    public void recibirTabla(TableView<Alumno> tabla){
        this.tabla=tabla;
    }
    /**
     * Método que se encarga de comprobar si ya hay algun alumno con el mismo Dni o numero de
     * telefono en la lista de alumnos.
     * @return boolean
     * */
    public boolean comprobarIgualdad(){
        return this.datosAlumnos.getListaAlumnos().stream().noneMatch(alumno1 -> alumno1.getDni().equalsIgnoreCase(this.introducirDni.getText())) &&
                this.datosAlumnos.getListaAlumnos().stream().noneMatch(alumno1 -> alumno1.getNum_telefono().equalsIgnoreCase(this.introducirTelef.getText()));
    }
}
