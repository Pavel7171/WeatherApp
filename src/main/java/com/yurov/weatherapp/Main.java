package com.yurov.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("weatherapp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 276, 442);
        stage.setTitle("WeatherApp");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/logo.png")));
    }

    public static void main(String[] args) {
        launch(args);
    }
}