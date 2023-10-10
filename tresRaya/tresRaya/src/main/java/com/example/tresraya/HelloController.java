package com.example.tresraya;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class HelloController {

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
    private Label mostrarTurno;
    @FXML
    private ArrayList<Button> listaBotones = new ArrayList<>();
    private int turno = 0;
    private String jugadorX = "X";
    private String jugadorO = "O";

    @FXML
    void eliminar(MouseEvent event) {

    }
    public void initialize(){
        this.listaBotones.add()
    }

    @FXML
    void jugar(MouseEvent event) {
        Button btn = (Button) event.getSource();

        System.out.println(this.listaBotones.add(button1));


        System.out.println(this.listaBotones.get(0));

        if (this.turno % 2 == 0) {
            btn.setText(this.jugadorX);
            btn.setStyle("-fx-background-color: #3442db; -fx-text-fill: #ffffff;-fx-font-size: 16px");
            this.turno += 1;
            btn.setDisable(true);
                /*
            if(comprobarGanador(this.jugadorX,0,0,1,0,2) ||
                    comprobarGanador(this.jugadorX,0,0,1,1,2)||
                    comprobarGanador(this.jugadorX,0,0,0,1,2)

            ){
                this.mostrarTurno.setText("Ganador Jugador X");
            }

                 */
        } else {
            btn.setText(this.jugadorO);
            btn.setStyle("-fx-background-color: #23ADB0; -fx-text-fill: #000000; -fx-font-size: 16px");
            this.turno += 1;
            btn.setDisable(true);
            /*
            if(comprobarGanador(this.jugadorO,0,0,1,0,2) ||
                    comprobarGanador(this.jugadorX,0,0,1,1,2)||
                    comprobarGanador(this.jugadorX,0,0,0,1,2)

            ){
                this.mostrarTurno.setText("Ganador Jugador O");
            }

             */
        }
    }

    public void bloquearBoton() {

    }
    /*
    public boolean comprobarGanador(String jugador,int filaInicial, int columnaInicial,int filaSuma,int columnaSuma,int veces){
        int filaSumada= filaInicial;
        int columnaSumada=columnaInicial;
        int contadorVeces=0;
        int aciertos=0;

        while ( (filaSumada>= 0 && columnaSumada>=0 )&&
                (filaSumada< this.listaBotones.length && columnaSumada<this.listaBotones.length )&&
                contadorVeces<veces &&
                this.listaBotones.get(filaSumada).get(columnaSumada)!=null
        ){


            if(this.listaBotones[filaSumada][columnaSumada].getText().equals(jugador)){
                aciertos+=1;
            }
            filaSumada+=filaSuma;
            columnaSumada+=columnaSuma;
            contadorVeces+=1;
        }

        return aciertos >= 3;
    }
    public void initialize(){
    }

     */


}
