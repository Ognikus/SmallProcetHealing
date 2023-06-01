module com.example.healingcontroller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.healingcontroller to javafx.fxml;
    exports com.example.healingcontroller;
}