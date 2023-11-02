package com.example.ejerciciosrepasoconmenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Objects;
import java.util.stream.Collectors;

public class ControllerPrimitiva {
    @FXML
    private Label mostrarListaElegida;
    @FXML
    private Label mostrarListaPremiada;
    @FXML
    private Label mostrarAciertos;
    @FXML
    private Button btnEmpezar;
    @FXML
    private Button btnEliminarUltimo;

    @FXML
    private TextField introducirNumero;
    @FXML
    private Button btnIntroducirNum;
    private ListaNumerosEnteros listaNumsSeleccionados = new ListaNumerosEnteros();
    /**
     * Método que se encarga de realizar toda la operacion de jugar la partida de primitiva.
     * Este metodo comprueba que el numero metido sea válido, luego compruba si hay que seguir metiendo numeros
     * o ya hay que generar la lista premiada. Al final, muestra las dos listas e indica los aciertos del jugador.
     * */
    @FXML
    void meterNumero(MouseEvent event) {
        Integer numero=null;
        try {
            System.out.println("hola");
            System.out.println(this.introducirNumero.getText());
            numero=Integer.valueOf(this.introducirNumero.getText());
            System.out.println("dsa");
            if( (numero<1 || numero>49)) {
                System.out.println("mal");
                throw new Exception("numero invalido");
            }
            System.out.println("hola2");
            this.listaNumsSeleccionados.lista(numero);
            System.out.println("hola3");
        }catch (Exception err){
            System.out.println(err.getMessage());
            return;
        }
        if(this.listaNumsSeleccionados.getNumeros().size()<6){
            this.introducirNumero.setText("");
            return;
        }
        ListaNumerosEnteros listaNumerosAleatorios= new ListaNumerosEnteros();
        GeneradorNumerosAleatorios generadorNumerosAleatorios= new GeneradorNumerosAleatorios();
        while (listaNumerosAleatorios.getNumeros().size()<6){
            int numeroAleatorio=generadorNumerosAleatorios.generar();
            try{
                listaNumerosAleatorios.lista(numeroAleatorio);
            }catch (Exception err){
                System.out.println(err.getMessage());
            }
        }
        String numerosElegidos= this.listaNumsSeleccionados.getNumeros().stream().map(Objects::toString).collect(Collectors.joining(", "));
        this.mostrarListaElegida.setText("Lista elegida "+numerosElegidos);
        String numerosPremiados= listaNumerosAleatorios.getNumeros().stream().map(Objects::toString).collect(Collectors.joining(", "));
        this.mostrarListaPremiada.setText("Lista premiada "+numerosPremiados);
        int aciertos= 0;
        for (int cadaNumeroElegido: this.listaNumsSeleccionados.getNumeros()){
            if(listaNumerosAleatorios.getNumeros().contains(cadaNumeroElegido)){
                aciertos+=1;
            }
        }
        if(aciertos==0){
            this.mostrarAciertos.setText("No hay ningun acierto");
        }else if(aciertos==1){
            this.mostrarAciertos.setText("Has acertado 1 vez");
        }else {
            this.mostrarAciertos.setText("Has acertado "+aciertos+" veces");
        }
    }
    /**
     * Método que se encarga de eliminar los datos para volver a jugar otra partida
     * */
    @FXML
    void eliminar(MouseEvent event) {
        this.mostrarAciertos.setText("");
        this.mostrarListaElegida.setText("");
        this.mostrarListaPremiada.setText("");
        this.listaNumsSeleccionados=new ListaNumerosEnteros();
    }
    /**
     * Método que se encarga de eliminar el ultimo numero introducido a la lista de numeros que ha
     * elegido el jugador.
     * */
    @FXML
    void eliminarUltimo(MouseEvent event) {
        if(!this.listaNumsSeleccionados.getNumeros().isEmpty()){
            this.listaNumsSeleccionados.getNumeros().remove(this.listaNumsSeleccionados.getNumeros().size()-1);
        }
    }

}
