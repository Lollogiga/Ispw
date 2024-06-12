package com.example.greenpear.controllergrafico;

import com.example.greenpear.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginGraphicController {
    private final SceneManager sceneManager = SceneManager.getInstance(null);

    @FXML
    private TextField txtFieldUsername;
    @FXML
    private TextField txtFieldPass;

    public void goToRegister() throws IOException {
        this.sceneManager.showRegister();
    }

    public void goToRicettario() throws IOException{
        this.sceneManager.showRicettario();
    }

}