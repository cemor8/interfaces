package com.example.testexamen;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerView implements Initializable {

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnOrdenar;

    @FXML
    private TableColumn<Alumno, String> colmNombre;

    @FXML
    private TableColumn<Alumno, Integer> columnNota;
    @FXML
    private TableView<Alumno> tabla;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        this.columnNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        this.colmNombre.setCellValueFactory(alumnoStringCellDataFeatures -> {
            String nombre = alumnoStringCellDataFeatures.getValue().getNombre();
            String apellidos = alumnoStringCellDataFeatures.getValue().getApellidos();
            return new SimpleStringProperty(nombre+" "+apellidos);
        });
    }
}