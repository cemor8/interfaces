package com.example.gestionalumnos;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerGestion implements Initializable {
    @FXML
    private Label mostrarCantidad;
    @FXML
    private Button btnContarCiclo;

    @FXML
    private Button btnContarTotal;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnFiltrar;

    @FXML
    private Button btnMeterAlumno;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnVaciar;

    @FXML
    private CheckBox checkDam;

    @FXML
    private CheckBox checkDaw;

    @FXML
    private CheckBox checkSist;
    @FXML
    private TableView<Alumno> tablaAlumnos;
    @FXML
    private TableColumn<Alumno, String> columnaCiclo;
    @FXML
    private TableColumn<Alumno, String> columnaNombre;
    private DatosAlumnos datosAlumnos;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaNombre.setCellValueFactory(data -> {
                String nombre=data.getValue().getNombre();
                String apellidos=data.getValue().getApellidos();
                return new SimpleStringProperty(nombre+" "+apellidos);
                });

        this.columnaCiclo.setCellValueFactory(new PropertyValueFactory<>("ciclo"));
        this.tablaAlumnos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

    }
    /**
     * Método que cuenta la cantidad de alumnos, dependiendo el boton que lo llame, cuenta todos los alumnos o
     * los alumnos por ciclo.
     * */
    @FXML
    void contar(MouseEvent event) {
        if(this.datosAlumnos.getListaAlumnos().isEmpty()){
            return;
        }
        Button btn=(Button) event.getSource();
        switch (btn.getId()){
            case "btnContarTotal":
                this.mostrarCantidad.setText("Alumnos: "+this.datosAlumnos.getListaAlumnos().size());
                break;
            default:
                int cantidadDam=this.datosAlumnos.getListaAlumnos().stream().filter(alumno -> alumno.getCiclo().equalsIgnoreCase("Desarrollo de Aplicaciones Multiplataforma")).toArray().length;
                int cantidadDaw=this.datosAlumnos.getListaAlumnos().stream().filter(alumno -> alumno.getCiclo().equalsIgnoreCase("Desarrollo de Aplicaciones Web")).toArray().length;
                int cantidadSist=this.datosAlumnos.getListaAlumnos().stream().filter(alumno -> alumno.getCiclo().equalsIgnoreCase("Sistemas Microinformáticos y Redes")).toArray().length;
                this.mostrarCantidad.setText("DAM: "+cantidadDam+"\n"+
                        "DAW: "+cantidadDaw+"\n"+
                        "SIST: "+cantidadSist+"\n"

                );
        }
    }
    /**
     * Método que elimina el alumno que está seleccionado.
     * */
    @FXML
    void eliminarAlumno(MouseEvent event) {
        Alumno alumnoSeleccionado = this.tablaAlumnos.getSelectionModel().getSelectedItem();
        if(alumnoSeleccionado==null){
            return;
        }
        this.datosAlumnos.getListaAlumnos().remove(alumnoSeleccionado);
    }
    /**
     * Método que se encarga de eliminar todos los datos de la aplicacion para empezar desde cero.
     * */
    @FXML
    void eliminarTodo(MouseEvent event) {
        this.datosAlumnos.resetear();
        this.tablaAlumnos.setItems(this.datosAlumnos.getListaAlumnos());
        this.tablaAlumnos.refresh();
        this.mostrarCantidad.setText("");
    }
    /**
     * Método que se encarga de filtrar los alumnos segun los checkbox marcados
     * en la interfaz
     * */
    @FXML
    void filtrar(MouseEvent event) {
        ObservableList<Alumno> listaAlumnosFiltrado=FXCollections.observableArrayList();
        Boolean encontrado=false;
        if(!this.checkDam.isSelected() && !this.checkDaw.isSelected() && !this.checkSist.isSelected()){
            return;
        }
        for (Alumno alumno : this.datosAlumnos.getListaAlumnos()){
           if(alumno.getCiclo().equalsIgnoreCase("Desarrollo de Aplicaciones Multiplataforma") && this.checkDam.isSelected()){
               encontrado=true;
               listaAlumnosFiltrado.add(alumno);
           }else if(alumno.getCiclo().equalsIgnoreCase("Desarrollo de Aplicaciones Web") && this.checkDaw.isSelected()){
               listaAlumnosFiltrado.add(alumno);
               encontrado=true;
           }else if(alumno.getCiclo().equalsIgnoreCase("Sistemas Microinformáticos y Redes") && this.checkSist.isSelected()){
               listaAlumnosFiltrado.add(alumno);
               encontrado=true;
           }
        }
        if(!encontrado){
            return;
        }
        this.tablaAlumnos.setItems(listaAlumnosFiltrado);
        this.tablaAlumnos.refresh();
        this.cambiarEstado(true);
    }
    /**
     * Método que se encarga de reestablecer el filtro.
     * */
    @FXML
    void eliminarFiltro(MouseEvent event) {
        this.checkDaw.setSelected(false);
        this.checkDam.setSelected(false);
        this.checkSist.setSelected(false);
        this.cambiarEstado(false);
        this.tablaAlumnos.setItems(this.datosAlumnos.getListaAlumnos());
        this.tablaAlumnos.refresh();
    }

    /**
     * Método que se encarga de abrir una nueva ventana para modificar o añadir
     * un nuevo alumno a la aplicacion.
     * */
    @FXML
    void mostrarFicha(MouseEvent event) {
        Button btn=(Button) event.getSource();
        if(btn.getId().equals("btnModificar")){
            Alumno alumnoSeleccionado = this.tablaAlumnos.getSelectionModel().getSelectedItem();
            if(alumnoSeleccionado==null){
                return;
            }
            this.datosAlumnos.setAlumnoSeleccionado(alumnoSeleccionado);
        }else {
            this.datosAlumnos.setAlumnoSeleccionado(null);
        }
        try{
            cambiarVentana();
        }catch (IOException err){
            System.out.println(err.getMessage());
        }

    }
    /**
     * Método que se encarga de recibir del modelo los datos necesarios
     * para poder realizar la aplicaion.
     * */
    public void establecerDatos(DatosAlumnos datosAlumnos){
        this.datosAlumnos=datosAlumnos;
        this.tablaAlumnos.setItems(this.datosAlumnos.getListaAlumnos());
    }
    /**
     * Método que se encarga de abrir una nueva ventana y cargar los datos necesarios
     * para el correcto funcionamiento de esta.
     * */
    public void cambiarVentana() throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ApliGestion.class.getResource("fichaAlumno.fxml"));
        Parent root=fxmlLoader.load();
        ControllerFicha controllerFicha=fxmlLoader.getController();
        controllerFicha.establecerDatos(this.datosAlumnos);
        controllerFicha.recibirTabla(this.tablaAlumnos);
        stage.setTitle("Ficha");
        stage.setScene(new Scene(root));
        stage.show();
    }
    /**
     * Método que cambia el estado de los botones.
     * */
    public void cambiarEstado(boolean estado){
        this.btnContarCiclo.setDisable(estado);
        this.btnMeterAlumno.setDisable(estado);
        this.btnEliminar.setDisable(estado);
        this.btnModificar.setDisable(estado);
        this.btnContarTotal.setDisable(estado);
        this.btnVaciar.setDisable(estado);
    }
}
