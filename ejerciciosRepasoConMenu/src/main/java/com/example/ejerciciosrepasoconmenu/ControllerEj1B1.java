package com.example.ejerciciosrepasoconmenu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerEj1B1 {


    @FXML
    private TextField letra;

    @FXML
    private TextField palabra;

    @FXML
    private Label resultado;

    /**
     * Método que elimina el texto.
     * @param event event
     * */
    @FXML
    public void eliminar(MouseEvent event){
        this.letra.setText("");
        this.palabra.setText("");
        this.resultado.setText("");
    }
    /**
     * Método que se encarga de buscar las ocurrencias de una letra en un texto introducido
     * @param event event
     * */
    @FXML
    void buscar(MouseEvent event) {
        String texto = this.palabra.getText();
        String letra=this.letra.getText();
            Pattern pattern=Pattern.compile("[a-zA-z]");
            Matcher matcher=pattern.matcher(letra);
        if (letra.length() != 1 || !matcher.matches()) {
            this.resultado.setText("Introduce una letra");
            return;
            }
        int contador=0;
        int posicion=0;
        posicion=texto.indexOf(letra);
        while (posicion!=-1){
            contador+=1;
            posicion=texto.indexOf(letra,posicion+1);

        }
        if(contador>1){
            this.resultado.setText("La letra: "+letra+", aparece "+contador+" veces");
        }else if(contador==1){
            this.resultado.setText("La letra: "+letra+", aparece "+contador+" vez");
        }else{
            this.resultado.setText("La letra: "+letra+", no aparece en el texto");
        }

    }

}