module com.example.gestionalumnos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestionalumnos to javafx.fxml;
    exports com.example.gestionalumnos;
}