package com.example.ejercicio3_b2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button btnIntroducirNum;
    private ListaNumerosEnteros listaNumsSeleccionados = new ListaNumerosEnteros();

    @FXML
    void meterNumero(MouseEvent event) {
        Integer numero=null;
        try {
            numero=Integer.valueOf(this.btnIntroducirNum.getText());
            if( (numero<1 ||numero>49)) {
                throw new Error("numero invalido");
            }
            this.listaNumsSeleccionados.lista(numero);
        }catch (Exception err){
            return;
        }
        if(this.listaNumsSeleccionados.getNumeros().size()<6){
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
        this.mostrarListaElegida.setText(numerosElegidos);
        String numerosPremiados= listaNumerosAleatorios.getNumeros().stream().map(Objects::toString).collect(Collectors.joining(", "));
        this.mostrarListaPremiada.setText(numerosPremiados);
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
    @FXML
    void eliminar(MouseEvent event) {
        this.mostrarAciertos.setText("");
        this.mostrarListaElegida.setText("");
        this.mostrarListaPremiada.setText("");
        this.listaNumsSeleccionados=new ListaNumerosEnteros();
    }
    @FXML
    void eliminarUltimo(MouseEvent event) {
        if(!this.listaNumsSeleccionados.getNumeros().isEmpty()){
            this.listaNumsSeleccionados.getNumeros().remove(this.listaNumsSeleccionados.getNumeros().size()-1);
        }
    }

}
