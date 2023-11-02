package com.example.ejercicio3b1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerEj3B1 {

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
        List<String> numeros=new ArrayList<>(Arrays.asList(this.introducirLista.getText().split(","))) ;
        this.mostrarLista.setText("");
        for(int i=numeros.size()-1;i>=0;i--){

            if(numeros.get(i).equals(this.numeroBorrar.getText())){
                numeros.remove(numeros.get(i));
            }
        }
        this.mostrarLista.setText(String.join(",",numeros));

    }

    @FXML
    void eliminar(MouseEvent event) {
        this.mostrarLista.setText("");
        this.introducirLista.setText("");
        this.numeroBorrar.setText("");
    }

}