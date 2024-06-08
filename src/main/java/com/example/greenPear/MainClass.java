package com.example.greenPear;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainClass extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager sceneManager = SceneManager.getInstance(stage);
        stage.setResizable(false);
        sceneManager.showWriteDiet("Breakfast");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
