package com.example.ejercicio2;

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
    private Button botonMeter;

    @FXML
    private Label facturaPrecioUnidad;

    @FXML
    private Label facturaProducto;

    @FXML
    private Label facturaTotal;

    @FXML
    private Label facturaUnidades;

    @FXML
    private TextField introducirNombre;

    @FXML
    private TextField introducirPrecioUnidad;

    @FXML
    private TextField introducirUnidades;
    private ArrayList<String> productos=new ArrayList<>();
    private ArrayList<Double> precios=new ArrayList<>();
    private ArrayList<Integer> unidades=new ArrayList<>();
    @FXML
    private Label labelTotal;

    @FXML
    void eliminar(MouseEvent event) {
        this.eliminarTexto();
        this.unidades=new ArrayList<>();
        this.precios=new ArrayList<>();
        this.productos=new ArrayList<>();
        this.labelTotal.setText("");
    }
    public void eliminarTexto(){
        this.introducirNombre.setText("");
        this.introducirPrecioUnidad.setText("");
        this.introducirUnidades.setText("");
    }
    @FXML
    void meterProducto(MouseEvent event) {
        if(this.productos.size()==3||this.precios.size()==3||this.unidades.size()==3){
            return;
        }
        double precio;
        int unidades;
        try {
            precio= Double.parseDouble(this.introducirPrecioUnidad.getText());
            unidades= Integer.parseInt(this.introducirUnidades.getText());
            
        }catch (NumberFormatException err){
            this.eliminarTexto();
            return;
        }
        this.productos.add(this.introducirNombre.getText());
        this.precios.add(precio);
        this.unidades.add(unidades);
        this.eliminarTexto();
        if(this.productos.size()==3){
            double total=0;
            for(int i = 0 ;i<this.productos.size();i++){
                this.facturaProducto.setText(this.facturaProducto.getText()+"\n"+this.productos.get(i));
                this.facturaUnidades.setText(this.facturaUnidades.getText()+"\n"+this.unidades.get(i));
                this.facturaPrecioUnidad.setText(this.facturaPrecioUnidad.getText()+"\n"+this.precios.get(i));
                this.facturaTotal.setText(this.facturaTotal.getText()+"\n"+(this.precios.get(i)*this.unidades.get(i)));
                total+=this.precios.get(i)*this.unidades.get(i);
            }
            this.labelTotal.setText(String.valueOf(total));
        }
    }

}
