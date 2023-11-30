package com.example.controllers;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.geometry.Insets;
import com.example.modelo.Comida;
import com.example.modelo.Data;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerCarro{

    @FXML
    private VBox meterCarro;
    private Data data = null;
    @FXML
    private AnchorPane anchor;
    @FXML
    private AnchorPane contenedorTarjeta;
    private double precio;
    @FXML
    private MFXButton btnPagar;
    @FXML
    private Label infoCaducidad;

    @FXML
    private Label infoCvc;

    @FXML
    private Label infoTarjeta;

    @FXML
    private Label infoTitular;
    private HashMap<String, String> columnasExpresiones = new HashMap<String, String>(){
        {
            put("tarjeta","^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$");
            put("cvc", "^[0-9]{3}$");
            put("nombre","^[A-Z][a-zA-Z]* [A-Z][a-zA-Z]* [A-Z][a-zA-Z]*$");
            put("fecha","^(0[1-9]|1[0-2])/[0-9]{2}$");
        }

    };
    @FXML
    private MFXTextField introduciCvc;

    @FXML
    private MFXTextField introducirCaducidad;

    @FXML
    private MFXTextField introducirNombre;

    @FXML
    private MFXTextField introducirNtarjeta;


    /**
     * Método que se encarga de recibir informacion y de crear el carrito
     * */
    public void recibirData(Data data){
        this.data = data;
        this.crearCarro();

    }
    /**
     * Método que se encarga de crear el carrito
     * */
    public void crearCarro(){
        if(this.data.getCurrentUser().getCarro().isEmpty()){
            this.contenedorTarjeta.getChildren().clear();
            Image imagenCesta = new Image(getClass().getResourceAsStream("/imagenes/imagenesCarrito/sad_cart.png"));
            ImageView imageView = new ImageView(imagenCesta);
            imageView.setFitHeight(300);
            imageView.setFitWidth(300);
            imageView.getStyleClass().add("imagen-vacio");
            Label texto = new Label("Tu cesta de la compra está vacía");
            texto.getStyleClass().add("texto");

            this.meterCarro.setSpacing(40);
            this.meterCarro.getChildren().setAll(imageView,texto);

            AnchorPane.setLeftAnchor(meterCarro, 300.0);
            return;
        }
        this.meterCarro.setSpacing(20);
        for(Comida cada_comida : this.data.getCurrentUser().getCarro()){

            VBox vbox = new VBox();
            vbox.setSpacing(30);
            vbox.setPadding(new Insets(5));
            vbox.getStyleClass().add("vbox-foto-nombre");

            VBox vbox2 = new VBox();
            vbox2.setSpacing(10);
            vbox2.setPadding(new Insets(5));
            vbox2.getStyleClass().add("vbox-foto-nombre");

            HBox hbox = new HBox();
            hbox.setSpacing(30);
            hbox.getStyleClass().add("contenedor-cada-comida");

            Image imagenComida = new Image(getClass().getResourceAsStream(cada_comida.getFoto()));
            ImageView imageView = new ImageView(imagenComida);
            imageView.setFitHeight(130);
            imageView.setFitWidth(130);
            imageView.getStyleClass().add("imagen-comida");

            Label nombreComida = new Label(cada_comida.getNombre());
            nombreComida.getStyleClass().add("nombre-comida");
            nombreComida.setLayoutY(120);
            nombreComida.setWrapText(true);

            ComboBox<Integer> mostrarCantidad = new ComboBox();
            for (int i = 1; i <= 20; i++) {
                mostrarCantidad.getItems().add(i);

            }
            mostrarCantidad.setValue(cada_comida.getCantidad());
            mostrarCantidad.setOnAction(this::mediador);
            mostrarCantidad.setId(cada_comida.getNombre());

            MFXButton btn = new MFXButton();
            btn.setText("Eliminar");
            btn.setOnMouseClicked(this::eliminar);
            btn.setId(cada_comida.getNombre());
            btn.getStyleClass().add("btn-eliminar");

            vbox.getChildren().setAll(nombreComida,btn);

            vbox.setMaxWidth(150);
            vbox.setMinWidth(150);
            hbox.getChildren().setAll(imageView,vbox,mostrarCantidad);

            hbox.setAlignment(Pos.CENTER);

            this.meterCarro.getChildren().add(hbox);
        }
        this.comprobarPrecio();

        this.meterCarro.getStylesheets().add(getClass().getResource("/styles/estilos_carro.css").toExternalForm());

    }
    /**
     * Método que elimina un articulo del inventario
     * */
    public void eliminar(Event event){
        Button btn = (Button) event.getSource();
        Optional<Comida> comidaOptional = this.data.getCurrentUser().getCarro().stream().filter(comida -> comida.getNombre().equalsIgnoreCase(btn.getId())).findAny();
        if(comidaOptional.isEmpty()){
            return;
        }
        Comida comidaBorrar = comidaOptional.get();
        this.data.getCurrentUser().getCarro().remove(comidaBorrar);

        //elimino el contenido de la ventana y vuelvo a crearla desde cero
        this.meterCarro.getChildren().clear();
        this.crearCarro();

    }
    /**
     * Método que se encarga de realizar las comprobaciones para el pago
     * del carrito
     * **/
    @FXML
    void pagar(ActionEvent event){
        boolean error = false;
        if(!validarContenido(this.columnasExpresiones.get("nombre"),this.introducirNombre.getText())){
            error = true;
            this.infoTitular.setText("Nombre inválido");
            this.introducirNombre.setText("");

        }
        if(!validarContenido(this.columnasExpresiones.get("tarjeta"),this.introducirNtarjeta.getText())){
            error = true;
            this.infoTarjeta.setText("Numero inválido");
            this.introducirNtarjeta.setText("");
        }
        if(!validarContenido(this.columnasExpresiones.get("cvc"),this.introduciCvc.getText())){
            error = true;
            this.infoCvc.setText("CVC erróneo");
            this.introduciCvc.setText("");
        }
        if(!validarContenido(this.columnasExpresiones.get("fecha"),this.introducirCaducidad.getText())){
            error = true;
            this.infoCaducidad.setText("Fecha errónea");
            this.introducirCaducidad.setText("");
        }
        if(error){
            return;
        }

        this.contenedorTarjeta.getChildren().clear();
        this.data.getCurrentUser().setCarro(new ArrayList<>());

        Image imagenCesta = new Image(getClass().getResourceAsStream("/imagenes/imagenesCarrito/feliz.png"));
        ImageView imageView = new ImageView(imagenCesta);
        imageView.setFitHeight(300);
        imageView.setFitWidth(350);
        imageView.getStyleClass().add("imagen-pagado");

        Label texto = new Label("Compra exitosa!!");
        texto.getStyleClass().add("texto");

        this.meterCarro.setSpacing(40);
        VBox.setMargin(texto,new Insets(0,0,0,80));
        this.meterCarro.getChildren().setAll(imageView,texto);
        AnchorPane.setLeftAnchor(meterCarro, 300.0);
        this.data.getCurrentUser().sumarDineroGastado(this.precio);


    }
    /**
     * Método que se encarga de devolver el precio total del carrito
     * **/
    public void comprobarPrecio(){
        this.precio = 0;
        for (Comida comida : this.data.getCurrentUser().getCarro()){
            this.precio+= comida.getPrecio() * (double) comida.getCantidad();
        }
        this.btnPagar.setText("Pagar : "+this.precio);

    }
    /**
     * Método que se encarga de actualizar la cantidad de la comida cuando se
     * selecciona un nuevo numero de articulos en el comboBox, tambien actualiza
     * el precio total.
     * */
    @FXML
    public void mediador(ActionEvent event){
        ComboBox comboBox = (ComboBox) event.getSource();
        Optional<Comida> comidaOptional = this.data.getCurrentUser().getCarro().stream().filter(comida -> comida.getNombre().equalsIgnoreCase(comboBox.getId())).findAny();
        if(comidaOptional.isPresent()){
            comidaOptional.get().setCantidad((Integer) comboBox.getValue());
        }else {
            return;
        }
        this.comprobarPrecio();
    }
    /**
     * Método que se encarga de validar mediante una expresion regular un contenido.
     * */
    public boolean validarContenido(String patron, String texto_buscar) {
        Pattern patronValidar = Pattern.compile(patron);
        Matcher matcher = patronValidar.matcher(texto_buscar);
        return matcher.matches();
    }
    /**
     * Método que elimina el texto de error al introducir datos inválidos
     * **/
    public void eliminarTexto(){
        this.infoCaducidad.setText("");
        this.infoCvc.setText("");
        this.infoTarjeta.setText("");
        this.infoTitular.setText("");
    }
    /**
     * Método que se ejecuta cuando se empieza a escribir para borrar el
     * texto rojo de la informacion.
     * **/
    @FXML
    void quitarTexto(Event event) {
        this.eliminarTexto();
    }

}

