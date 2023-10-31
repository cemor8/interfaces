module com.example.ejercicio3_b2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicio3_b2 to javafx.fxml;
    exports com.example.ejercicio3_b2;
}