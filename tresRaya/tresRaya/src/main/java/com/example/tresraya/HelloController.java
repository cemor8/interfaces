package com.example.tresraya;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {

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
    private ArrayList<ArrayList<Button>> listaBotones = new ArrayList<>();
    private int turno = 0;
    private String jugadorX = "X";
    private String jugadorO = "O";

    @FXML
    void eliminar(MouseEvent event) {
        this.bloquearDesbloquearBoton(false,true);
        this.mostrarTurno.setText("Turno jugador X");
        this.turno=0;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ArrayList<Button> lista=new ArrayList<>();
        lista.add(this.button1);
        lista.add(this.button2);
        lista.add(this.button3);
        this.listaBotones.add(lista);
        ArrayList<Button> lista2=new ArrayList<>();
        lista2.add(this.button4);
        lista2.add(this.button5);
        lista2.add(this.button6);
        this.listaBotones.add(lista2);
        ArrayList<Button> lista3=new ArrayList<>();
        lista3.add(this.button7);
        lista3.add(this.button8);
        lista3.add(this.button9);
        this.listaBotones.add(lista3);



    }

    @FXML
    void jugar(MouseEvent event) {
        Button btn = (Button) event.getSource();
        if (this.turno % 2 == 0) {
            btn.setText(this.jugadorX);
            btn.setStyle("-fx-background-color: #3442db; -fx-text-fill: #ffffff;-fx-font-size: 16px");
            this.turno += 1;
            btn.setDisable(true);

            if(comprobacion(this.jugadorX)

            ){
                this.mostrarTurno.setText("Ganador Jugador X");
                this.bloquearDesbloquearBoton(true,false);
            }


        } else {
            btn.setText(this.jugadorO);
            btn.setStyle("-fx-background-color: #23ADB0; -fx-text-fill: #000000; -fx-font-size: 16px");
            this.turno += 1;
            btn.setDisable(true);

            if(comprobacion(this.jugadorO)

            ){
                this.mostrarTurno.setText("Ganador Jugador O");
                this.bloquearDesbloquearBoton(true,false);
            }


        }
    }

    public boolean comprobacion(String jugador){
        if(comprobarGanador(jugador,0,0,1,0,3) ||
                comprobarGanador(jugador,0,0,1,1,3)||
                comprobarGanador(jugador,0,0,0,1,3)||
                comprobarGanador(jugador,0,2,1,0,3)||
                comprobarGanador(jugador,0,2,1,-1,3)||
                comprobarGanador(jugador,2,2,0,-1,3)||
                comprobarGanador(jugador,0,1,1,0,3)||
                comprobarGanador(jugador,1,0,0,1,3)


        ){
            return true;
        }
        return false;
    }



    public void bloquearDesbloquearBoton(boolean estado,boolean resetear) {
        for (int i = 0;i<this.listaBotones.size();i++){
            for (int j = 0;j<this.listaBotones.get(i).size();j++){
                this.listaBotones.get(i).get(j).setDisable(estado);
                if(resetear){
                    this.listaBotones.get(i).get(j).setText("");
                    this.listaBotones.get(i).get(j).setStyle("");
                }
            }
        }
    }


    public boolean comprobarGanador(String jugador,int filaInicial, int columnaInicial,int filaSuma,int columnaSuma,int veces){
        int filaSumada= filaInicial;
        int columnaSumada=columnaInicial;
        int contadorVeces=0;
        int aciertos=0;
        while ( (filaSumada>= 0 && columnaSumada>=0 )&&
                (filaSumada< this.listaBotones.size() && columnaSumada<this.listaBotones.size() )&&
                contadorVeces<veces &&
                this.listaBotones.get(filaSumada).get(columnaSumada)!=null
        ){


            if(this.listaBotones.get(filaSumada).get(columnaSumada).getText().equals(jugador)){
                aciertos+=1;
            }
            filaSumada+=filaSuma;
            columnaSumada+=columnaSuma;
            contadorVeces+=1;
        }

        return aciertos >= 3;
    }

}
