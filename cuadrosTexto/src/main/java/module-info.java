module com.example.cuadrostexto {
    requires javafx.controls;
    requires javafx.fxml;



    opens com.example.cuadrostexto to javafx.fxml;
    exports com.example.cuadrostexto;
}