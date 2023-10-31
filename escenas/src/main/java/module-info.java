module com.example.escenas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.escenas to javafx.fxml;
    exports com.example.escenas;
}