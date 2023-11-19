module com.example.delivery {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    opens com.example.delivery to javafx.fxml;
    exports com.example.delivery;
    exports com.example.controllers;
    exports com.example.modelo;
    opens com.example.controllers to javafx.fxml;
    opens com.example.modelo to javafx.fxml;

}