package com.example.ejerciciosrepasoconmenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerEj4B1 {

    @FXML
    private Button btnAñadir;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnBuscar;
    @FXML
    private Label mostrarNota;

    @FXML
    private TextField nombreAñadir;

    @FXML
    private TextField nombreBuscar;

    @FXML
    private TextField notaAñadir;
    private HashMap<String,Double> listaEstudiantes= new HashMap<>();
    public boolean validarExpresion(String patronBuscar,String texto){
        Pattern patron= Pattern.compile(patronBuscar);
        Matcher matcher= patron.matcher(texto);
        return matcher.matches();
    }
    /**
     * Método que se encarga de añadir un alumno a la lista de alumnos.
     * Comprueba sus valores para comprobar que son válidos
     * @param event event
     * */
    @FXML
    void añadir(MouseEvent event) {
        if(!validarExpresion("^[A-Za-z]{4,10}$",this.nombreAñadir.getText())||
        !validarExpresion("[0-9]+([.,][0-9]+)?",this.notaAñadir.getText())){
            this.mostrarNota.setText("Datos invalidos");
            this.nombreAñadir.setText("");
            this.notaAñadir.setText("");
            return;
        }
        this.listaEstudiantes.put(this.nombreAñadir.getText(),Double.valueOf(this.notaAñadir.getText()));
        this.mostrarNota.setText("");
        this.nombreAñadir.setText("");
        this.notaAñadir.setText("");

    }
    /**
     * Método que se encarga de buscar un alumno en la lista para devolver su nota.
     * Comprueba que sus datos sean válidos.
     * @param event event
     * */
    @FXML
    void buscar(MouseEvent event) {
        if(!validarExpresion("^[A-Za-z]{4,10}$",this.nombreBuscar.getText())){
            this.mostrarNota.setText("Datos invalidos");
            this.nombreBuscar.setText("");
            return;
        }
        this.mostrarNota.setText("");
        Double nota=this.listaEstudiantes.get(this.nombreBuscar.getText());
        if(nota!=null){
            this.mostrarNota.setText(String.valueOf(nota));
        }else {
            this.nombreBuscar.setText("");
            this.mostrarNota.setText("Nombre no encontrado en la lista");
        }
    }

    @FXML
    void eliminar(MouseEvent event) {
        this.listaEstudiantes=new HashMap<>();
        this.nombreBuscar.setText("");
        this.mostrarNota.setText("");
        this.nombreAñadir.setText("");
        this.notaAñadir.setText("");
    }

}
