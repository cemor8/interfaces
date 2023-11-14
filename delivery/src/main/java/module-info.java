module com.example.delivery {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.delivery to javafx.fxml;
    exports com.example.delivery;
}