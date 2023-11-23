package com.example.controllers;
import javafx.geometry.Insets;
import com.example.delivery.MainApplication;
import com.example.modelo.Comida;
import com.example.modelo.Data;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.border.CompoundBorder;
import java.io.IOException;
import java.util.Optional;

public class ControllerCarro{

    @FXML
    private VBox meterCarro;
    private Data data = null;



    @FXML
    void pagar(ActionEvent event) {

    }
    public void recibirData(Data data){
        this.data = data;
        this.crearCarro();
    }
    public void crearCarro(){
        if(this.data.getCurrentUser().getCarro().isEmpty()){
            HBox hbox = new HBox();
            hbox.setSpacing(10);

            return;
        }
        this.meterCarro.setSpacing(20);
        for(Comida cada_comida : this.data.getCurrentUser().getCarro()){
            VBox vbox = new VBox();
            vbox.setSpacing(10);
            vbox.setPadding(new Insets(5));

            vbox.getStyleClass().add("vbox-foto-nombre");
            HBox hBox2 = new HBox();
            VBox vbox2 = new VBox();
            vbox2.setSpacing(10);
            vbox2.setPadding(new Insets(5));
            vbox2.getStyleClass().add("vbox-foto-nombre");
            HBox hbox = new HBox();
            hbox.setSpacing(30);
            hbox.getStyleClass().add("contenedor-cada-comida");
            Image imagenComida = new Image(getClass().getResourceAsStream(cada_comida.getFoto()));
            ImageView imageView = new ImageView(imagenComida);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.getStyleClass().add("imagen-comida");
            Label nombreComida = new Label(cada_comida.getNombre());
            nombreComida.getStyleClass().add("nombre-comida");
            ComboBox<Integer> mostrarCantidad = new ComboBox();
            for (int i = 1; i <= 20; i++) {
                mostrarCantidad.getItems().add(i);

            }
            mostrarCantidad.setValue(cada_comida.getCantidad());
            MFXButton btn = new MFXButton();
            btn.setText("Eliminar");
            btn.setOnMouseClicked(this::eliminar);
            btn.setId(cada_comida.getNombre());

            vbox.getChildren().setAll(nombreComida,btn);

            //vbox2.getChildren().setAll(btn,mostrarCantidad);

            hbox.getChildren().setAll(imageView,vbox,mostrarCantidad);

            hbox.setAlignment(Pos.CENTER);

            this.meterCarro.getChildren().add(hbox);
        }
        MFXButton btn = new MFXButton();
        btn.setText("Pagar");
        btn.setOnMouseClicked(this::pagar);
        this.meterCarro.getChildren().add(btn);

    }
    public void eliminar(Event event){
        Button btn = (Button) event.getSource();
        Optional<Comida> comidaOptional = this.data.getCurrentUser().getCarro().stream().filter(comida -> comida.getNombre().equalsIgnoreCase(btn.getId())).findAny();
        if(comidaOptional.isEmpty()){
            return;
        }
        Comida comidaBorrar = comidaOptional.get();
        this.data.getCurrentUser().getCarro().remove(comidaBorrar);
        Stage stage = (Stage) btn.getScene().getWindow();
        Parent root = null;
        try {
             root= FXMLLoader.load(MainApplication.class.getResource("carrito.fxml"));
        }catch (IOException err){
            System.out.println(err.getMessage());
        }

        stage.setTitle("Carrito");
        stage.setScene(new Scene(root));
    }
    public void pagar(Event event){

    }

}

