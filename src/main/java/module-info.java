module com.yurov.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires json;
    requires java.desktop;

    opens com.yurov.weatherapp to javafx.fxml;
    exports com.yurov.weatherapp;
}
