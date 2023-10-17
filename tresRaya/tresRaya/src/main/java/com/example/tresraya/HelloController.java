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
    private GridPane gridPane;
    @FXML
    private Label mostrarTurno;
    @FXML
    private ArrayList<ArrayList<Button>> listaBotones = new ArrayList<>();
    private int turno = 0;
    private String jugadorX = "X";
    private String jugadorO = "O";
    private boolean ganador=false;
    /**
     * Método que reestablece la partida, borra el contenido y estilo de botones,
     * el turno se reinicia, indica que no hay ganador y empieza el jugador x
     * otra vez.
     * */
    @FXML
    void eliminar(MouseEvent event) {
        this.bloquearDesbloquearBoton(false,true);
        this.mostrarTurno.setText("Turno jugador X");
        this.turno=0;
        this.ganador=false;
    }
    /**
     * Método que de forma dinamica crea 0 botones para añadirlos a un gridpane en una
     * disposición de 3x3, tambien crea una matriz 3x3 para luego hacer las comprobaciones de victoria.
     * a mayores, modifica el ancho, alto y desactiva la preseleccion azul del boton.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.mostrarTurno.setText("Turno jugador X");
        int i=1;
        //Hago fors para crear los botones, en cada iteracion del for creo una lista vacia para añadir los botones
        for (int fila = 0; fila < 3; fila++) {
            this.listaBotones.add(new ArrayList<>());
            for (int columna = 0; columna < 3; columna++) {
                Button boton = new Button();
                //le pongo el ancho, alto y desactivo la preseleccion azul
                boton.setPrefWidth(150);
                boton.setPrefHeight(75);
                boton.setFocusTraversable(false);
                //establezco id y le indico que cuando el raton clicke en el llame al metodo jugar.
                boton.setId("button"+i);
                boton.setOnMouseClicked(mouseEvent -> jugar(mouseEvent));
                // lo añado al gridPane indicando la columnda y luego la fila donde quiero ponerlo
                this.gridPane.add(boton, columna, fila);
                this.listaBotones.get(fila).add(boton);
                i++;
            }
        }
        this.gridPane.setHgap(10);
        this.gridPane.setVgap(10);
    }
    /**
     * Método que es llamado cuando se clicka con el raton un boton,
     * comprueba de quien es el turno y dependiendo de quien sea, rellena
     * el boton de una manera u otra. Luego comprueba si hay ganador, si lo hay acaba el juego,
     * si no lo hay, comprueba si el juego sigue transcurriendo o si ha acabado y hay un empate.
     * */
    @FXML
    void jugar(MouseEvent event) {
        Button btn = (Button) event.getSource();
        if (this.turno % 2 == 0) {
            btn.setText(this.jugadorX);
            btn.setStyle("-fx-background-color: #3442db; -fx-text-fill: #ffffff;-fx-font-size: 16px");
            this.turno += 1;
            btn.setDisable(true);

            if(comprobacion(this.jugadorX)){
                this.mostrarTurno.setText("Ganador Jugador X");
                this.bloquearDesbloquearBoton(true,false);
                this.ganador=true;
                return;
            }else if(!this.ganador && this.turno>=9){
                    this.mostrarTurno.setText("Empate");
                    return;
            }
            this.mostrarTurno.setText("Turno jugador O");


        } else {

            btn.setText(this.jugadorO);
            btn.setStyle("-fx-background-color: #23ADB0; -fx-text-fill: #000000; -fx-font-size: 16px");
            this.turno += 1;
            btn.setDisable(true);

            if(comprobacion(this.jugadorO)){
                this.mostrarTurno.setText("Ganador Jugador O");
                this.bloquearDesbloquearBoton(true,false);
                this.ganador=true;
                return;
            }
            this.mostrarTurno.setText("Turno jugador X");


        }
    }
    /**
     * Método que comprueba si hay un ganador. Recibe el tipo de jugador que se buscará.
     * */
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


    /**
     * Método que resetea los botones si el parametro resetear es true, tambien recibe el
     * estado e indica este a la propiedad setDisable.
     * */
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

    /**
     * Método que se encarga de comprobar si hay un ganador, recibe el jugador a buscar, las filas y
     * columnas donde se empieza y hacia la direccion a la que se busca, que es la suma a la fila y a la columna.
     * Empieza a buscar, mientras este dentro del tablero y el boton tenga texto, comprueba que el texto es igual al jugador,
     * si no lo es devuelve directamente false, si el jugador se encuentra ahi, avanza a la siguiente casilla y sigue hasta que
     * el jugador gane, que devolvera true, o no, que devolvera false.
     * */
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
            }else {
                return aciertos >= 3;
            }
            filaSumada+=filaSuma;
            columnaSumada+=columnaSuma;
            contadorVeces+=1;
        }

        return aciertos >= 3;
    }

}
