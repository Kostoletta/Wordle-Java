package com.metohu.wordle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Wordle!");
        stage.setScene(scene);
        stage.setResizable(false);
        MenuController controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}