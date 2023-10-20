package com.example.ejercicio3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class HelloController {

    @FXML
    private Button botonBorrar;

    @FXML
    private TextField introducirLista;

    @FXML
    private TextField numeroBorrar;

    @FXML
    private Button meterNumero;

    @FXML
    private Label mostrarLista;
    private ArrayList<Integer> ints = new ArrayList<>();

    @FXML
    void borrarOcurrencias(MouseEvent event) {
        String[] numeros=this.introducirLista.getText().split(",");
        for (String cadaNumero : numeros){
            try {
                Integer.parseInt(cadaNumero);
            } catch (NumberFormatException err) {
                this.eliminar(event);
                return;
            }
            this.ints.add(Integer.parseInt(cadaNumero));
        }
        Integer numeroABorrar;
        try {
            numeroABorrar=Integer.valueOf(this.numeroBorrar.getText());
        }catch (NumberFormatException err){
            return;
        }
        for(int i=0;i<this.ints.size();i++){

            if(this.ints.get(i).equals(numeroABorrar)){
                continue;
            }
            this.mostrarLista.setText(this.mostrarLista.getText()+this.ints.get(i)+" ");
        }

    }

    @FXML
    void eliminar(MouseEvent event) {
        this.ints = new ArrayList<>();
        this.mostrarLista.setText("");
        this.introducirLista.setText("");
        this.numeroBorrar.setText("");
    }

}


