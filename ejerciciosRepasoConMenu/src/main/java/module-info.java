module com.example.ejerciciosrepasoconmenu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejerciciosrepasoconmenu to javafx.fxml;
    exports com.example.ejerciciosrepasoconmenu;
}