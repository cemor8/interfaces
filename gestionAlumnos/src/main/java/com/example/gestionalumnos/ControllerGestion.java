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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGestion implements Initializable {

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
    private ObservableList<Alumno> listaAlumnos= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaCiclo.setCellValueFactory(new PropertyValueFactory<>("ciclo"));
        this.tablaAlumnos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        this.tablaAlumnos.setItems(this.listaAlumnos);
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaCiclo.setCellValueFactory(new PropertyValueFactory<>("ciclo"));
        columnaNombre.setCellValueFactory(cellData -> {
            String nombre = cellData.getValue().getNombre();
            String apellidos = cellData.getValue().getApellidos();
            return new SimpleStringProperty(nombre + " " + apellidos);
        });
        TableColumn<Alumno, String> columnaApellidos = new TableColumn<>("apellidos");
        columnaApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tablaAlumnos.getColumns().addAll(columnaNombre, columnaApellidos);
    }


    @FXML
    void contar(MouseEvent event) {

    }

    @FXML
    void eliminarAlumno(MouseEvent event) {

    }

    @FXML
    void eliminarTodo(MouseEvent event) {

    }
    public void recibirAlumno(Alumno alumno){
        this.listaAlumnos.add(alumno);
    }
    @FXML
    void mostrarFicha(MouseEvent event) throws IOException {
        Button btn=(Button) event.getSource();
        if(btn.getId().equals("btnModificar")){
            ControllerFicha controllerFicha=new ControllerFicha();
            Alumno alumnoSeleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
            if(alumnoSeleccionado==null){
                return;
            }
            controllerFicha.recibirAlumno(alumnoSeleccionado);
        }
        Stage stage=new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("fichaAlumno.fxml"));
        stage.setTitle("stage 2");
        stage.setScene(new Scene(root));
        stage.show();
    }


}
