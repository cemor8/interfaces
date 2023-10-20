package com.example.ejer1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class HelloController{

    @FXML
    private Button botonBuscar;

    @FXML
    private TextField letra;

    @FXML
    private TextField palabra;

    @FXML
    private Label resultado;
    @FXML
    private Button botonBorrar;

    @FXML
    public void eliminar(MouseEvent event){
        this.letra.setText("");
        this.palabra.setText("");
        this.resultado.setText("");
    }

    @FXML
    void buscar(MouseEvent event) {
        String texto = this.palabra.getText();
        String letra=this.letra.getText();
        if (letra.length() != 1){
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
            this.resultado.setText("La letra: "+letra+" aparece "+contador+" veces");
        }else if(contador==1){
            this.resultado.setText("La letra: "+letra+" aparece "+contador+" vez");
        }else{
            this.resultado.setText("La letra: "+letra+" no aparece en el texto");
        }


    }

}