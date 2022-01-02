module com.example.dziennik_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens Application to javafx.fxml;
    exports Application;
    exports Controller;
    opens Controller to javafx.fxml;
}