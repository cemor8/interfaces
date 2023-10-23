module com.example.ejercicio1b1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicio1b1 to javafx.fxml;
    exports com.example.ejercicio1b1;
}