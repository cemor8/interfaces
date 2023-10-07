package com.example.demo3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class HelloController {


    @FXML
    private Label mostrarOperacion;

    @FXML
    private Label resultado;

    private ArrayList<String> operadores = new ArrayList<>();
    private ArrayList<String> numeros = new ArrayList<>();
    private String string = "";
    private String strinMostrar="";
    private boolean hayResultado=false;
    /**
     * Método que devuelve al valor inicial todas las variables
     * */
    @FXML
    void eliminar(MouseEvent event) {
        this.string="";
        this.numeros=new ArrayList<>();
        this.operadores=new ArrayList<>();
        this.strinMostrar="";
        this.resultado.setText("");
        this.mostrarOperacion.setText("");
        this.hayResultado=false;
    }
    /**
     * Método que mete el texto del evento el cual lo llama, este
     * se le asignara a todos los botones para que cuando lo llamen,
     * el texto del boton (el numero), se guarde en una string (por si el numero es mas grande que 9),
     * luego se guarda en la string para mostrar por pantalla.
     * */
    @FXML
    void meter(MouseEvent event) {
        if (hayResultado&&this.operadores.size()==0){
            this.eliminar(event);
            return;
        }
        Button btn = (Button) event.getSource();
        this.string += btn.getText();
        this.strinMostrar+=btn.getText();
        this.mostrarOperacion.setText(this.strinMostrar);
    }
    /**
     * Método que guarda un operador en la lista de operadores,
     * comprueba si la string donde se esta guardando el numero anterior al
     * operador no esta vacia, ya que si hemos realizado una operacion previamente
     * se podra meter un signo a continuacion y no un numero, por lo cual la string estará vacia,
     * si no esta vacia, añadimos el numero que ya estará completo, a la lista de los numeros.
     * */
    @FXML
    void meterSigno(MouseEvent event) {

        Button btn = (Button) event.getSource();
        this.operadores.add(btn.getText());
        this.strinMostrar+=btn.getText();
        this.mostrarOperacion.setText(this.strinMostrar);
        if(this.string.length()==0){
            return;
        }
        this.numeros.add(this.string);
        this.string = "";

    }
    /**
     * Método que se encarga de hacer las operaciones, como tiene que haber 1 operador
     * menos que la cantidad de numeros totales, se comprueba, ya que si no estará mal,
     * luego se recorre la lista de los operadores, para acceder al numero anterior a este, que sera
     * el que esta en la misma posicion que el operador en la lista de los numeros, y el que prosigue a este,
     * se accede a ellos y se opera, luego el numero siguiente pasa a ser el resultado de la operacion, por si
     * hay que seguir operando. Luego se guarda el resultado en la lista de los numeros, se muestra y se vacia la lista
     * de operadores, tambien se reinicia el valor de la string donde se guarda el numero.
     * */
    @FXML
    void operar(MouseEvent event) {
        this.numeros.add(this.string);
        this.string = "";
        if ((this.operadores.size() != (this.numeros.size() - 1)) || this.operadores.size()==0) {
            this.eliminar(event);
            return;
        }
        for (int i = 0; i < this.operadores.size(); i++) {
            Double num1;
            Double num2;
            try {
                 num1 = Double.parseDouble(this.numeros.get(i));
                 num2 = Double.parseDouble(this.numeros.get(i + 1));
            }catch (Exception err){
                this.eliminar(event);
                this.mostrarOperacion.setText("mal");
                return;
            }
            switch (this.operadores.get(i)) {
                case "-" -> {
                    this.numeros.set(i + 1, String.valueOf(num1 - num2));
                    this.resultado.setText(String.valueOf(num1 - num2));
                }
                case "+" -> {
                    this.numeros.set(i + 1, String.valueOf(num1 + num2));
                    this.resultado.setText(String.valueOf(num1 + num2));
                }
                case "*" -> {
                    this.numeros.set(i + 1, String.valueOf(num1 * num2));
                    this.resultado.setText(String.valueOf(num1 * num2));
                }
                case "/" -> {
                    this.numeros.set(i + 1, String.valueOf(num1 / num2));
                    this.resultado.setText(String.valueOf(num1 / num2));
                }
            }
        }
        hayResultado=true;
        this.strinMostrar=this.resultado.getText();
        this.string="";
        this.mostrarOperacion.setText(this.strinMostrar);
        this.operadores=new ArrayList<>();
        this.numeros=new ArrayList<>(List.of(this.resultado.getText()));
    }

}