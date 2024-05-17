package com.example.GreenPear;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterGraphicController {
    private final SceneManager sceneManager = SceneManager.getInstance(null);

    @FXML
    private TextField txtFieldUsername;
    @FXML
    private TextField txtFieldPass;

    public void goToLogin() throws IOException {
        this.sceneManager.showLogIn();
    }

    public void goToRicettario() throws IOException{
        this.sceneManager.showRicettario();
    }
}
