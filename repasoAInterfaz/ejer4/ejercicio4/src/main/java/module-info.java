module com.example.ejercicio4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicio4 to javafx.fxml;
    exports com.example.ejercicio4;
}