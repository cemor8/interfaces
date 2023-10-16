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
     * Método que inicializa el programa, mete los botones en una coleccion para desactivarles el
     * preseleccionado azul que aparece por defecto, luego los añado a una lista para luego comprobar
     * los ganadores.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Collection<Button> buttonCollection = new ArrayList<>();
        Collections.addAll(buttonCollection, button1, button2, button3,button4,button5,button6,button7,button8,button9);
        buttonCollection.forEach(button -> button.setFocusTraversable(false));
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
            }else if(!this.ganador && this.turno>=8){
                    this.mostrarTurno.setText("Empate");
            }


        } else {
            btn.setText(this.jugadorO);
            btn.setStyle("-fx-background-color: #23ADB0; -fx-text-fill: #000000; -fx-font-size: 16px");
            this.turno += 1;
            btn.setDisable(true);

            if(comprobacion(this.jugadorO)){
                this.mostrarTurno.setText("Ganador Jugador O");
                this.bloquearDesbloquearBoton(true,false);
                this.ganador=true;
            }


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
