package com.example.agenda;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public Label labelNumeroBuscarModificar;
    @FXML
    private Button bntEliminar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnMeter;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnVaciar;
    @FXML
    private Button btnEnviarModificaciones;

    @FXML
    private TextField introducirNumero;

    @FXML
    private TextField introducirTexto;
    @FXML
    private TextField introducirNumeroBuscar;

    @FXML
    private Label labelOpcionModificar;
    @FXML
    TableColumn<Contacto, String> columnaNombre= new TableColumn<>("nombre");
    @FXML
    TableColumn<Contacto, String> columnaNumero = new TableColumn<>("numero");

    @FXML
    private TableView<Contacto> tablaAgenda;
    ObservableList<Contacto> listaContactos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.labelNumeroBuscarModificar.setVisible(false);
        this.introducirNumeroBuscar.setVisible(false);
        this.btnEnviarModificaciones.setVisible(false);
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tablaAgenda.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }

    @FXML
    void buscarContacto(MouseEvent event) {
        this.labelOpcionModificar.setText("");
        ObservableList<Contacto> listaEncontrados = FXCollections.observableArrayList();

        if(this.introducirTexto.getText().isEmpty()){
            this.labelOpcionModificar.setText("Introduce un nombre para buscar");
            return;
        }
        boolean hay = false;
        for (Contacto cada_contacto : this.listaContactos) {
            if (cada_contacto.getNombre().equalsIgnoreCase(this.introducirTexto.getText())) {
                listaEncontrados.add(cada_contacto);
                hay = true;
            }
        }

        if (!hay) {
            this.labelOpcionModificar.setText("No hay contactos con ese nombre");
            return;
        }
        tablaAgenda.setItems(listaEncontrados);
    }

    @FXML
    void eliminarContacto(MouseEvent event) {
        this.labelOpcionModificar.setText("");
        if(this.introducirNumero.getText().isEmpty()){
            this.labelOpcionModificar.setText("Introduce un numero para eliminar el contacto");
            return;
        }
        Optional<Contacto> contactoOptional= this.listaContactos.stream().filter(contacto -> contacto.getNumero().equals(this.introducirNumero.getText())).findFirst();
        if(contactoOptional.isPresent()){
            this.listaContactos.remove(contactoOptional.get());
        }else {
            this.labelOpcionModificar.setText("No se encontro un contacto con ese numero");
        }
    }

    @FXML
    void meterContacto(MouseEvent event) {
        this.labelOpcionModificar.setText("");
        String nombre=this.introducirTexto.getText();
        String numero=this.introducirNumero.getText();
        Contacto contacto=new Contacto(nombre,numero);
        if(this.listaContactos.stream().anyMatch(contacto1 -> contacto1.getNombre().equals(contacto.getNombre()))){
            this.labelOpcionModificar.setText("Se encontro un contacto con el mismo nombre pero se añadirá");
        }
        listaContactos.add(contacto);
        this.ordenarPorNombre();
        tablaAgenda.setItems(listaContactos);
    }

    @FXML
    void modificarContacto(MouseEvent event) {

        if(this.introducirNumeroBuscar.getText().isEmpty()){
            this.labelOpcionModificar.setText("Introduce un numero para buscar");
            return;
        }
        Optional<Contacto> contactoOptional=this.listaContactos.stream().filter(contacto -> contacto.getNumero().equals(this.introducirNumeroBuscar.getText())).findFirst();
        if(contactoOptional.isEmpty()){
            this.labelOpcionModificar.setText("No se encontro contacto con ese numero");
            return;
        }
        Contacto contacto=contactoOptional.get();
        System.out.println("hola");
        if(!this.introducirTexto.getText().isEmpty()){
            contacto.setNombre(this.introducirTexto.getText());
        }
        if(!this.introducirNumero.getText().isEmpty()){
            contacto.setNumero(this.introducirNumero.getText());

        }
        System.out.println(contacto);
        System.out.println(this.listaContactos);
        this.btnVaciar.setVisible(true);
        this.btnBuscar.setVisible(true);
        this.btnModificar.setVisible(true);
        this.btnMeter.setVisible(true);
        this.bntEliminar.setVisible(true);
        this.btnEnviarModificaciones.setVisible(false);
        this.labelNumeroBuscarModificar.setVisible(false);
        this.introducirNumeroBuscar.setVisible(false);
        this.tablaAgenda.refresh();
        this.labelOpcionModificar.setText("");
        this.introducirNumero.setText("");
    }
    @FXML
    void modificarInicio(MouseEvent event) {
        this.introducirNumero.setText("");
        this.introducirTexto.setText("");
        this.labelOpcionModificar.setText("");
        this.btnVaciar.setVisible(false);
        this.btnBuscar.setVisible(false);
        this.btnModificar.setVisible(false);
        this.btnMeter.setVisible(false);
        this.bntEliminar.setVisible(false);
        this.btnEnviarModificaciones.setVisible(true);
        this.labelNumeroBuscarModificar.setVisible(true);
        this.introducirNumeroBuscar.setVisible(true);
    }

    @FXML
    void vaciarAgenda(MouseEvent event) {
        this.labelOpcionModificar.setText("");
        this.listaContactos=FXCollections.observableArrayList();
        this.tablaAgenda.refresh();
    }
    /**
     * Método que ordena la lista de contactos por el nombre.
     */
    public void ordenarPorNombre() {
        this.listaContactos.sort((contacto1, contacto2) -> {
            return contacto1.getNombre().compareTo(contacto2.getNombre());
        });
    }


}
