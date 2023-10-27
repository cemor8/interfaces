package com.example.ejercicio2b2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController {



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
    /**
     * Método que se encarga de borrar los datos de la factura
     * @param event event
     * */
    @FXML
    void eliminar(MouseEvent event) {
        this.eliminarTexto();
        this.unidades=new ArrayList<>();
        this.precios=new ArrayList<>();
        this.productos=new ArrayList<>();
        this.labelTotal.setText("");
        this.facturaPrecioUnidad.setText("");
        this.facturaProducto.setText("");
        this.facturaTotal.setText("");
        this.facturaUnidades.setText("");

    }
    /**
     *Método que  se encarga de borrar el texto de los textfield
     * en los cuales se introducen los datos del producto
     * */
    public void eliminarTexto(){
        this.introducirNombre.setText("");
        this.introducirPrecioUnidad.setText("");
        this.introducirUnidades.setText("");
    }
    /**
     * Método que se encarga de validar los datos mediante una expresion regular
     * @return boolean
     * */
    public boolean validarDatos(String patronValidar, String texto){
        Pattern patron=Pattern.compile(patronValidar);
        Matcher matcher= patron.matcher(texto);
        return matcher.matches();
    }
    /**
     *Método que se encarga de meter los productos en la factura, primero comprueba
     * que los datos introducidos sean correctos, luego los añade al label de la factura para
     * mostrarlos y al final calcula su total.
     * */
    @FXML
    void meterProducto(MouseEvent event) {
        if (this.introducirNombre.getText().isEmpty()||this.introducirUnidades.getText().isEmpty()||this.introducirPrecioUnidad.getText().isEmpty()){
            this.labelTotal.setText("Faltan datos para introducir el producto");
            return;
        }else if(!validarDatos("^[a-zA-Z]{1,15}$",this.introducirNombre.getText())||
                !validarDatos("^[0-9]+$",this.introducirUnidades.getText())||
                !validarDatos("^[0-9]+([,.][0-9]+)?$",this.introducirPrecioUnidad.getText())){
            this.labelTotal.setText("Datos inválidos");
            return;
        }
        if(this.productos.size()==3||this.precios.size()==3||this.unidades.size()==3){
            this.labelTotal.setText("No se pueden añadir mas productos");
            return;
        }
        double precio;
        int unidades;
        try {
            precio= Double.parseDouble(this.introducirPrecioUnidad.getText());
            unidades= Integer.parseInt(this.introducirUnidades.getText());

        }catch (NumberFormatException err){
            this.eliminarTexto();
            this.labelTotal.setText("Datos incorrectos");
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