module com.example.ejercicio3b1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicio3b1 to javafx.fxml;
    exports com.example.ejercicio3b1;
}