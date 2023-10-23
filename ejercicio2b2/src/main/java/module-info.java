module com.example.ejercicio2b2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicio2b2 to javafx.fxml;
    exports com.example.ejercicio2b2;
}