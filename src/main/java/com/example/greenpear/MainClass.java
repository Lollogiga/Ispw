package com.example.greenpear;

import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.exception.LoadSceneException;
import com.example.greenpear.utils.Printer;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.security.auth.login.CredentialException;
import java.io.IOException;

public class MainClass extends Application {
    @Override
    public void start(Stage stage){
        try {

            SceneManager sceneManager = SceneManager.getInstance(stage);
            stage.setResizable(false);
            sceneManager.showLogIn();
            stage.show();
        }catch (LoadSceneException e){
            Printer.printError(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
