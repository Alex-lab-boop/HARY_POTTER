module com.example.potter {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.potter to javafx.fxml;
    exports com.example.potter;
}