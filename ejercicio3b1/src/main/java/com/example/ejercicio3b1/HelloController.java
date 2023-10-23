package com.example.ejercicio3b1;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController {

    @FXML
    private Button botonBorrar;

    @FXML
    private TextField introducirLista;

    @FXML
    private TextField numeroBorrar;

    @FXML
    private Button buttonElimiarEnLista;

    @FXML
    private Label mostrarLista;

    @FXML
    void borrarOcurrencias(MouseEvent event) {
        Pattern patron= Pattern.compile("^\\d+(?:,\\d+)*$");
        Matcher matcher= patron.matcher(this.introducirLista.getText());
        if(!matcher.matches()){
            this.eliminar(event);
            this.mostrarLista.setText("Error al introducir la lista");
            return;
        }
        String[] numeros=this.introducirLista.getText().split(",");
        this.mostrarLista.setText("");
        for(int i=0;i<numeros.length;i++){

            if(numeros[i].equals(this.numeroBorrar.getText())){

                continue;
            }
            this.mostrarLista.setText(this.mostrarLista.getText()+numeros[i]+" ");
        }

    }

    @FXML
    void eliminar(MouseEvent event) {
        this.mostrarLista.setText("");
        this.introducirLista.setText("");
        this.numeroBorrar.setText("");
    }

}