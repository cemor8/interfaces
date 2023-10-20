module com.example.ejer1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejer1 to javafx.fxml;
    exports com.example.ejer1;
}