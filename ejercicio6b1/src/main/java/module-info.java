module com.example.ejercicio6b1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejercicio6b1 to javafx.fxml;
    exports com.example.ejercicio6b1;
}