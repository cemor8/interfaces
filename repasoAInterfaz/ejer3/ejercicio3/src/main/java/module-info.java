module com.example.ejercicio3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicio3 to javafx.fxml;
    exports com.example.ejercicio3;
}