module com.example.tresraya {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tresraya to javafx.fxml;
    exports com.example.tresraya;
}