package com.example.cuadrostexto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private Button botonSuma;
    private Button botonResta;
    private Button botonDiv;
    private Button botonMult;

    @FXML
    private TextField resultado;

    @FXML
    private TextField txtnum1;

    @FXML
    private TextField txtnum2;

    @FXML
    void operar(ActionEvent event) {
        double num1;
        double num2;
        try {
             num1 = Double.parseDouble(this.txtnum1.getText());
             num2 = Double.parseDouble(this.txtnum2.getText());
        }catch (Exception err){
            this.resultado.setText("Contenido invalido para operar");
            return;
        }
        Button btn = (Button) event.getSource();
        switch (btn.getText()) {
            case "-" -> this.resultado.setText(Double.toString(num1 - num2));
            case "+" -> this.resultado.setText(Double.toString(num1 + num2));
            case "*" -> this.resultado.setText(Double.toString(num1 * num2));
            case "/" -> this.resultado.setText(Double.toString(num1 / num2));
        }

    }

}