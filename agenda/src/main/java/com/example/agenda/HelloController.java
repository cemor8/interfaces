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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private Button bntMostrar;
    @FXML
    private Button btnVolverAtras;

    @FXML
    private Label labelOpcionModificar;
    @FXML
    TableColumn<Contacto, String> columnaNombre= new TableColumn<>("nombre");
    @FXML
    TableColumn<Contacto, String> columnaNumero = new TableColumn<>("numero");

    @FXML
    private TableView<Contacto> tablaAgenda;
    private ObservableList<Contacto> listaContactos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.labelNumeroBuscarModificar.setVisible(false);
        this.introducirNumeroBuscar.setVisible(false);
        this.btnEnviarModificaciones.setVisible(false);
        this.btnVolverAtras.setVisible(false);
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        this.tablaAgenda.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        this.introducirTexto.setPromptText("Escribe aquí el nombre...");
        this.introducirNumero.setPromptText("Escribe aquí el numero formato (xxx-xx-xx-xx)");
        this.introducirNumeroBuscar.setPromptText(" formato (xxx-xx-xx-xx)");
    }
    /**
     * Métodod que busca un contacto basandose en el nombre, verifica que el nombre introducido sea correcto.
     * Si es válido, busca los contactos por ese nombre, luego los muestra en la tabla. Si no encuentra ninguno, lo
     * indica.
     * @param event event
     * */
    @FXML
    void buscarContacto(MouseEvent event) {
        this.labelOpcionModificar.setText("");
        if(!creaExpresion("^[a-zA-Z0-9]{1,20}$",this.introducirTexto.getText())){
            this.labelOpcionModificar.setText("Contenido invalido");
            return;
        }
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
        this.tablaAgenda.setItems(listaEncontrados);
        this.tablaAgenda.refresh();
    }
    /**
     * Método que elimina un contacto, se basa en el numero que recibe en introducirNumero.
     * Comprueba el formato del numero y busca uno si es válido, si lo encuenta lo elimina y si
     * no encuentra ninguno, lo indica.
     * @param event event
     * */
    @FXML
    void eliminarContacto(MouseEvent event) {
        this.labelOpcionModificar.setText("");
        if(this.introducirNumero.getText().isEmpty()){
            this.labelOpcionModificar.setText("Introduce un numero para eliminar el contacto");
            return;
        }
        if(!creaExpresion("^\\d{3}-\\d{2}-\\d{2}-\\d{2}$",this.introducirTexto.getText())){
            this.labelOpcionModificar.setText("Contenido invalido");
            return;
        }
        Optional<Contacto> contactoOptional= this.listaContactos.stream().filter(contacto -> contacto.getNumero().equals(this.introducirNumero.getText())).findFirst();
        if(contactoOptional.isPresent()){
            this.listaContactos.remove(contactoOptional.get());
        }else {
            this.labelOpcionModificar.setText("No se encontro un contacto con ese numero");
        }
        this.introducirNumero.setText("");
    }
    /**
     * Método que verifica una expresion regular en un texto mandado.
     * @param patron expresion regular
     * @param texto string en donde buscar texto
     * */
    public boolean creaExpresion(String patron, String texto){
        Pattern patronBuscar=Pattern.compile(patron);
        Matcher matcher=patronBuscar.matcher(texto);
        return matcher.matches();
    }
    /**
     * Método que mete un contacto en la agenda
     * @param event event
     * */
    @FXML
    void meterContacto(MouseEvent event) {
        this.labelOpcionModificar.setText("");
        String nombre=this.introducirTexto.getText();
        String numero=this.introducirNumero.getText();
        if(!creaExpresion("^[a-zA-Z0-9]{1,20}$",nombre)||!creaExpresion("^\\d{3}-\\d{2}-\\d{2}-\\d{2}$",numero)){
            this.labelOpcionModificar.setText("Contenido invalido");
            return;
        }
        Contacto contacto=new Contacto(nombre,numero);
        if (this.listaContactos.stream().anyMatch(contacto1 -> contacto1.getNumero().equals(contacto.getNumero()))){
            this.labelOpcionModificar.setText("Ya hay un contacto con ese numero");
            return;
        }else if(this.listaContactos.stream().anyMatch(contacto1 -> contacto1.getNombre().equals(contacto.getNombre()))){
            this.labelOpcionModificar.setText("Se encontro un contacto con el mismo nombre pero se añadirá");
        }
        this.listaContactos.add(contacto);
        this.ordenarPorNombre();
        this.tablaAgenda.setItems(listaContactos);
        this.eliminarTexto(event);
    }
    /**
     * Método que modifica un contacto, comprueba que el numero para buscar tiene un formato válido,
     * luego busca un contacto con ese numero, comprueba que los datos introducidos para los nuevos valores
     * sean válidos (si se dejan en blanco, se quedan los anteriores), y los modifica.
     * @param event event
     * */
    @FXML
    void modificarContacto(MouseEvent event) {
        this.labelOpcionModificar.setText("");
        if(this.introducirNumeroBuscar.getText().isEmpty()){
            this.labelOpcionModificar.setText("Introduce un numero para buscar");
            return;
        }
        if(!creaExpresion("^\\d{3}-\\d{2}-\\d{2}-\\d{2}$",this.introducirNumeroBuscar.getText())){
            this.labelOpcionModificar.setText("Contenido invalido");
            return;
        }
        Optional<Contacto> contactoOptional=this.listaContactos.stream().filter(contacto -> contacto.getNumero().equals(this.introducirNumeroBuscar.getText())).findFirst();
        if(contactoOptional.isEmpty()){
            this.labelOpcionModificar.setText("No se encontro contacto con ese numero");
            return;
        }
        Contacto contacto=contactoOptional.get();
        if(!this.introducirTexto.getText().isEmpty()&&!creaExpresion("^[a-zA-Z0-9]{1,20}$",this.introducirTexto.getText())){
            contacto.setNombre(this.introducirTexto.getText());
        }
        if(!this.introducirNumero.getText().isEmpty()&&!creaExpresion("^\\d{3}-\\d{2}-\\d{2}-\\d{2}$",this.introducirNumeroBuscar.getText())){
            contacto.setNumero(this.introducirNumero.getText());

        }
        this.cambiarVisibilidad(true,false);
        this.btnModificar.setVisible(true);
        this.tablaAgenda.refresh();
    }
    /**
     * Método que se encarga de cambiar la visibilidad de los elementos de
     * la interfaz de la calculadora.
     * @param propiedad boolean
     * @param propiedad2 boolean
     * */
    public void cambiarVisibilidad(Boolean propiedad,Boolean propiedad2){
        this.introducirNumero.setText("");
        this.introducirTexto.setText("");
        this.labelOpcionModificar.setText("");
        this.btnVaciar.setVisible(propiedad);
        this.btnBuscar.setVisible(propiedad);
        this.btnMeter.setVisible(propiedad);
        this.bntEliminar.setVisible(propiedad);
        this.btnEnviarModificaciones.setVisible(propiedad2);
        this.labelNumeroBuscarModificar.setVisible(propiedad2);
        this.introducirNumeroBuscar.setVisible(propiedad2);
        this.btnVolverAtras.setVisible(propiedad2);
        this.bntMostrar.setVisible(propiedad);
    }
    /**
     * Método que modifica la interfaz de la agenda para adaptarla al
     * método modificarContacto.
     * @param event event
     * */
    @FXML
    void modificarInicio(MouseEvent event) {
        this.cambiarVisibilidad(false,true);
        this.btnModificar.setVisible(false);
    }
    /**
     * Método que muestra la lista de contactos almacenados en la agenda.
     * @param event event
     * */
    @FXML
    void mostrar(MouseEvent event) {
        this.labelOpcionModificar.setText("");
        this.tablaAgenda.setItems(this.listaContactos);
        this.tablaAgenda.refresh();
    }

    /**
     * Método que vacia la agenda.
     * @param event event
     * */
    @FXML
    void vaciarAgenda(MouseEvent event) {
        this.labelOpcionModificar.setText("");
        this.listaContactos=FXCollections.observableArrayList();
        this.tablaAgenda.setItems(listaContactos);
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
    /**
     * Método que elimina el texto que se muestra al introducir valores o
     * realizar alguna opcion.
     * @param event event
     * */
    @FXML
    void eliminarTexto(MouseEvent event) {
        this.labelOpcionModificar.setText("");
        this.introducirTexto.setText("");
        this.introducirNumeroBuscar.setText("");
        this.introducirNumero.setText("");
    }
    /**
     * Método que permite volver atrás en el apartado de modficacion
     * a un contacto.
     * @param event event
     * */
    @FXML
    void volver(MouseEvent event) {
        this.eliminarTexto(event);
        this.cambiarVisibilidad(true,false);
        this.btnModificar.setVisible(true);
    }
}
