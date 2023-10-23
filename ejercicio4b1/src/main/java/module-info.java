module com.example.ejercicio4b1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicio4b1 to javafx.fxml;
    exports com.example.ejercicio4b1;
}