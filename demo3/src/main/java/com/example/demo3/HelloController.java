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
    private Button botonBorrar;

    @FXML
    private Button botonIgual;

    @FXML
    private Button botonMult;

    @FXML
    private Button botonRestar;

    @FXML
    private Button botonSumar;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Label mostrarOperacion;

    @FXML
    private Label resultado;
    //private String operacion = "";
    private String operador = "\\+";
    private ArrayList<String> operadores = new ArrayList<>();
    private ArrayList<String> operacion = new ArrayList<>();
    private String string = "";

    @FXML
    void eliminar(MouseEvent event) {




        /*
        this.operador = "";
        this.operacion = "";
        this.mostrarOperacion.setText("");
        */

    }

    @FXML
    void meter(MouseEvent event) {

        Button btn = (Button) event.getSource();
        this.string += btn.getText();
        this.mostrarOperacion.setText(this.mostrarOperacion.getText() + this.string);

        /*
        Button btn = (Button) event.getSource();
        this.operacion += btn.getText();
        this.mostrarOperacion.setText(operacion);
        */

    }

    @FXML
    void meterSigno(MouseEvent event) {

        Button btn = (Button) event.getSource();
        this.operacion.add(this.string);
        this.operador = btn.getText();
        this.operadores.add(btn.getText());
        this.string = "";
        this.mostrarOperacion.setText(this.operacion.toString() + btn.getText());


        /*
        Button btn = (Button) event.getSource();
        this.operador = btn.getText();
        this.operacion += btn.getText();
        this.mostrarOperacion.setText(operacion);
        */

    }

    @FXML
    void operar(MouseEvent event) {
        this.operacion.add(this.string);
        this.string = "";
        System.out.println(this.operadores.size());
        System.out.println(this.operadores);
        System.out.println(this.operacion.size());
        System.out.println(this.operacion);
        if (this.operadores.size() != (this.operacion.size() - 1)) {
            this.resultado.setText("mal");
            return;
        }
        for (int i = 0; i < this.operadores.size(); i++) {
            Double num1 = Double.parseDouble(this.operacion.get(i));
            Double num2 = Double.parseDouble(this.operacion.get(i + 1));

            switch (this.operadores.get(i)) {
                case "-" :
                    this.operacion.set(i+1,String.valueOf(num1 - num2));
                    this.resultado.setText(String.valueOf(num1 - num2));
                    break;
                case "+" :
                    this.operacion.set(i+1,String.valueOf(num1 + num2));
                    this.resultado.setText(String.valueOf(num1 + num2));
                    break;
                case "*" :
                    this.operacion.set(i+1,String.valueOf(num1 * num2));
                    this.resultado.setText(String.valueOf(num1 * num2));
                    break;
                case "/" :
                    this.operacion.set(i+1,String.valueOf(num1 / num2));
                    this.resultado.setText(String.valueOf(num1 / num2));
                    break;
            }
        }
        /*
        this.operacion=new ArrayList<>(List.of(this.resultado.getText()));
        this.operadores=new ArrayList<>();
        this.mostrarOperacion.setText(String.valueOf(this.operacion));
        */



        /*
        String separador =  Pattern.quote(this.operador);
        String[] partes = this.operacion.split(separador);
        if (partes.length == 3) {
            this.resultado.setText("Operacion invalida, suma de dos en dos");
            return;
        }
        Double num1;
        Double num2;
        try {
            num1 = Double.parseDouble(partes[0]);
            num2 = Double.parseDouble(partes[1]);
        } catch (Exception err) {
            System.out.println("error" + err.getMessage());
            return;
        }

        switch (this.operador) {
            case "-" -> this.resultado.setText(String.valueOf(num1 - num2));
            case "+" -> this.resultado.setText(String.valueOf(num1 + num2));
            case "*" -> this.resultado.setText(String.valueOf(num1 * num2));
            case "/" -> this.resultado.setText(String.valueOf(num1 / num2));
        }
        this.operacion = this.resultado.getText();
        this.mostrarOperacion.setText(this.operacion);
        this.operador = "";
        */
    }

}