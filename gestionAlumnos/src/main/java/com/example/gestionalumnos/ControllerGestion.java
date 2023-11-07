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
    private DatosAlumnos datosAlumnos;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaCiclo.setCellValueFactory(new PropertyValueFactory<>("ciclo"));
        this.tablaAlumnos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        this.tablaAlumnos.setItems(this.datosAlumnos.getListaAlumnos());
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
    @FXML
    void mostrarFicha(MouseEvent event) {
        Button btn=(Button) event.getSource();
        if(btn.getId().equals("btnModificar")){
            Alumno alumnoSeleccionado = tablaAlumnos.getSelectionModel().getSelectedItem();
            if(alumnoSeleccionado==null){
                return;
            }
            this.datosAlumnos.setAlumnoSeleccionado(alumnoSeleccionado);
        }
        try{
            cambiarVentana();
        }catch (IOException err){
            System.out.println(err.getMessage());
        }

    }
    public void establecerDatos(DatosAlumnos datosAlumnos){
        this.datosAlumnos=datosAlumnos;
    }
    public void cambiarVentana() throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ApliGestion.class.getResource("fichaAlumno.fxml"));
        ControllerFicha controllerFicha=fxmlLoader.getController();
        controllerFicha.establecerDatos(this.datosAlumnos);
        Parent root=fxmlLoader.load();
        stage.setTitle("Ficha");
        stage.setScene(new Scene(root));
        stage.show();
    }


}
