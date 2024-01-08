module com.example.testexamen {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.testexamen to javafx.fxml;
    exports com.example.testexamen;
}