package com.example.ejercicio6b1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController implements Initializable {

    @FXML
    private Button btnEliminar1;

    @FXML
    private Button btnEliminar2;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnIntroducirMenu;

    @FXML
    private Button btnIntroducirNumeroComensales;

    @FXML
    private TextField introducirNumeroComensales;

    @FXML
    private TextField introducirMenu;

    @FXML
    private Label labelIndicarMenu;
    @FXML
    private Label labelNumeroComensales;

    @FXML
    private Label labelMostrarMenusFinal;
    private ArrayList<String> menus=new ArrayList<>();
    private int comensales=0;
    public boolean validarContenido(String patronBuscar,String texto){
        Pattern patron=Pattern.compile(patronBuscar);
        Matcher matcher= patron.matcher(texto);
        return matcher.matches();
    }

    @FXML
    void eliminar(MouseEvent event) {
        this.comensales=0;
        this.menus=new ArrayList<>();
        this.labelIndicarMenu.setText("Menu del comensal: 1");
        this.introducirNumeroComensales.setVisible(true);
        this.introducirNumeroComensales.setEditable(true);
        this.btnIntroducirNumeroComensales.setVisible(true);
        this.btnEliminar2.setVisible(true);
        this.labelNumeroComensales.setVisible(true);
        this.labelIndicarMenu.setVisible(false);
        this.introducirMenu.setVisible(false);
        this.btnIntroducirMenu.setVisible(false);
        this.btnEliminar1.setVisible(false);
        this.labelIndicarMenu.setVisible(false);
        this.introducirMenu.setVisible(false);
        this.btnIntroducirMenu.setVisible(false);
        this.btnEliminar1.setVisible(false);
        this.btnEliminar.setVisible(false);

        this.introducirNumeroComensales.setText("");
        this.labelMostrarMenusFinal.setText("");
    }

    @FXML
    void introducirComensales(MouseEvent event) {
        if(!validarContenido("[1-5]",this.introducirNumeroComensales.getText())){
            this.introducirNumeroComensales.setText("Numero incorrecto");
            return;
        }
        this.comensales=Integer.valueOf(this.introducirNumeroComensales.getText());
        this.introducirNumeroComensales.setVisible(false);
        this.introducirNumeroComensales.setEditable(false);
        this.btnIntroducirNumeroComensales.setVisible(false);
        this.labelNumeroComensales.setVisible(false);
        this.btnEliminar2.setVisible(false);
        this.labelIndicarMenu.setVisible(true);
        this.introducirMenu.setVisible(true);
        this.btnIntroducirMenu.setVisible(true);
        this.btnEliminar1.setVisible(true);

    }

    @FXML
    void introducirMenu(MouseEvent event) {
        if(!validarContenido("[1-3]",this.introducirMenu.getText())){
            this.labelIndicarMenu.setText("Menu incorrecto");
            return;
        }
        this.menus.add(this.introducirMenu.getText());
        if(this.menus.size()!=this.comensales){
            this.labelIndicarMenu.setText("Menu del comensal: "+(this.menus.size()+1));
            return;
        }
        this.labelIndicarMenu.setVisible(false);
        this.introducirMenu.setVisible(false);
        this.btnIntroducirMenu.setVisible(false);
        this.btnEliminar1.setVisible(false);
        this.btnEliminar.setVisible(true);
        for(int i = 0 ; i<this.menus.size();i++){
            this.labelMostrarMenusFinal.setText(this.labelMostrarMenusFinal.getText()+"\nComensal: "+(i+1)+", Menu: "+this.menus.get(i)+"\n");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.labelIndicarMenu.setVisible(false);
        this.introducirMenu.setVisible(false);
        this.btnIntroducirMenu.setVisible(false);
        this.btnEliminar.setVisible(false);
        this.btnEliminar1.setVisible(false);
    }
}

